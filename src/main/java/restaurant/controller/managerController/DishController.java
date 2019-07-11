package restaurant.controller.managerController;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import restaurant.entity.Dish;
import restaurant.service.managerService.DishManageService;

/***
 * 
 * 菜品的管理controller
 * @author Nie Jun
 * @date 2019.07.07
 *
 */
@Controller
public class DishController {

	private static final String manageDish = "manage_dish";
	private static final String addDishPage = "add_dish";
	private static final String editDishPage = "edit_dish";
	
	@Autowired
	DishManageService managerService;
	
	@RequestMapping("/manageDish")
	public Object manageDish(HttpSession session,@RequestParam(value = "start",defaultValue = "0")int start){
		return managerService.loadDish(manageDish,start);
	}
	
	@RequestMapping("/editDishPage")
	public Object editDishPage(HttpSession session,@RequestParam("dishName") String name)
	{
		return managerService.editDishUse(editDishPage,name);
	}
	
	@ResponseBody
	@RequestMapping("/editDish")
	public Object editDish(HttpSession session,@RequestParam(value="dishName") String dishName,
			@RequestParam(value="dishPrice")int dishPrice,@RequestParam(value="dishDesc") String dishDesc,
			@RequestParam(value="dishPicture") String dishPicture,
			@RequestParam(value="picture",required = false) MultipartFile picture,
			@RequestParam(value="isrecommend") int isrecommend
			,@RequestParam(value="dishId") long dishId){
		Map<String,Object> maps = new HashMap<>();
		List<Dish> d= managerService.findbyname(dishName);
		int flag = 0;
		
		if(d.size()>1)
		{
			flag = 1; 
		}
		System.out.println("---------flag"+flag);
		if(flag==0)
		{
			if(picture!=null) {
			String resource = System.getProperty("user.dir")+"/src/main/resources/static/dishImages/";
	        File dest = new File(resource + dishPicture+".jpg");

	        try {
	            picture.transferTo(dest);
	        } catch (IOException e) {
	            flag=1;
	        }}
			System.out.println("*******");
            managerService.updateDish(dishId,dishName, dishPrice, dishDesc,dishPicture, isrecommend);
		}
        maps.put("isrepeat", flag);
		return maps;
	}

	
	@ResponseBody
	@RequestMapping("/deleteDish")
	public void deleteDish(HttpSession session,@RequestParam("dishId")long dishId)
	{
		System.out.println("删除成功");
		managerService.deleteDish(dishId);
	}
	
	@RequestMapping("/addDishPage")
	public Object addDishPage(HttpSession session)
	{
		return addDishPage;
	}
	
	@RequestMapping("/addDish")
	@ResponseBody
	public Object addDish(HttpSession session,@RequestParam(value="dishName") String dishName,
			@RequestParam(value="dishPrice")int dishPrice,@RequestParam(value="dishDesc") String dishDesc,
			@RequestParam(value="picture") MultipartFile picture,
			@RequestParam(value="dishPicture") String dishPicture,@RequestParam(value="isrecommend") int isrecommend){
		Map<String,Object> maps = new HashMap<>();
		System.out.println(dishPicture);
		int flag = 0;
		
		List<Dish> d= managerService.findbyname(dishName);
		
		if(d.size()>0)
		{
			flag = 1; 
			//System.out.println("已经存在菜品了");
		}
		System.out.println(d.size());
		if(flag==0)
		{
			//添加图片
			String resource = System.getProperty("user.dir")+"/src/main/resources/static/dishImages/";
	        File dest = new File(resource + dishPicture+".jpg");

	        try {
	            picture.transferTo(dest);
	        } catch (IOException e) {
	            flag=1;
	        }
			//System.out.println("正在加入------");
            managerService.addNewDish(dishName, dishPrice, dishDesc,dishPicture, isrecommend);
		}
        maps.put("isrepeat", flag);
		return maps;
	}


	@RequestMapping("/changeRecommend")
	public Object changeRecommend(HttpSession session,@RequestParam("dishId")long dishId)
	{
		Dish d = managerService.findDish(dishId);
		int bef = d.getIsrecommend();
		if(bef==0)
		{
		    managerService.setRecommend(dishId,1);
		}else {
			managerService.setRecommend(dishId,0);
		}
		return manageDish;
	}
}
