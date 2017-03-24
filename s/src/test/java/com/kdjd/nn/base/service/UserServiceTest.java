package com.kdjd.nn.base.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kdjd.nn.base.BaseTestCase;
import com.kdjd.nn.base.dto.LoginInfo;
import com.kdjd.nn.base.dto.LoginInfo.LoginResult;
import com.kdjd.nn.base.entity.User;
import com.kdjd.nn.base.tools.MD5;

public class UserServiceTest extends BaseTestCase {

	@Autowired
	UserService service;

	private User user = new User();

	@Before
	public void reday() {
		service.deleteAll(User.class);

		user.setName("kdjd");
		user.setEmail("abc@qq.com");
		user.setPassword("123456");

		user = service.register(user);
	}

	@Test
	public void testRegister() {
		assertEquals(user.getName(), "kdjd");
		assertEquals(user.getPassword(), MD5.md5("123456"));

	}

	@Test
	public void testLogin() {
		LoginInfo info = service.login("kdjd", "123456");
		assertEquals(info.getResult(), LoginResult.success);
		assertEquals(info.getUser().getId(), user.getId());

		info = service.login("kdjd3", "123456");
		assertEquals(info.getResult(), LoginResult.noUser);
		assertEquals(info.getUser(), null);

		info = service.login("abc@qq.com", "123456");
		assertEquals(info.getResult(), LoginResult.success);
		assertEquals(info.getUser().getId(), user.getId());

		info = service.login("abcd@qq.com", "123456");
		assertEquals(info.getResult(), LoginResult.noUser);
		assertEquals(info.getUser(), null);

		info = service.login("abc@qq.com", "1234567");
		assertEquals(info.getResult(), LoginResult.wrongPassword);
		assertEquals(info.getUser(), null);

		info = service.login("kdjd", "1234567");
		assertEquals(info.getResult(), LoginResult.wrongPassword);
		assertEquals(info.getUser(), null);

	}

}
