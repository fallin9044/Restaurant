package restaurant.controller.waiterController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import restaurant.service.managerService.ManagerService;
@Controller
public class CheckController {
	@Autowired
	ManagerService managerService;
	@RequestMapping("/manage/check")
	public Object showManageCheck(){
		return managerService.getPerson();
	}

}
