package com.lin.microservice.cloud.edge.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
		http.requestMatchers()
        	.antMatchers("/login", "/oauth/**")
			.and()
			.authorizeRequests().anyRequest().permitAll()
			.and()
			//.formLogin().permitAll()
			//.and()
			//.logout().permitAll()
			//.and()
			.csrf().disable();
    }
}
