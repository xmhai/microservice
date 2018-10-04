package com.lin.microservice.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
public class UserServiceApplication extends SpringBootServletInitializer {
	private static Logger logger = LoggerFactory.getLogger(UserServiceApplication.class);

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		logger.info("*** User Service is starting up...");
		SpringApplication.run(UserServiceApplication.class, args);
	}
	
	// below code are for WAR deployment
	
	// Override the configure method and point your sources to this same class (it’s the entry point of your application). 
	// Use the passed argument, SpringApplicationBuilder. 
	// If you don’t do that, your different components won’t be detected, won’t be injected in the context so your application won’t work.
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(UserServiceApplication.class);
    }
}
