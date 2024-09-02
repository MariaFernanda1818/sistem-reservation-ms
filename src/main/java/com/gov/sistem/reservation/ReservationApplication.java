package com.gov.sistem.reservation;

import com.gov.sistem.reservation.login.util.configuration.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Import(SecurityConfig.class)
@EntityScan("com.gov.sistem.reservation.commons")
@EnableJpaRepositories("com.gov.sistem.reservation")
public class ReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationApplication.class, args);
	}

}
