package restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import restaurant.entity.Person;

import java.util.List;


public interface PersonRepositoryJPA extends JpaRepository<Person, Long> {

}
