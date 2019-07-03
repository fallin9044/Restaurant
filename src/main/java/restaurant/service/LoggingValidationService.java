package restaurant.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restaurant.entity.Person;
import restaurant.repository.PersonRepository;
import restaurant.utils.WebUtils;

@Service
public class LoggingValidationService {

	@Autowired
    PersonRepository personRepository;
	
	@Transactional
	public int findPerson(String name,String password){
		//数据库查询用户
		List<Person> persons = personRepository.findByNamePassword(name, password);
		if(persons.isEmpty()){
			return -1;
		}
		//用户权限
		int result = persons.get(0).getAuthority();
		//设置session
		WebUtils.setSessionAttribute("personId", persons.get(0).getId());
		//设置权限
		WebUtils.setSessionAttribute("authority", persons.get(0).getAuthority());
		if(result==2){
			//服务员设置工作状态
			personRepository.waiterIsWork(persons.get(0).getId());
		}
		//返回用户权限
		return result;
	}
	
}
