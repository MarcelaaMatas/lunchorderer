package com.example.lunchordererapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LunchOrdererApplication {

	public static void main(String[] args) {
		SpringApplication.run(LunchOrdererApplication.class, args);
	}

}
