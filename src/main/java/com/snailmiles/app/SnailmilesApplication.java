package com.snailmiles.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SnailmilesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnailmilesApplication.class, args);
	}

}
