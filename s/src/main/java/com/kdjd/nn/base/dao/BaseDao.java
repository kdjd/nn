package com.kdjd.nn.base.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.kdjd.nn.base.entity.BaseEntity;

public interface BaseDao {

	public <T extends BaseEntity> void insert(T entity);

	public <T extends BaseEntity> T findById(String id, Class<T> entityClass);

	public <T extends BaseEntity> List<T> findByQuery(Query query, Class<T> entityClass);

	// public <T extends BaseEntity> T update(T entity, Update update);

	public <T extends BaseEntity> T update(T entity);

	public <T extends BaseEntity> T findAndModify(Query query, Update update, Class<T> entityClass);

	public <T extends BaseEntity> List<T> findAll(Class<T> entityClass);

	public <T extends BaseEntity> void delete(T entity);

	public <T extends BaseEntity> void deleteById(String id, Class<T> entityClass);

	public <T extends BaseEntity> void deleteAll(Class<T> entityClass);

}
