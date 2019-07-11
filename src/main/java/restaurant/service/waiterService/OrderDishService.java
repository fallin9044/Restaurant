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
public class OrderDishService {

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

	public Object loadOrderDish(Long tableId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("existingMenu", menuRepository.findByTableId(tableId));
		map.put("dishes", dishRepository.findAll());
		map.put("tableId", tableId);
		return WebUtils.setModelAndView("orderDish", map);
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
