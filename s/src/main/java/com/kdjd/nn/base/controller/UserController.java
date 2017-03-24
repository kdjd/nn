package com.kdjd.nn.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kdjd.nn.base.aop.annotation.Tailor;
import com.kdjd.nn.base.aop.annotation.Tailors;
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

		log.info("�û�ע��ɹ�,�û���:" + user.getName() + ",�û�����:" + user.getEmail());
		return Result.successResult(user, "ע��ɹ�");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@Tailors(tailors = { @Tailor(properties = { "" }, target = LoginInfo.class),
			@Tailor(properties = { "password" }, target = User.class) })
	public Object login(String login, String password, HttpServletRequest request, HttpServletResponse response) {
		LoginInfo info = service.login(login, password);

		log.info("�û���¼,��¼��:" + login + "��¼���:" + info.getResult());
		return Result.successResult(info, "��¼");
	}

}
