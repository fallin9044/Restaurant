package restaurant.service.managerService;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import restaurant.entity.Dish;
import restaurant.entity.Person;
import restaurant.repository.DishRepository;
import restaurant.entity.OrderStream;
import restaurant.entity.Person;
import restaurant.repository.OrderStreamRepository;
import restaurant.repository.PersonRepository;
import restaurant.utils.WebUtils;

@Service
public class ManagerService {

	@Autowired
	PersonRepository personDAO;
	
	@Autowired
	DishRepository dishDAO;
	
	/**************************************************/
	/***
	 * 添加一个新菜(Johnieh)
	 * @author Nie Jun
	 * @param name 菜名
	 * @param price 价格
	 * @param desc 描述
	 * @param pic 图片
	 * @param rec 推荐
	 */
	public void addNewDish(String name,int price,String desc,String pic,int rec)
	{
        Dish d = new Dish();
        d.setDishName(name);
        d.setDishPrice(price);
        d.setDishDesc(desc);
        d.setDishPicture(pic);
        d.setIsrecommend(rec);
        dishDAO.save(d);
	}
	
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
	/**
	 * 通过ID找到菜品
	 * @author Nie Jun
	 * @param id
	 * @return 一个菜品实体
	 */
	public Dish findDish(long id)
	{
		return dishDAO.SelectByID(id);
	}
	
	/**
	 * 修改推荐菜单
	 * @author Nie Jun (1状态推荐，2状态不推荐)
	 * @param rec
	 */
	public void setRecommend(int rec)
	{
		dishDAO.updateRecom(rec);
	}
	
	/*************************************************/
	

	@Autowired
	OrderStreamRepository orderStreamRepository;

	public Object loadOrderStream(String detail, int start) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<OrderStream> orders = null;
		List<OrderStream> orderList = new ArrayList<>();
		if(detail.equals("今日")){
			orders = orderStreamRepository.findToday();
		}else if(detail.equals("最近一周")){
	        orders = orderStreamRepository.findWeek();
		}else if(detail.equals("最近一月")){
	        orders = orderStreamRepository.findMonth();
		}else{
			orders = orderStreamRepository.findAll();
		}
		WebUtils.getObjectList(orders, orderList, start, 5);
		int[] info = WebUtils.getPagingInfo(start, 5, orders.size());
		map.put("next", info[0]);
		map.put("pre", info[1]);
		map.put("last", info[2]);
		map.put("orderStream", orderList);
		map.put("detail", detail);
		return WebUtils.setModelAndView("manage_order", map);
	}

	public Object getPerson(){
		//person
		List<Person> persons =personDAO.getWorkingPerson();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("persons",persons);
		//get now
		Date day=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    String dateNowStr = sdf.format(day);
	    StringTokenizer st=new StringTokenizer(dateNowStr);
	    String now=st.nextToken();
		//time&if attending
		List<String> dates=new ArrayList<>();
		List<String> times=new ArrayList<>();
		List<String> ifAttending=new ArrayList<>(); 
		for(Person person :persons){
			String str 
			= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(person.getPersonTime());
			StringTokenizer stringTokenizer=new StringTokenizer(str);
			String date=stringTokenizer.nextToken();
			dates.add(date);
			times.add(stringTokenizer.nextToken());
			if(date.equals(now)){
				ifAttending.add("出勤");
			}
			else ifAttending.add("缺勤");
		}
		map.put("dates", dates);
		map.put("times", times);
		
		map.put("ifAttending", ifAttending);
		return WebUtils.setModelAndView("manage_check", map);
	}
	/**
	 * 展示所有在职的服务员
	 * @param location 目的网页
	 * @return
	 */
	@Transactional(isolation=Isolation.SERIALIZABLE)
	public Object takePerson(String location){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("personList",personDAO.findByAuthority());
		return WebUtils.setModelAndView(location, map);
		
	}
	
	@Transactional(isolation=Isolation.SERIALIZABLE)
	public int addPerson(String name,String sex,String telephone,String password){
		int flag = 0;
		int sexnum = 0;
		if(sex=="男") {
			sexnum = 1;
		}else {
			sexnum = 0;
		}
		
		personDAO.save(new Person(name,password,sexnum,telephone,1,1));
		
		
		return flag;
		
	}
}
