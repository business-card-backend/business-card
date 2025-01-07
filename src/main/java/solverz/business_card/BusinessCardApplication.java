package solverz.business_card;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableFeignClients
public class BusinessCardApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusinessCardApplication.class, args);
	}

}
