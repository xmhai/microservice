package com.lin.microservice.service.user.service;

import org.springframework.stereotype.Service;

import com.lin.common.core.EntityService;
import com.lin.microservice.service.user.entity.User;
import com.lin.microservice.service.user.repository.UserRepository;

@Service
public interface UserService  extends EntityService<User, Long, UserRepository> {

	User findByUsername(String username);

}
