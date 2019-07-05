package restaurant.controller.managerController;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import restaurant.service.managerService.ManagerService;

@Controller
public class OrderStreamController {

	@Autowired
	ManagerService managerService;

	@RequestMapping("/manager/orderStream")
	public Object showOrderStream(HttpSession session, 
			@RequestParam(value="start",required=false,defaultValue="0") int start,
			@RequestParam(value="detail",required=false,defaultValue="-1") String detail) {
		
		return managerService.loadOrderStream(detail,start);
	}

}
