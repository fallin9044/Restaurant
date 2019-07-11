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
public class WaiterManageService {

	@Autowired
	PersonRepository personDAO;
	
	@Autowired
	DishRepository dishDAO;
	
	@Autowired
	ReservesRepository reservesDAO;
	
	@Autowired
	DiningRepository diningDAO;
	

	
	
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
			List<Person> persons= personDAO.findIsRepeat(telephone);
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
	 * 编辑服务员信息
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
			List<Person> persons= personDAO.findIsRepeatx(telephone);
			//验证是否重复添加
			if(persons.size()<1) {
				flag = 1;
				personDAO.editinfo(id, name, sexnum, telephone, password);
				
			}else {
				flag = 0;
			}
		}catch (Exception e){
			e.printStackTrace();
			
		}
		

		return flag;
		
	}
	
	
}
