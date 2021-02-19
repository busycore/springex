package com.challenge.simpleApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SimpleApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleApiApplication.class, args);
	}

}
