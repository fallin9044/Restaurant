package restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restaurant.entity.Reserves;

public interface ReservesRepositoryJPA extends JpaRepository<Reserves, Long> {
	
	@Query(value = "select * from reserves where date_add(now(),INTERVAL 2 HOUR) > reserve_time and date_sub(now(),INTERVAL 2 HOUR) < reserve_time",nativeQuery = true)
	List<Reserves> twoHourInterval();
	
}
