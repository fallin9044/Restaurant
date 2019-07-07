package restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import restaurant.entity.Menu;
import restaurant.entity.Person;

import java.util.List;


public interface MenuRepositoryJPA extends JpaRepository<Menu, Long> {

	List<Menu> findByTableId(long id);
	
	List<Menu> findByTableIdAndDishId(long tableId, long dishId);
	
	void deleteByTableId(long tableId);
	
	@Modifying
	@Query(value = "Update Menu b set b.dishState = :state where b.id = :id")
	void changeMenuState(@Param("id")long menuId,@Param("state") int i);
}
