package restaurant.controller.waiterController;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import restaurant.service.waiterService.WaiterService;

@Controller
public class TableController {

	@Autowired
	WaiterService waiterService;
	
	@RequestMapping("/waiter/tableStatus")
	public Object showTableStatus(HttpSession session){
		return waiterService.loadTableStatus();
	}
	
	@ResponseBody
	@RequestMapping("/waiter/exit")
	public Object exitLogging(HttpSession session){
		Map<String,Object> maps = new HashMap<>();
		waiterService.exitLogging(session);
		return maps;
	}
	
	@ResponseBody
	@RequestMapping("/waiter/takeTable")
	public Object takeTable(HttpSession session,
			@RequestParam("tableState") String tableState,
			@RequestParam("tableId") long tableId){
		Map<String,Object> maps = new HashMap<>();
		
		if(waiterService.takeTable(tableId,tableState)){
			maps.put("flag", 1);
		}else{
			maps.put("flag", -1);
		}
		
		return maps;
	}
	
}
