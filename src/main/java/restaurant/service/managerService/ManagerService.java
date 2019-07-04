package restaurant.service.managerService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restaurant.repository.PersonRepository;
import restaurant.utils.WebUtils;
@Service
public class ManagerService {
	
	@Autowired
	PersonRepository personDAO;
	
	public Object getPerson(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("persons",personDAO.findAll());
		return 	WebUtils.setModelAndView("manage_check", map);
	}
}
