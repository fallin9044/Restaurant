package restaurant.controller.managerController;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import restaurant.service.managerService.ManagerService;

/**
 * 
 * @author wychen
 *
 */
@Controller
public class DisplayWaiterController {
	
	@Autowired
	ManagerService managerService;
	
	private static final String managerIndex = "manage_index";
	/**
	 * 
	 * @param session
	 * @param start 起始号码
	 * @return
	 */
	@RequestMapping("/managerIndex")
	public Object DisplaWaiter(HttpSession session,
			@RequestParam(value="start",required=false,defaultValue="0") int start){
		
		return managerService.takePerson(managerIndex,start);
	}
	
	
	

}
