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
public class TableAndReserveService {


	
	@Autowired
	ReservesRepository reservesDAO;
	
	@Autowired
	DiningRepository diningDAO;
	
	
	@Transactional
	public Object loadTables(String root) {
		Map<String,Object> map= new HashMap<String, Object>();
		reservesDAO.deleteOverTimeReserve();
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
	

	@Transactional
	public Object loadReserves(String managereserve, long tableId) {
		Map<String,Object> map= new HashMap<String, Object>();
		reservesDAO.deleteOverTimeReserve();
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
			if(reserveTime.before(new Date()))return -1;
			System.out.println(time);
			Timestamp timestamp=new Timestamp(reserveTime.getTime());
			List<Reserves> res=reservesDAO.twoHourInterval(tableId,reserveTime);
			if(res.size()>0)return 2;
			List<Dining> tabs = diningDAO.isAbleReserve(tableId,reserveTime);
			if(tabs.size()>0)return 0;
			reservesDAO.saveAndFlush(new Reserves(tableId, timestamp, tele));
			if(diningDAO.findById(tableId).get().getTableState()!=2) diningDAO.takeTableWithReserve(tableId);
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	@Transactional
	public Object deleteReserve(long id) {
		try{
			long tableId=reservesDAO.findById(id).get().getTableId();
			int tableState = diningDAO.findById(tableId).get().getTableState();
			reservesDAO.deleteById(id);
			if(reservesDAO.findByTableId(tableId).size()==0&&tableState!=2) {
				diningDAO.releaseTableWithNoReserve(tableId);
			}
		}catch(Exception e) {
			return 0;
		}
		return 1;
	}
	
	@Transactional
	public Object loadSearchedTable(String managereserve, String time) {
		SimpleDateFormat x=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List<Dining> tablesDinings=diningDAO.findAll();
		List<Dining> tablesres=new ArrayList<Dining>();
		reservesDAO.deleteOverTimeReserve();
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
