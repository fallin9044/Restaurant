package restaurant.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import restaurant.entity.OrderStream;

public interface OrderStreamRepositoryJPA extends JpaRepository<OrderStream, Long> {

	@Query(value = "select b from OrderStream b where to_days(b.orderTime) = to_days(now())")
	public List<OrderStream> findToday();

	@Query(value = "select * from order_stream where date_sub(CURDATE(),INTERVAL 7 DAY) <= DATE(order_time)",nativeQuery=true)
	public List<OrderStream> findWeek();
	
	@Query(value = "select * from order_stream where date_sub(CURDATE(),INTERVAL 30 DAY) <= DATE(order_time)",nativeQuery=true)
	public List<OrderStream> findMonth();
	
}
