package com.lin.common.core;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Transactional(readOnly = true)
public abstract class EntityServiceSupport<T, ID extends Serializable, R extends EntityRepository<T, ID>> implements EntityService<T, ID, R> {
	private static final Logger logger = LoggerFactory.getLogger(EntityServiceSupport.class);
	
	@Autowired
	protected R entityRepository;

	protected Class<T> entityClass;

	protected EntityServiceSupport() {
	}

	@SuppressWarnings("unchecked")
	public Class<T> getEntityClass() {
		if (entityClass == null) {
			ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
			entityClass = (Class<T>) (superclass).getActualTypeArguments()[0];
		}
		return entityClass;
	}

	public R getEntityRepository() {
		return entityRepository;
	}

	@Override
	@Transactional
	public T save(T entity) throws Exception {
		Assert.notNull(entity, "The value must not be null");
		beforeSave(entity);
		return entityRepository.save(entity);
	}

	protected void beforeSave(T entity) {

	}

	@Override
	@Transactional
	public Iterable<T> save(Iterable<T> entities) {
		Assert.notNull(entities, "The value must not be null");
		for (T entity : entities) {
			beforeSave(entity);
		}

		return entityRepository.saveAll(entities);
	}

	@Override
	public T findById(ID id) {
		Assert.notNull(id, "The value must not be null");
		Optional<T> entity = entityRepository.findById(id);
		return entity.isPresent() ? entity.get() : null;
	}

	@Override
	public boolean exists(ID id) {
		Assert.notNull(id, "The value must not be null");
		return entityRepository.existsById(id);
	}

	@Override
	public List<T> findAll() {
		return entityRepository.findAll();
	}

	@Override
	public List<T> findAll(Iterable<ID> ids) {
		Assert.notNull(ids, "The value must not be null");
		return entityRepository.findAllById(ids);
	}

	@Override
	public List<T> findAll(Sort sort) {
		Assert.notNull(sort, "The value must not be null");
		return entityRepository.findAll(sort);
	}

	@Override
	public long count() {
		return entityRepository.count();
	}

	@Override
	@Transactional
	public void delete(ID id) {
		Assert.notNull(id, "The value must not be null");
		delete(findById(id));
	}

	@Override
	@Transactional
	public void delete(T entity) {
		Assert.notNull(entity, "The value must not be null");
		entityRepository.delete(entity);
	}

	@Override
	@Transactional
	public void delete(Iterable<T> entities) {
		Assert.notNull(entities, "The value must not be null");
		for (T entity : entities) {
			delete(entity);
		}
	}

	@Override
	@Transactional
	public void deleteAll() {
		getEntityRepository().deleteAll();
	}
}
