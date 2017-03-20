package com.kdjd.nn.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.kdjd.nn.base.dao.UserDao;
import com.kdjd.nn.base.entity.BaseEntity;
import com.kdjd.nn.base.service.BaseService;

public class BaseServiceImpl implements BaseService {

	@Autowired
	UserDao dao;

	@Override
	public <T extends BaseEntity> T insert(T entity) {
		dao.insert(entity);
		return entity;
	}

	@Override
	public <T extends BaseEntity> T findById(String id, Class<T> entityClass) {
		T t = dao.findById(id, entityClass);
		return t;
	}

	@Override
	public <T extends BaseEntity> List<T> findByQuery(Query query, Class<T> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends BaseEntity> T update(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends BaseEntity> T findAndModify(Query query, Update update, Class<T> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends BaseEntity> List<T> findAll(Class<T> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends BaseEntity> void delete(T entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T extends BaseEntity> void deleteById(String id, Class<T> entityClass) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T extends BaseEntity> void deleteAll(Class<T> entityClass) {
		// TODO Auto-generated method stub

	}

}
