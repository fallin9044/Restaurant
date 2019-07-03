package restaurant.service.waiterService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;

import restaurant.entity.Dining;
import restaurant.entity.Person;
import restaurant.repository.DiningRepository;
import restaurant.repository.PersonRepository;
import restaurant.utils.WebUtils;

import java.util.Optional;


@Service
public class WaiterService  {
//public class RawMaterialService extends BaseService {

    
    @Autowired
    DiningRepository diningDAO;




	public Object loadTableStatus() {
		Map<String,Object> map= new HashMap<String, Object>();
		map.put("tablestatus",diningDAO.findAll());
		return 	WebUtils.setModelAndView("tableStatus", map);
	}




}
