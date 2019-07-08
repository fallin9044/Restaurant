package restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import restaurant.entity.Dining;


public interface DiningRepositoryJPA extends JpaRepository<Dining, Long> {

	@Modifying
	@Query(value = "update Dining b set b.tableState=2 where b.tableId = :id")
	public void takeTable(@Param("id") long id);
	
	
}
