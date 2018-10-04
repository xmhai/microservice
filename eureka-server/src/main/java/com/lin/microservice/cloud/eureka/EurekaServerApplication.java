package com.lin.microservice.cloud.eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication extends SpringBootServletInitializer {
	private static Logger logger = LoggerFactory.getLogger(EurekaServerApplication.class);

	public static void main(String[] args) {
		logger.info("*** Eureka Server is starting up...");
		SpringApplication.run(EurekaServerApplication.class, args);
	}


	// below code are for JBoss deployment
	
	// Override the configure method and point your sources to this same class (it’s the entry point of your application). 
	// Use the passed argument, SpringApplicationBuilder. 
	// If you don’t do that, your different components won’t be detected, won’t be injected in the context so your application won’t work.
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EurekaServerApplication.class);
    }
}
