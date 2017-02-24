package com.kdjd.nn.base.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.kdjd.nn.base.BaseTestCase;
import com.kdjd.nn.base.entity.BaseEntity;

public class BaseDaoTest extends BaseTestCase {

	@Autowired
	BaseDao dao;

	private BaseEntity a = null;

	@Before
	public void ready() {
		dao.deleteAll(BaseEntity.class);

		a = new BaseEntity();
		a.setDescription("description");
		dao.insert(a);
	}

	@Test
	public void testInsert() {
		BaseEntity baseEntity = new BaseEntity();
		baseEntity.setDescription("111111");
		dao.insert(baseEntity);

		List<BaseEntity> entities = dao.findAll(BaseEntity.class);
		assertEquals(entities.size(), 2);
	}

	@Test
	public void testFindById() {
		BaseEntity baseEntity = dao.findById(a.getId(), BaseEntity.class);

		assertEquals(baseEntity.getDescription(), "description");
	};

	@Test
	public void testFindByQuery() {
		Query query = new Query(Criteria.where("description").is("description"));
		List<BaseEntity> entities = dao.findByQuery(query, BaseEntity.class);

		assertEquals(entities.size(), 1);
		assertEquals(entities.get(0).getDescription(), "description");
	};

	// @Test
	// public void update() {
	// Update update = new Update();
	// update.set("description", "test");
	// a = dao.update(a, update);
	//
	// assertEquals(a.getDescription(), "test");
	// BaseEntity baseEntity = dao.findById(a.getId(), BaseEntity.class);
	// assertEquals(baseEntity.getDescription(), "test");
	// };

	@Test
	public void testUpdat() {
		a.setDescription("test");
		a = dao.update(a);

		assertEquals(a.getDescription(), "test");
		BaseEntity baseEntity = dao.findById(a.getId(), BaseEntity.class);
		assertEquals(baseEntity.getDescription(), "test");
	}

	public void testFindAndModify() {
	};

	public void findAll() {
	};

	public void delete() {
	};

	public void deleteById() {
	};

	public void deleteAll() {
	};

}
