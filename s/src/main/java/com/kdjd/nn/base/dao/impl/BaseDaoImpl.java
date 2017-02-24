package com.kdjd.nn.base.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.kdjd.nn.base.dao.BaseDao;
import com.kdjd.nn.base.entity.BaseEntity;

@Repository
public class BaseDaoImpl implements BaseDao {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public <T extends BaseEntity> void insert(T entity) {
		entity.setCreatedAt(new Date());
		this.mongoTemplate.insert(entity);
	}

	@Override
	public <T extends BaseEntity> T findById(String id, Class<T> entityClass) {
		return this.mongoTemplate.findById(id, entityClass);
	}

	@Override
	public <T extends BaseEntity> List<T> findByQuery(Query query, Class<T> entityClass) {
		return this.mongoTemplate.find(query, entityClass);
	}

	// @SuppressWarnings("unchecked")
	// @Override
	// public <T extends BaseEntity> T update(T entity, Update update) {
	// update.set("updatedAt", new Date());
	// Query query = new Query(Criteria.where("id").is(entity.getId()));
	// entity = (T) this.mongoTemplate.findAndModify(query, update, new
	// FindAndModifyOptions().returnNew(true),
	// entity.getClass());
	// return entity;
	// }

	@SuppressWarnings("unchecked")
	@Override
	public <T extends BaseEntity> T update(T entity) {
		this.mongoTemplate.save(entity);
		entity = (T) findById(entity.getId(), entity.getClass());
		return entity;
	}

	@Override
	public <T extends BaseEntity> T findAndModify(Query query, Update update, Class<T> entityClass) {
		update.set("updatedAt", new Date());
		T t = this.mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), entityClass);
		return t;
	}

	@Override
	public <T extends BaseEntity> List<T> findAll(Class<T> entityClass) {
		return this.mongoTemplate.findAll(entityClass);
	}

	@Override
	public <T extends BaseEntity> void delete(T entity) {
		this.mongoTemplate.remove(entity);

	}

	@Override
	public <T extends BaseEntity> void deleteById(String id, Class<T> entityClass) {
		Query query = new Query(Criteria.where("id").is(id));
		this.mongoTemplate.findAllAndRemove(query, entityClass);
	}

	@Override
	public <T extends BaseEntity> void deleteAll(Class<T> entityClass) {
		this.mongoTemplate.dropCollection(entityClass);
	}

}
