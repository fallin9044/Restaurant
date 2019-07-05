package restaurant.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restaurant.entity.Person;
import restaurant.repository.PersonRepository;

@Service
public class LoggingValidationService {

	@Autowired
    PersonRepository personRepository;
	
	@Transactional
	public int findPerson(HttpSession session,String name,String password){
		//数据库查询用户
		List<Person> persons = personRepository.findByNamePassword(name, password);
		if(persons.isEmpty()){
			return -1;
		}
		//用户权限
		int result = persons.get(0).getAuthority();
		//设置session
		session.setAttribute("personId", persons.get(0).getId());
		//设置权限
		session.setAttribute("authority", persons.get(0).getAuthority());
//		if(result==2){
//			//服务员设置工作状态
//			personRepository.waiterIsWork(persons.get(0).getId());
//		}
		//返回用户权限
		return result;
	}
	
}
