package restaurant.service.waiterService;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restaurant.repository.DiningRepository;
import restaurant.repository.PersonRepository;
import restaurant.utils.WebUtils;

@Service
public class WaiterService  {
    
    @Autowired
    DiningRepository diningDAO;
    @Autowired
    PersonRepository personRepository;

	public Object loadTableStatus() {
		Map<String,Object> map= new HashMap<String, Object>();
		map.put("tablestatus",diningDAO.findAll());
		return 	WebUtils.setModelAndView("tableStatus", map);
	}
	
	@Transactional
	public void exitLogging(HttpSession session){
		long id = (long) session.getAttribute("personId");
		personRepository.waiterIsNotWork(id);
		session.setAttribute("personId", null);
		session.setAttribute("authority", null);
		System.out.println("LLLLLLLLLLLLLLLLLLL");
	}

}
