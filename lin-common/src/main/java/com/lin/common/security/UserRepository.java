package com.lin.common.security;

import org.springframework.stereotype.Repository;

import com.lin.common.core.EntityRepository;

@Repository
public interface UserRepository extends EntityRepository<User, Long> {

	User findByUserName(String userName);

}
