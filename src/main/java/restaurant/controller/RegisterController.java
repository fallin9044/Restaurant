package restaurant.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import restaurant.service.LoggingValidationService;
import restaurant.service.waiterService.WaiterService;
import restaurant.utils.WebUtils;

@Controller
public class RegisterController {

	@Autowired
	private LoggingValidationService loggingValidationService;
	@Autowired
	WaiterService waiterService;
	
	private static final String registerPage = "register";
	private static final String managerPage = "manager";
	
	@RequestMapping("/register")
	public Object tryRegisterPage(){
		if(WebUtils.pageValidate()){
			int authority = (int) WebUtils.getSessionAttribute("authority");
			if(authority==2){
				return waiterService.loadTableStatus();
			}else{
				return managerPage;
			}
		}
		return registerPage;
	}
	
	@ResponseBody
	@RequestMapping("/logging")
	public Object loggingValidation(@RequestParam(value="name",required = false, defaultValue = "*****") String user,
			@RequestParam(value="password",required = false, defaultValue = "*****") String password){
		Map<String,Object> maps = new HashMap<>();
		int result = loggingValidationService.findPerson(user, password);
		//2表示服务员，1表示经理
		maps.put("authority", result);
		return maps;
	}
	
}
