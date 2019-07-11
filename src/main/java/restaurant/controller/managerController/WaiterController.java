package restaurant.controller.managerController;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import restaurant.service.managerService.WaiterManageService;

/**
 * 
 * @author wychen
 *
 */
@Controller
public class WaiterController {
	
	@Autowired
	WaiterManageService managerService;
	
	private static final String managerIndex = "manage_index";
	private static final String addWaiter = "add_waiter";
	private static final String editWaiter1 = "edit_waiter";
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
	
	@RequestMapping("/addWaiter")
	public Object AddWaiter(HttpSession session){
		return addWaiter;
	}
	
	/**
	 * 
	 * @author wychen
	 * @param session
	 * @param name 姓名
	 * @param sex 性别
	 * @param telephone 电话
	 * @param password 密码
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/adding")
	public Object addWaiter(HttpSession session,@RequestParam(value="waitername") String waitername,
			@RequestParam(value="sex") String sex,@RequestParam(value="telephone") String telephone,
			@RequestParam(value="password") String password){
		
		System.out.println("dfadsfasdfsfsaf");			
		Map<String,Object> maps = new HashMap<>();
		int flag = managerService.addPerson(waitername, sex, telephone, password);
		System.out.println(flag);
		maps.put("isrepeat", flag);
		return maps;
	}

	@ResponseBody
	@RequestMapping("/deleteWaiter")
	public Object deleteWaiter(HttpSession session,@RequestParam(value="personId") long personId){		
		managerService.deleteWaiter(personId);;
		return "success";
	}
	
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
		maps.put("isrepeat",flag);
		return maps;
	}
}
