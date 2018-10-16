package com.lin.microservice.cloud.edge.security;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService extends JdbcDaoImpl {
	@Autowired
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	@Override
	@Value("select username,password,true as enabled from idm_user where username = ?")
	public void setUsersByUsernameQuery(String usersByUsernameQueryString) {
		super.setUsersByUsernameQuery(usersByUsernameQueryString);
	}
	
	@Override
	@Value("select username,role from idm_user u, idm_user_role ur, idm_role r where u.id=ur.user_id and ur.role_id=r.id and username =?")
	public void setAuthoritiesByUsernameQuery(String queryString) {
		super.setAuthoritiesByUsernameQuery(queryString);
	}
}