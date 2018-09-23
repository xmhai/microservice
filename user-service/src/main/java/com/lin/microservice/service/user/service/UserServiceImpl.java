package com.lin.microservice.service.user.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lin.common.core.EntityServiceSupport;
import com.lin.microservice.service.user.entity.User;
import com.lin.microservice.service.user.repository.UserRepository;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl extends EntityServiceSupport<User, Long, UserRepository> implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User findByUsername(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	@Transactional
	public User save(User user) throws Exception {
		User u = userRepository.findByUserName(user.getUserName());
		if (u != null) {
			logger.error("There is already an existing account with username: " + user.getUserName());
			throw new Exception("There is already an existing account with username: " + user.getUserName());
		}
		
		u = userRepository.findByEmail(user.getEmail());
		if (u != null) {
			logger.error("There is already an existing account with email: " + user.getEmail());
			return null;
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
}
