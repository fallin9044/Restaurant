package restaurant.controller.waiterController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TestController {

	@GetMapping(value = "/hello")
	public Object testHello() {

		return "hello";
	}

	@RequestMapping(value = "/index")
	public Object testRequestParam(@RequestParam("username") String user,
			@RequestParam(value = "age", required = false, defaultValue = "0") int age) {
		ModelAndView modelAndView = new ModelAndView("index");
		System.out.println("test Request Param " + user + " " + age);
		modelAndView.addObject("user",user);
		modelAndView.addObject("age",age);
		return modelAndView;
	}

}
