package restaurant.repository;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

@Component
@EnableJpaAuditing
public interface ReservesRepository extends ReservesRepositoryJPA {

}
