package com.js.springbootcrmbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableAsync
public class SpringBootCrmBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCrmBackendApplication.class, args);
	}

}
