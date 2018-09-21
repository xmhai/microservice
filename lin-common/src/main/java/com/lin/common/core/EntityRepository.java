package com.lin.common.core;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EntityRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	Optional<T> findById(ID id);

}
