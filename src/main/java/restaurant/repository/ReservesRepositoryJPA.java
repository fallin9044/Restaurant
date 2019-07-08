package restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restaurant.entity.Reserves;

public interface ReservesRepositoryJPA extends JpaRepository<Reserves, Long> {

	
	
}
