package restaurant.controller.managerController;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import restaurant.service.managerService.ManagerService;

@Controller
public class AddWaiterController {

		private static final String addWaiter = "add_waiter";
		ManagerService managerService;
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
		@RequestMapping("/addWaiter")
		public Object addWaiter(HttpSession session,@RequestParam(value="waitername") String name,
				@RequestParam(value="sex") String sex,@RequestParam(value="telephone") String telephone,
				@RequestParam(value="password") String password){
			
			Map<String,Object> maps = new HashMap<>();
			
			int flag = managerService.addPerson(name, sex, telephone, password);
			maps.put("flag",flag);
			return maps;
		}

	

}
