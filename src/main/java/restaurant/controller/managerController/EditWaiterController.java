package restaurant.controller.managerController;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

}
