package com.apirest.products;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApirestApplication {
	static Logger LOGGER = LoggerFactory.getLogger("ApirestApplicationLogger");


	public static void main(String[] args) {
		LOGGER.info("Initialing application.");
		SpringApplication.run(ApirestApplication.class, args);
	}

}
