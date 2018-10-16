package com.lin.microservice.cloud.edge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableWebMvc
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("com.lin")
@EnableJpaRepositories("com.lin")
@EntityScan("com.lin")
public class EdgeServerApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EdgeServerApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(EdgeServerApplication.class, args);
	}
}
