package com.lin.microservice.cloud.edge.security;

import org.springframework.stereotype.Repository;

import com.lin.common.core.EntityRepository;

@Repository
public interface UserRepository extends EntityRepository<User, Long> {

	User findByUserName(String userName);

}
