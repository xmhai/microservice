package com.lin.microservice.cloud.edge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableZuulProxy
@EnableResourceServer
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
