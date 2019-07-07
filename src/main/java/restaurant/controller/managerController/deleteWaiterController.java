package restaurant.controller.managerController;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import restaurant.service.managerService.ManagerService;

@Controller
public class deleteWaiterController {
	
	@Autowired
	ManagerService managerService;
	
	@RequestMapping("/deleteWaiter")
	public void DisplaWaiter(HttpSession session,
			@RequestParam(value="personId") String id){
		Long personId = Long.parseLong(id);
		managerService.deleteWaiter(personId);
	}

}
