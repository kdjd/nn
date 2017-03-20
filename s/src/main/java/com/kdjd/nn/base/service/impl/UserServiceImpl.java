package com.kdjd.nn.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdjd.nn.base.dao.UserDao;
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
}
