package restaurant.service.waiterService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restaurant.repository.DiningRepository;
import restaurant.utils.WebUtils;

@Service
public class WaiterService  {
    
    @Autowired
    DiningRepository diningDAO;

	public Object loadTableStatus() {
		Map<String,Object> map= new HashMap<String, Object>();
		map.put("tablestatus",diningDAO.findAll());
		return 	WebUtils.setModelAndView("tableStatus", map);
	}

}
