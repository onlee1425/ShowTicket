package zb.Team.showticket;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@RequiredArgsConstructor
@EnableJpaRepositories
@EnableJpaAuditing
public class ShowticketApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShowticketApplication.class, args);
	}

}
