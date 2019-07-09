package restaurant.controller.managerController;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import restaurant.service.managerService.ManagerService;
@Controller
public class CheckController {
	@Autowired
	ManagerService managerService;
	@RequestMapping("/manager/check")
	public Object showManageCheck(HttpSession httpSession,@RequestParam(value = "start",defaultValue = "0") int start){
		return managerService.getPerson(start);
	} 

}
