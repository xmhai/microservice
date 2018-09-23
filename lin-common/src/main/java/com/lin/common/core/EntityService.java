package com.lin.common.core;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

public interface EntityService<T, ID extends Serializable, R extends EntityRepository<T, ID>> {

	T save(T entity) throws Exception;

	Iterable<T> save(Iterable<T> entities) throws Exception;

	T findById(ID id);

	boolean exists(ID id);

	List<T> findAll();

	List<T> findAll(Iterable<ID> ids);

	List<T> findAll(Sort sort);

	long count();

	void delete(ID id);

	void delete(T entity);

	void delete(Iterable<T> entities);

	void deleteAll();

	R getEntityRepository();

}
