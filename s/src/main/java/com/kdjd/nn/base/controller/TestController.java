package com.kdjd.nn.base.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdjd.nn.base.aop.annotation.Tailor;
import com.kdjd.nn.base.aop.annotation.Tailors;
import com.kdjd.nn.base.entity.Result;
import com.kdjd.nn.base.entity.User;
import com.kdjd.nn.base.service.UserService;

@RestController
@RequestMapping(value = "/main")
public class TestController {

	@Autowired
	UserService userService;

	protected static Log log = LogFactory.getLog(TestController.class);

	@RequestMapping(value = "/")
	@Tailors(tailors = { @Tailor(properties = { "password" }, target = User.class) })
	public Object main() {
		log.info("request at " + new Date());
		List<User> users = userService.findAll(User.class);
		return Result.successResult(users, "获取成功");
	}

	@RequestMapping(value = "/login")
	public Object login(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie("kdjd", "kdjd-test");

		response.addCookie(cookie);

		return "login success";
	}

	@RequestMapping(value = "/check")
	public Object check(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookies.length <= 0) {
			return "no cookie";
		} else {
			for (Cookie cookie : cookies) {
				if (cookie != null && cookie.getName().equals("kdjd")) {
					System.out.println(cookie.getName());
					System.out.println(cookie.getValue());
				}
			}
			return "check the cookie";
		}
	}

}
