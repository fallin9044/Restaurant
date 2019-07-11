package restaurant.service.waiterService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import restaurant.entity.Dining;
import restaurant.entity.Menu;
import restaurant.entity.OrderStream;
import restaurant.entity.Reserves;
import restaurant.repository.DiningRepository;
import restaurant.repository.DishRepository;
import restaurant.repository.MenuRepository;
import restaurant.repository.OrderStreamRepository;
import restaurant.repository.PersonRepository;
import restaurant.repository.ReservesRepository;
import restaurant.utils.WebUtils;

@Service
public class WaiterService {

	@Autowired
	DiningRepository diningDAO;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	DishRepository dishRepository;

	@Autowired
	OrderStreamRepository orderStreamRepository;

	@Autowired
	ReservesRepository reservesRepository;
	
	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public Object loadTableStatus(String waiterPage, int start) {
		reservesRepository.deleteOverTimeReserve();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Dining> dinings = diningDAO.findAll();
		List<Dining> diningList = new ArrayList<>();
		WebUtils.getObjectList(dinings, diningList, start, 5);
		int[] info = WebUtils.getPagingInfo(start, 5, dinings.size());
		map.put("next", info[0]);
		map.put("pre", info[1]);
		map.put("last", info[2]);
		map.put("count", info[3]);
		map.put("total", info[4]);
		map.put("tablestatus", diningList);
		List<Reserves> reserves = reservesRepository.twoHourInterval();
		List<Reserves> reservesList = new ArrayList<>();
		getFirstReserve(reserves, reservesList, diningList);
		map.put("reserves", reservesList);

		System.out.println("你好好好好好");
		return WebUtils.setModelAndView(waiterPage, map);
	}
	public  void getFirstReserve(List<Reserves> org, List<Reserves> target,List<Dining> diningList) {
		Map<Long, Integer> tmp = new HashMap<>();
		Integer index = 0;
		for (Reserves res : org) {
			if (tmp.get(res.getTableId()) == null) {
				tmp.put(res.getTableId(), index);
			} else {
				Reserves temp = org.get(tmp.get(res.getTableId()));
				if (res.getReserveTime().before(temp.getReserveTime())) {
					tmp.replace(res.getTableId(), index);
				}
			}
			index++;
		}
		for (Long key : tmp.keySet()) {
			index = tmp.get(key);
			target.add(org.get(index));
		}
		
		for(Dining din:diningList){
			if(din.getTableState()==1){
				int flag = 0;
				for(Reserves res:target){
					if(res.getTableId()==din.getTableId()){
						flag = 1;
					}
				}
				if(flag == 0){
					din.setTableState(0); 
					HibernateEntityManager hi=(HibernateEntityManager)entityManager;
					Session session = hi.getSession();
					session.evict(din);
				}
			}
		}
	}
	public Object loadOrderDish(Long tableId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("existingMenu", menuRepository.findByTableId(tableId));
		map.put("dishes", dishRepository.findAll());
		map.put("tableId", tableId);
		return WebUtils.setModelAndView("orderDish", map);
	}

	@Transactional
	public void exitLogging(HttpSession session) {
		session.setAttribute("personId", null);
		session.setAttribute("authority", null);
	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public boolean takeTable(long tableId, String tableState,long reserveId) {
		if (tableState.equals("空闲")) {
			diningDAO.takeTable(tableId);
			return true;
		} else if (tableState.equals("预约")) {
			diningDAO.takeTable(tableId);
			reservesRepository.deleteById(reserveId);
			return true;
		} else {
			return false;
		}
	}

	@Transactional
	public String addMenu(String menus) {
		try {
			JSONArray jsons = JSON.parseArray(menus);
			Menu menu;
			for (int i = 0; i < jsons.size(); i++) {
				menu = jsons.getObject(i, Menu.class);
				List<Menu> res = menuRepository.findByTableIdAndDishId(menu.getTableId(), menu.getDishId());
				if (res.size() > 0) {
					Menu menu2 = res.get(0);
					menu2.setDishNumber(menu2.getDishNumber() + menu.getDishNumber());
					menu2.setDishState(0);
					menuRepository.saveAndFlush(menu2);
				} else {
					menuRepository.saveAndFlush(menu);
				}
			}

		} catch (Exception e) {
			return "failed";
		}
		return "success";
	}

	@Transactional
	public String settleAccount(HttpSession session, long tableId, int total) {
		try {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			diningDAO.releaseTable(tableId);
			OrderStream orderStream = new OrderStream(0, (Long) session.getAttribute("personId"), total, now, tableId);
			menuRepository.deleteByTableId(tableId);
			if(reservesRepository.findByTableId(tableId).size()>0) diningDAO.takeTableWithReserve(tableId);
			orderStreamRepository.saveAndFlush(orderStream);
		} catch (Exception e) {
			return "failed";
		}
		return "success";
	}

	@Transactional
	public Object changeMenuState(long menuId) {
		try {
			menuRepository.changeMenuState(menuId, 1);
		} catch (Exception e) {
			return "failed";
		}
		return "success";
	}
	
	@Transactional
	public Object houHui(HttpSession session, long tableId) {
		try {
			if(reservesRepository.findByTableId(tableId).size()>0) diningDAO.releaseTableWithReserve(tableId);
			else diningDAO.releaseTable(tableId);

		} catch (Exception e) {
			return "failed";
		}
		return "success";
	}
}
