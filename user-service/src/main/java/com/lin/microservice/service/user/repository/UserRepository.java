package com.lin.microservice.service.user.repository;

import org.springframework.stereotype.Repository;

import com.lin.common.core.EntityRepository;
import com.lin.microservice.service.user.entity.User;

@Repository
public interface UserRepository extends EntityRepository<User, Long> {

	User findByUserName(String userName);
	
	User findByEmail(String email);

}
