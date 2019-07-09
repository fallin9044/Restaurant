package restaurant.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import restaurant.entity.Dining;


public interface DiningRepositoryJPA extends JpaRepository<Dining, Long> {

	@Modifying
	@Query(value = "update dining set table_state=2,start_time=now() where table_id = :id",nativeQuery=true)
	public void takeTable(@Param("id") long id);
	
	@Modifying
	@Query(value = "update Dining b set b.tableState=0 where b.tableId = :id")
	public void releaseTable(@Param("id") long id);
	
	
	@Query(value = "select * from dining where table_id = :tableId and (start_time = null or date_sub(:time,INTERVAL 2 HOUR) < start_time)" ,nativeQuery = true)
	List<Dining> isAbleReserve(@Param("tableId")long tableId, @Param(value = "time")Date reserveTime);
	
}
