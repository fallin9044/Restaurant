package restaurant.service.managerService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import restaurant.repository.PersonRepository;
import restaurant.utils.WebUtils;
public class ManagerService {
	
	@Autowired
	PersonRepository personDAO;
	
	public Object getPerson(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("person",personDAO.findAll());
		return 	WebUtils.setModelAndView("manage_check", map);
	}
}
