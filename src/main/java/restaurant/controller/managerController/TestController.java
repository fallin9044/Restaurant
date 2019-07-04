package restaurant.controller.managerController;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	private static final String managerPage = "manager";
	
	@RequestMapping("/manager")
	public Object managerPage(HttpSession session){
		return managerPage;
	}
	
}
