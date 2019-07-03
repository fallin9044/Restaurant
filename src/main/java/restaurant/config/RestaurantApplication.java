package restaurant.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author 田付山
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
@ComponentScan(basePackages = {"restaurant.*"})
@EntityScan({"restaurant.entity"})
@EnableJpaRepositories("restaurant.repository")
public class RestaurantApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    	return builder.sources(RestaurantApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(RestaurantApplication.class, args);
    }
}
