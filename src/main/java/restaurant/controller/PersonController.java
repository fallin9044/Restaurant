package restaurant.controller;

import java.io.Console;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import restaurant.entity.Person;
import restaurant.service.PersonService;

/**
*
* @author tianfushan (slndig.common.cn@siemens.com)
*/
@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService waiterService;



    @ResponseBody
    @GetMapping(path = "/findAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<List<Person>> findAll() {
        try {
        	List<Person> MaterialPile = waiterService.findAll();
            return new ResponseEntity<>(MaterialPile, HttpStatus.OK);
        } catch (Exception ex) {
        	ex.printStackTrace();
            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<>(null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping(value="/hello")
	public String test(){
		return "hello";
	}


    
}
