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
public class EditWaiterController {
	
	
	@Autowired
	ManagerService managerService;
	
	private static final String editWaiter1 = "edit_waiter";
	
	@RequestMapping("/editWaiter")
	public Object Edit_Waiter(HttpSession session,
			@RequestParam(value="personId") Long personId) {
		
		return managerService.editWaiter(editWaiter1, personId);
	}
	
	@ResponseBody
	@RequestMapping("/editSubmit")
	public Object Edit_Submit(HttpSession session,@RequestParam(value="waiterid") long waiterid,
			@RequestParam(value="waitername") String waitername,@RequestParam(value="sex") String sex,
			@RequestParam(value="telephone") String telephone,@RequestParam(value="password") String password) {
		
		Map<String,Object> maps = new HashMap<>();
		int flag = managerService.updatePerson(waiterid, waitername, sex, telephone, password);
		System.out.print("dsfadfasdfdasfa"+flag);
		maps.put("isrepeat",flag);
		return maps;
	}

}
