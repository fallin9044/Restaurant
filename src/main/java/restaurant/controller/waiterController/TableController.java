package restaurant.controller.waiterController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
