package restaurant.service.waiterService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import restaurant.entity.Dining;
import restaurant.entity.Menu;
import restaurant.repository.DiningRepository;
import restaurant.repository.DishRepository;
import restaurant.repository.MenuRepository;
import restaurant.repository.PersonRepository;
import restaurant.utils.WebUtils;

@Service
public class WaiterService  {
    
    @Autowired
    DiningRepository diningDAO;
    
    @Autowired
    PersonRepository personRepository;
    
    @Autowired
    MenuRepository menuRepository;
    
    @Autowired
    DishRepository dishRepository;

	public Object loadTableStatus() {
		Map<String,Object> map= new HashMap<String, Object>();
		map.put("tablestatus",diningDAO.findAll());
		return 	WebUtils.setModelAndView("tableStatus", map);
	}
	
	
	public Object loadOrderDish(Long tableId) {
		Map<String,Object> map= new HashMap<String, Object>();
		map.put("existingMenu",menuRepository.findByTableId(tableId));
		map.put("dishes", dishRepository.findAll());
		map.put("tableId", tableId);
		return WebUtils.setModelAndView("orderDish", map);
	}
	
	@Transactional
	public void exitLogging(HttpSession session){
		long id = (long) session.getAttribute("personId");
		//personRepository.waiterIsNotWork(id);
		session.setAttribute("personId", null);
		session.setAttribute("authority", null);
	}

	@Transactional(isolation=Isolation.SERIALIZABLE)
	public boolean takeTable(long tableId){
		Dining dining = diningDAO.findById(tableId).get();
		if(dining.getTableState()==0){
			diningDAO.takeTable(tableId);
			return true;
		}else{
			return false;
		}
	}
	
	@Transactional
	public String addMenu(String menus) {
		try {
			JSONArray jsons = JSON.parseArray(menus);
			Menu menu;
			for(int i=0;i<jsons.size();i++) {
				menu = jsons.getObject(i,Menu.class);
				List<Menu> res= menuRepository.findByTableIdAndDishId(menu.getTableId(),menu.getDishId());
				if(res.size()>0) {
					Menu menu2 = res.get(0);
					menu2.setDishNumber(menu2.getDishNumber()+menu.getDishNumber());
					menuRepository.save(menu2);
				}else {
					menuRepository.save(menu);
				}
			}
			
		}catch (Exception e) {
			return "failed";
		}
		return "success";
	}
}
