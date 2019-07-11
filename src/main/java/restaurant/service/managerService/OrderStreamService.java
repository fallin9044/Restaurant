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
public class OrderStreamService {

	
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
		int count = 0;
		for(OrderStream os:orders){
			count += os.getTotal();
		}
		WebUtils.getObjectList(orders, orderList, start, 5);
		int[] info = WebUtils.getPagingInfo(start, 5, orders.size());
		map.put("streamTotal", count);
		map.put("next", info[0]);
		map.put("pre", info[1]);
		map.put("last", info[2]);
		map.put("count", info[3]);
		map.put("total", info[4]);
		map.put("orderStream", orderList);
		map.put("detail", detail);
		return WebUtils.setModelAndView("manage_order", map);
	}

	
}
