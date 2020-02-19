package com.dicegame.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DicegameApplication {

	public static void main(String[] args) {
		SpringApplication.run(DicegameApplication.class, args);
	}

}