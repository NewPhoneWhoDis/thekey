package com.the.key;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KeyApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeyApplication.class, args);
	}

}
