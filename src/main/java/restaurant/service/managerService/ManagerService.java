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

import restaurant.entity.Person;
import restaurant.repository.PersonRepository;
import restaurant.utils.WebUtils;
@Service
public class ManagerService {
	
	@Autowired
	PersonRepository personDAO;

	
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
		return 	WebUtils.setModelAndView("manage_check", map);
	}
}
