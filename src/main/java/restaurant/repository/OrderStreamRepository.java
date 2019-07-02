package restaurant.repository;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

/**
*
* @author tianfushan (slndig.common.cn@siemens.com)
*/
@Component
@EnableJpaAuditing
public interface OrderStreamRepository extends PersonRepositoryJPA {

}
