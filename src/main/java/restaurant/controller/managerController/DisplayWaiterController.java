package restaurant.controller.managerController;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import restaurant.service.managerService.ManagerService;

@Controller
public class DisplayWaiterController {
	
	@Autowired
	ManagerService managerService;
	
	private static final String managerIndex = "manage_index";
		
	@RequestMapping("/managerIndex")
	public Object DisplaWaiter(HttpSession session){
		
		return managerService.takePerson(managerIndex);
	}
	
	
	

}
