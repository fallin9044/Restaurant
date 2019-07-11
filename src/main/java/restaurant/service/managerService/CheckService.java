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
public class CheckService {

	@Autowired
	PersonRepository personDAO;
	
	
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
	
	
	
}
