package restaurant.service.managerService;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import restaurant.entity.Person;
import restaurant.repository.PersonRepository;
import restaurant.utils.WebUtils;
@Service
public class ManagerService {
	
	@Autowired
	PersonRepository personDAO;
	
	
	public Object getPerson(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("persons",personDAO.findAll());
		return 	WebUtils.setModelAndView("manage_check", map);
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
