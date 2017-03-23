package com.kdjd.nn.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.kdjd.nn.base.dao.UserDao;
import com.kdjd.nn.base.dto.LoginInfo;
import com.kdjd.nn.base.dto.LoginInfo.LoginResult;
import com.kdjd.nn.base.entity.User;
import com.kdjd.nn.base.service.UserService;
import com.kdjd.nn.base.tools.MD5;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Autowired
	UserDao dao;

	@Override
	public User register(User user) {
		user.setPassword(MD5.md5(user.getPassword()));
		dao.insert(user);
		return user;
	}

	@Override
	public LoginInfo login(String login, String password) {
		Query query = new Query(
				new Criteria().orOperator(Criteria.where("email").is(login), Criteria.where("name").is(login)));
		List<User> users = dao.findByQuery(query, User.class);
		if (users.isEmpty()) {
			return new LoginInfo(false, LoginResult.noUser);
		}
		User user = users.get(0);
		if (user.getPassword().equals(MD5.md5(password))) {
			return new LoginInfo(user.getId());
		} else {
			return new LoginInfo(false, LoginResult.wrongPassword);
		}

	}
}
