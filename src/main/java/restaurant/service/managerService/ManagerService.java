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
public class ManagerService {

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
		map.put("total", info[4]+1);
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

	public Object getPerson(int start){
		Map<String,Object> map=new HashMap<String,Object>();
		//person
		List<Person> personList =personDAO.getWorkingPerson();
		List<Person> personForm = new ArrayList<>();
		WebUtils.getObjectList(personList, personForm, start, 6);
		int[] info = WebUtils.getPagingInfo(start, 6, personList.size());
		map.put("next", info[0]);
		map.put("pre", info[1]);
		map.put("last", info[2]);
		map.put("count", info[3]);
		map.put("total", info[4]);
		map.put("persons", personForm);
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

		for(Person person :personForm){
			if(person.getPersonTime() != null) {
			String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(person.getPersonTime());

			StringTokenizer stringTokenizer=new StringTokenizer(str);
			String date=stringTokenizer.nextToken();
			if(date.equals(now)){
				ifAttending.add("出勤");
				dates.add(date);
				times.add(stringTokenizer.nextToken());
			}
			else ifAttending.add("缺勤");
			}
			else {
			dates.add("");
			times.add("");
			ifAttending.add("缺勤");
			}
			
		}
		map.put("today", now);
		map.put("dates", dates);
		map.put("times", times);
		
		map.put("ifAttending", ifAttending);
		return WebUtils.setModelAndView("manage_check", map);
	}
	
	
	/**
	 * 展示所有在职的服务员,并进行分页
	 * 
	 * @author wychen
	 * @param location 目的网页
	 * @return
	 *
	 */
	@Transactional(isolation=Isolation.SERIALIZABLE)
	public Object takePerson(String location,int start){
		
		Map<String,Object> map = new HashMap<String,Object>();
		List<Person> personList = personDAO.findByAuthority();
		List<Person> personForm = new ArrayList<>();
		//从数据库中获取所有的服务员
		map.put("personList",personDAO.findByAuthority());

		WebUtils.getObjectList(personList, personForm, start, 6);
		int[] info = WebUtils.getPagingInfo(start, 6, personList.size());
		map.put("next", info[0]);
		map.put("pre", info[1]);
		map.put("last", info[2]);
		map.put("count", info[3]);
		map.put("total", info[4]);
		map.put("personStream", personForm);
		
		return WebUtils.setModelAndView(location, map);
		
		
		
	}
	/**
	 * 
	 * 添加新的服务员
	 * 
	 * @author wychen
	 * @param name 姓名
	 * @param sex 性别
	 * @param telephone 电话
	 * @param password 密码
	 * @return flag 返回标志，flag为1表示添加成功，flag为0表示添加失败
	 */
	@Transactional(isolation=Isolation.SERIALIZABLE)
	public int addPerson(String name,String sex,String telephone,String password){
		int flag = 1;
		int sexnum = 0;
		
		try {
			if(sex.equals("男")) {
				sexnum = 1;
			}else {
				sexnum = 0;
			}
			List<Person> persons= personDAO.findIsRepeat(name, telephone);
			//验证是否重复添加
			if(persons==null||persons.size()==0) {
				flag = 1;
				personDAO.save(new Person(name,password,sexnum,telephone,2,1));
				
			}else {
				flag = 0;
			}
		}catch (Exception e){
			e.printStackTrace();
			
		}
		

		return flag;
		
	}
	/**
	 * 
	 * @param location
	 * @param personId
	 * @return
	 */
	@Transactional(isolation=Isolation.SERIALIZABLE)
	public Object editWaiter(String location,Long personId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Optional<Person> czxzz = personDAO.findById(personId);
		String sexName = "";
		if(czxzz.get().getSex()==1) {
			sexName = "男";
		}else {
			sexName = "女";
		}
		map.put("person", czxzz.get());
		map.put("sex", sexName);
		
		return WebUtils.setModelAndView(location, map);
	}
	/**
	 * 将服务员的在职状态置0
	 * @author wychen
	 * @param personId 服务员ID
	 */
	@Transactional(isolation=Isolation.SERIALIZABLE)
	public void deleteWaiter(Long personId) {
		
		personDAO.waiterIsNotWork(personId);
	}
	
	/**
	 * 
	 * 更新信息
	 * 
	 * @author wychen
	 * @param name 姓名
	 * @param sex 性别
	 * @param telephone 电话
	 * @param password 密码
	 * @return flag 返回标志，flag为1表示更新成功，flag为0表示更新失败
	 */
	@Transactional(isolation=Isolation.SERIALIZABLE)
	public int updatePerson(Long id,String name,String sex,String telephone,String password){
		int flag = 0;
		int sexnum = 0;
		
		try {
			if(sex.equals("男")) {
				sexnum = 1;
			}else {
				sexnum = 0;
			}
			List<Person> persons= personDAO.findIsRepeatx(name,sexnum,telephone,password);
			//验证是否重复添加
			if(persons==null||persons.size()==0) {
				flag = 1;
				personDAO.editinfo(id, name, sexnum, telephone, password);;
				
			}else {
				flag = 0;
			}
		}catch (Exception e){
			e.printStackTrace();
			
		}
		

		return flag;
		
	}
	public Object loadTables(String root) {
		Map<String,Object> map= new HashMap<String, Object>();
		List<Dining> tablesDinings=diningDAO.findAll();
		map.put("tables", tablesDinings);
		return 	WebUtils.setModelAndView(root, map);
	}
	
	@Transactional
	public Object addTable(long id, int number) {
		Optional<Dining> res= diningDAO.findById(id);
		if(res.isPresent())return 0;
		else {
			diningDAO.save(new Dining(id, number, 0,null));
		}
		return 1;
	}
	
	@Transactional
	public Object deleteTable(long id) {
		try{
			diningDAO.deleteById(id);
		}catch(Exception e) {
			return 0;
		}
		return 1;
	}
	


	public Object loadReserves(String managereserve, long tableId) {
		Map<String,Object> map= new HashMap<String, Object>();
		List<Reserves> reserves=reservesDAO.findByTableId(tableId);
		map.put("reserves",reserves );
		map.put("tableId", tableId);
		return WebUtils.setModelAndView(managereserve, map);
	}

	@Transactional
	public Object isAbleReserve(String time, String tele,long tableId) {
		SimpleDateFormat x=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date reserveTime =x.parse(time);
			System.out.println(time);
			Timestamp timestamp=new Timestamp(reserveTime.getTime());
			List<Reserves> res=reservesDAO.twoHourInterval(tableId,reserveTime);
			if(res.size()>0)return 0;
			List<Dining> tabs = diningDAO.isAbleReserve(tableId,reserveTime);
			if(tabs.size()>0)return 0;
			reservesDAO.saveAndFlush(new Reserves(tableId, timestamp, tele));
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	@Transactional
	public Object deleteReserve(long id) {
		try{
			reservesDAO.deleteById(id);
		}catch(Exception e) {
			return 0;
		}
		return 1;
	}

	public Object loadSearchedTable(String managereserve, String time) {
		SimpleDateFormat x=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List<Dining> tablesDinings=diningDAO.findAll();
		List<Dining> tablesres=new ArrayList<Dining>();
		Date date;
		Map<String,Object> map= new HashMap<String, Object>();
		try {
			date = x.parse(time);		
		for(Dining a:tablesDinings) {
			if(diningDAO.isAbleReserve(a.getTableId(),date).size()==0&&reservesDAO.twoHourInterval(a.getTableId(), date).size()==0)
				tablesres.add(a);
		}
		map.put("datetime",time.replaceFirst(" ", "T"));
		}catch (Exception e) {
			// TODO: handle exception
		}
		map.put("tables", tablesres);

		return 	WebUtils.setModelAndView(managereserve, map);
	}
}
