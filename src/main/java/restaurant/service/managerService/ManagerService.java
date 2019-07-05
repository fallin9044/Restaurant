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

import restaurant.entity.OrderStream;
import restaurant.entity.Person;
import restaurant.repository.OrderStreamRepository;
import restaurant.repository.PersonRepository;
import restaurant.utils.WebUtils;

@Service
public class ManagerService {

	@Autowired
	PersonRepository personDAO;

<<<<<<< HEAD
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

	public Object getPerson() {
		// person
		List<Person> persons = personDAO.getWorkingPerson();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("persons", persons);
		// get now
		Date day = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf.format(day);
		StringTokenizer st = new StringTokenizer(dateNowStr);
		String now = st.nextToken();
		// time&if attending
		List<String> dates = new ArrayList<>();
		List<String> times = new ArrayList<>();
		List<String> ifAttending = new ArrayList<>();
		for (Person person : persons) {
			String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(person.getPersonTime());
			StringTokenizer stringTokenizer = new StringTokenizer(str);
			String date = stringTokenizer.nextToken();
			dates.add(date);
			if (date.equals(now)) {
				ifAttending.add("出勤");
			} else
				ifAttending.add("缺勤");
			times.add(stringTokenizer.nextToken());
=======
	
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
>>>>>>> 2404765de59fc1d7465b3ac67d7d370c7ec3a8b9
		}
		map.put("dates", dates);
		map.put("times", times);
		
		map.put("ifAttending", ifAttending);
		return WebUtils.setModelAndView("manage_check", map);
	}
}
