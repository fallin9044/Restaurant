package restaurant.service.managerService;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import restaurant.entity.Dining;
import restaurant.entity.Dish;
import restaurant.entity.Person;
import restaurant.entity.Reserves;
import restaurant.repository.DiningRepository;
import restaurant.repository.DishRepository;
import restaurant.entity.OrderStream;
import restaurant.entity.Person;
import restaurant.repository.OrderStreamRepository;
import restaurant.repository.PersonRepository;
import restaurant.repository.ReservesRepository;
import restaurant.utils.WebUtils;

@Service
public class DishManageService {

	@Autowired
	PersonRepository personDAO;
	
	@Autowired
	DishRepository dishDAO;
	
	@Autowired
	ReservesRepository reservesDAO;
	
	@Autowired
	DiningRepository diningDAO;
	
	/**
	 * 根据Id删除一个菜
	 * @param id
	 * @author Nie Jun
	 */
	public void deleteDish(int id)
	{
	   dishDAO.DeleteById(id);
	}
	
	/**
	 * 修改一个菜品的相关信息
	 * @author Nie Jun
	 * @param id 菜品Id
	 * @param d 菜品被更新
	 */
	public void updateDish(long id,Dish d)
	{
	     	dishDAO.updateDish(id,d.getDishName(),d.getDishPrice(),d.getDishDesc(),d.getDishPicture(),d.getIsrecommend());
	}
	
	/**
	 * 通过菜名的模糊搜索得到相应列表 
	 * @author Nie Jun
	 * @param name
	 * @return dish类的list
	 */
	public List<Dish> findDishs(String name)
	{
		List<Dish> d_list = dishDAO.selectByName(name);
		return d_list;
	}
	/**************************************************/
	/***
	 * 添加一个新菜(Johnieh)
	 * @author Nie Jun
	 * @param name 菜名
	 * @param price 价格
	 * @param desc 描述
	 * @param pic 图片
	 * @param rec 推荐
	 * @return 1 增加成功 0 增加失败
	 */
	public int addNewDish(String name,int price,String desc,String pic,int rec)
	{
        List<Dish> l = dishDAO.selectByName(name);
        if(l.size()>1)
        	return 0;
		Dish d = new Dish();
        d.setDishName(name);
        d.setDishPrice(price);
        d.setDishDesc(desc);
        d.setDishPicture(pic);
        d.setIsrecommend(rec);
        dishDAO.save(d);
        return 1;        
	}
	
	/**
	 * 根据Id删除一个菜
	 * @param id
	 * @author Nie Jun
	 */
	@Transactional
	public void deleteDish(long id)
	{
		//System.out.println("niejunnmmmmmmm"+id);
	   dishDAO.DeleteById(id);
	}
	
	/**
	 * 修改一个菜品的相关信息
	 * @author Nie Jun
	 * @param id 菜品Id
	 * @param d 菜品被更新
	 */
	@Transactional
	public void updateDish(long dishId,String dishName,int dishPrice,String dishDesc,String dishPicture, int isrecommend)
	{
		System.out.println("---------------进入DAO包");
	     dishDAO.updateDish(dishId,dishName,dishPrice,dishDesc,dishPicture,isrecommend);
	}
	

	/**
	 * 通过ID找到菜品
	 * @author Nie Jun
	 * @param id
	 * @return 一个菜品实体
	 */
	public Dish findDish(long id)
	{
		List<Dish> l = dishDAO.SelectByID(id);
		return l.get(0);
	}
	
	/**
	 * 更改推荐菜品
	 * @param dishid 菜品Id
	 * @param rec 更新后状态(1推荐 0不推荐)
	 */
	@Transactional
	public void setRecommend(long dishid,int rec)
	{
		System.out.println("--------进入DAO操作");
		dishDAO.updateRecom(rec,dishid);
	}
	
	/****
	 * 通过菜名看菜是否已进存在菜单中
	 * @author Nie Jun
	 * @param name
	 * @return dish类型
	 */
	public List<Dish> findbyname(String name)
	{
		List<Dish> d = dishDAO.findByName(name);
		System.out.println("--------jin ry  find name");
		return d;
	}
	
	/*****
	 * 查找所有的菜品名单
	 * @author Niejun
	 * @param managedish
	 * @param start
	 * @return
	 */
	@Transactional
	public Object loadDish(String managedish,int start) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Dish> dishForm = new ArrayList<>();
		List<Dish> dishList = dishDAO.findAll();
//		map.put("dishStream", dishList);
		WebUtils.getObjectList(dishList, dishForm, start, 7);
		int[] info = WebUtils.getPagingInfo(start, 7, dishList.size());
		map.put("next", info[0]);
		map.put("pre", info[1]);
		map.put("last", info[2]);
		map.put("count", info[3]);
		map.put("total", info[4]);
		map.put("dishStream", dishForm);
		
		return WebUtils.setModelAndView(managedish, map);
	}
	
	/***
	 * 修改界面专用
	 * @author Nie jun
	 * @param name 菜品名称 
	 * @return map
	 */
	@Transactional
	public Object editDishUse(String webpage,String name)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		Optional<Dish> dish = dishDAO.selectADish(name);
		String rec = "";
		if(dish.get().getIsrecommend()==1) {
			rec = "是";
		}else {
			rec = "否";
		}
		map.put("dish", dish.get());
		map.put("recommend", rec);
		
		return WebUtils.setModelAndView(webpage, map);
	}
	
	/*************************************************/
	

}
