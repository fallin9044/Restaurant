package restaurant.service.waiterService;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restaurant.entity.Person;
import restaurant.repository.PersonRepository;

import java.util.Optional;


@Service
public class PersonService  {
//public class RawMaterialService extends BaseService {

    @Autowired
    PersonRepository dao;



    
    public List<Person> findAll() {
        return dao.findAll();
    }




}
