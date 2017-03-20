package com.kdjd.nn.base.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kdjd.nn.base.BaseTestCase;
import com.kdjd.nn.base.entity.User;
import com.kdjd.nn.base.tools.MD5;

public class UserServiceTest extends BaseTestCase {

	@Autowired
	UserService service;

	@Test
	public void testRegister() {
		User user = new User();
		user.setName("kdjd");
		user.setEmail("abc@qq.com");
		user.setPassword("123456");

		user = service.register(user);

		assertEquals(user.getName(), "kdjd");
		assertEquals(user.getPassword(), MD5.md5("123456"));
		
	}

}
