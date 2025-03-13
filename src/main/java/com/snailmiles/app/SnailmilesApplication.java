package com.snailmiles.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableScheduling
public class SnailmilesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnailmilesApplication.class, args);
	}

}
