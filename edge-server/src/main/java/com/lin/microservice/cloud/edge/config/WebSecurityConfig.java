package com.lin.microservice.cloud.edge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.lin.microservice.cloud.edge.security.ApiAuthenticationEntryPoint;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
		http
			.exceptionHandling()
	            //Actually Spring already configures default AuthenticationEntryPoint - LoginUrlAuthenticationEntryPoint
	            //This one is REST-specific addition to default one, that is based on PathRequest
	            .defaultAuthenticationEntryPointFor(getRestAuthenticationEntryPoint(), new AntPathRequestMatcher("/api/**"))
				.and()
			.authorizeRequests()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/webjars/**").permitAll()
				.antMatchers("/login", "/home", "/login-error", "/").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
	            //.loginProcessingUrl("/j_spring_security_check")
	            //.usernameParameter("j_username")
	            //.passwordParameter("j_password")
				.defaultSuccessUrl("/home")
	            .failureUrl("/login-error")
				.permitAll()
				.and()
			.logout()
				.logoutSuccessUrl("/home")
				.permitAll();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private AuthenticationEntryPoint getRestAuthenticationEntryPoint() {
        return new ApiAuthenticationEntryPoint();
    }
}
