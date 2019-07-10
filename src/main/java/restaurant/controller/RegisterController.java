package restaurant.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import restaurant.service.LoggingValidationService;
import restaurant.service.managerService.ManagerService;
import restaurant.service.waiterService.WaiterService;
import restaurant.utils.WebUtils;

@Controller
public class RegisterController {

	@Autowired
	private LoggingValidationService loggingValidationService;
	@Autowired
	WaiterService waiterService;
	@Autowired
	ManagerService managerService;
	
	private static final String registerPage = "register";
	private static final String managerIndex = "redirect:managerIndex";
	private static final String waiterTable = "redirect:waiter/tableStatus";
	
	@RequestMapping("/register")
	public Object tryRegisterPage(HttpSession session){
		if(WebUtils.pageValidate(session)){
			int authority = (int) session.getAttribute("authority");
			if(authority==2){
				return waiterTable;
			}else{
				return managerIndex;
			}
		}
		return registerPage;
	}
	
	@ResponseBody
	@RequestMapping("/logging")
	public Object loggingValidation(HttpSession session,@RequestParam(value="tele") String tele,
			@RequestParam(value="password") String password){
		Map<String,Object> maps = new HashMap<>();
		int result = loggingValidationService.findPerson(session, tele, password);
		//2表示服务员，1表示经理
		maps.put("authority", result);
		return maps;
	}
	
}
