package restaurant.controller.waiterController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TableController {

	@RequestMapping("/tableStatus")
	public Object showTableStatus(){
		
		return null;
	}
	
	
}
