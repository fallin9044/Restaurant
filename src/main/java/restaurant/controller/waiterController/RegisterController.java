package restaurant.controller.waiterController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/waiter")
public class RegisterController {

	private static final String registerPage = "register";
	
	@RequestMapping("/register")
	public Object turnRegisterPage(){
		
		return registerPage;
	}
	
	@RequestMapping("/logging")
	public Object loggingValidation(@RequestParam("username") String user,
			@RequestParam("password") String password){
		
		return registerPage;
	}
	
}
