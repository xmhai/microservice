package com.lin.microservice.cloud.edge.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lin.microservice.cloud.edge.security.AuthenticationSuccessHandler;
import com.lin.microservice.cloud.edge.security.JwtHelper;
import com.lin.microservice.cloud.edge.security.JwtTokenAuthenticationFilter;
import com.lin.microservice.cloud.edge.security.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private JwtHelper jwtHelper;
 
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
    @Autowired
    private MyUserDetailsService userDetailsService;

    @Bean
    public JwtTokenAuthenticationFilter jwtAuthenticationTokenFilter() throws Exception {
        return new JwtTokenAuthenticationFilter(jwtHelper);
    }
	
	@Override
  	protected void configure(HttpSecurity http) throws Exception {
		http
			// make sure we use stateless session; session won't be used to store user's state.
			//.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			//.and()
			// handle an authorized attempts
			.exceptionHandling()
				.authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
				.and()
			.addFilterAfter(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests()
				.antMatchers("/", "/home").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login").permitAll()
				.successHandler(authenticationSuccessHandler)
        		.and()
        	.logout().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authProvider;
    }
}