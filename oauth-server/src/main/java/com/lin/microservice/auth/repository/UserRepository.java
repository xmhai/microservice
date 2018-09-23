package com.lin.microservice.auth.repository;

import org.springframework.stereotype.Repository;

import com.lin.common.core.EntityRepository;
import com.lin.microservice.auth.entity.User;

@Repository
public interface UserRepository extends EntityRepository<User, Long> {

	User findByUserName(String userName);

}
