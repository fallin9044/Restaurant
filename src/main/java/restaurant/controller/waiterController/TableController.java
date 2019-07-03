package restaurant.controller.waiterController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import restaurant.entity.Dining;
import restaurant.service.waiterService.WaiterService;

@Controller
public class TableController {

	@Autowired
	WaiterService waiterService;
	
	@RequestMapping("/waiter/tableStatus")
	public Object showTableStatus(){
		return waiterService.loadTableStatus();
	}
	

	
}
