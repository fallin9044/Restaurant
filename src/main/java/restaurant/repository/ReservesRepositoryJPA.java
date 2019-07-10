package restaurant.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import restaurant.entity.Reserves;

public interface ReservesRepositoryJPA extends JpaRepository<Reserves, Long> {
	
	@Query(value = "select * from reserves where date_add(now(),INTERVAL 2 HOUR) > reserve_time and date_sub(now(),INTERVAL 2 HOUR) < reserve_time",nativeQuery = true)
	List<Reserves> twoHourInterval();
	
	List<Reserves> findByTableId(long tableId);
	
	
	@Query(value = "select * from reserves where table_id = :tableId and date_add(:time,INTERVAL 2 HOUR) > reserve_time and date_sub(:time,INTERVAL 2 HOUR) < reserve_time",nativeQuery = true)
	List<Reserves> twoHourInterval(@Param(value = "tableId")long tableId, @Param(value = "time") Date reserveTime);
	
	@Modifying
	@Query(value = "delete from reserves where date_sub(now(),INTERVAL 30 MINUTE) > reserve_time",nativeQuery = true)
	void deleteOverTimeReserve();

}
