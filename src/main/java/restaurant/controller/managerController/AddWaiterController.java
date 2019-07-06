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
public class AddWaiterController {

		private static final String addWaiter = "add_waiter";
		
		@Autowired
		ManagerService managerService;
		
		@RequestMapping("/addWaiter")
		public Object AddWaiter(HttpSession session){
			return addWaiter;
		}
		
		/**
		 * 
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

	

}
