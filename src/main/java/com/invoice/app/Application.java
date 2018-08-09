package com.invoice.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * The Rest service initializer using the Spring Boot Application
 * This class using spring boot Initialize the generateInvoice rest service. 
 * 
 * @author mohansar0
 *
 */
@SpringBootApplication
@ComponentScan({ "com.invoice" })
public class Application {

	private static Logger LOG = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		LOG.info("STARTING - INVOICE APPLICATION");
		SpringApplication.run(Application.class, args);
	}

}
