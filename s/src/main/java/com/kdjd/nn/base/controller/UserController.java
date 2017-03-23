package com.kdjd.nn.base.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kdjd.nn.base.dto.LoginInfo;
import com.kdjd.nn.base.entity.Result;
import com.kdjd.nn.base.entity.User;
import com.kdjd.nn.base.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {

	protected static Log log = LogFactory.getLog(UserController.class);

	@Autowired
	UserService service;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Object register(@RequestBody User user) {
		user = service.register(user);

		log.info("用户注册成功,用户名:" + user.getName() + ",用户邮箱:" + user.getEmail());
		return Result.successResult(user, "注册成功");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Object login(String login, String password) {
		LoginInfo info = service.login(login, password);

		log.info("用户登录,登录名:" + login);
		return Result.successResult(info, "登录");
	}

}