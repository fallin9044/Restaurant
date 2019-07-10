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
	
	private static final String waiterTable = "tableStatus";
	
	@RequestMapping("/waiter/tableStatus")
	public Object showTableStatus(HttpSession session,
			@RequestParam(value="start",required=false,defaultValue="0") int start){
		return waiterService.loadTableStatus(waiterTable,start);
	}
	
	
	
	@ResponseBody
	@RequestMapping("/waiter/takeTable")
	public Object takeTable(HttpSession session,
			@RequestParam("tableState") String tableState,
			@RequestParam("tableId") long tableId,
			@RequestParam("reserveId") long reserveId){
		Map<String,Object> maps = new HashMap<>();
		
		if(waiterService.takeTable(tableId,tableState,reserveId)){
			maps.put("flag", 1);
		}else{
			maps.put("flag", -1);
		}
		
		return maps;
	}
	
}
