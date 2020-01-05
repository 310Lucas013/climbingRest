package com.fon.luc.climbingRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ClimbingRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClimbingRestApplication.class, args);
	}

}
