package restaurant.controller.waiterController;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import restaurant.service.waiterService.WaiterService;

@Controller
public class OrderDishController {
	
	@Autowired
	WaiterService waiterService;
	
	@RequestMapping("/waiter/orderDish")
	public Object showTableStatus(HttpSession session,@RequestParam(value = "tableId",required = false, defaultValue = "1") long tableId){
		System.out.println("lalalalalalal"+tableId);
		return waiterService.loadOrderDish(tableId);
	}
	
	
}
