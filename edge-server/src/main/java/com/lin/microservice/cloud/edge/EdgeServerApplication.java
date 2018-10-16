package com.lin.microservice.cloud.edge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@EnableWebMvc
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class EdgeServerApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EdgeServerApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(EdgeServerApplication.class, args);
	}
}
