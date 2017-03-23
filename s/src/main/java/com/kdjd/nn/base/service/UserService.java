package com.kdjd.nn.base.service;

import com.kdjd.nn.base.dto.LoginInfo;
import com.kdjd.nn.base.entity.User;

public interface UserService extends BaseService {

	public User register(User user);

	public LoginInfo login(String login, String password);
}
