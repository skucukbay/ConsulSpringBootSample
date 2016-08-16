package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.PostConstruct;
@EnableDiscoveryClient
@SpringBootApplication
public class DemoProjectApplication /*extends SpringBootServletInitializer*/{



	private static Logger logger = LoggerFactory.getLogger(DemoProjectApplication.class);


	@PostConstruct
	public void logSomething() {
		logger.debug("Sample Debug Message");
	}

	public static void main(String[] args) {


		SpringApplication.run(DemoProjectApplication.class, args);

	}
}
