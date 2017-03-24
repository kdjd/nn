package com.kdjd.nn.base.dto;

import com.kdjd.nn.base.entity.User;

public class LoginInfo {

	public enum LoginResult {
		success, noUser, wrongPassword
	}

	private boolean success = true;

	private User user;

	private LoginResult result = LoginResult.success;

	public LoginInfo(boolean b, LoginResult r) {
		super();
		this.success = b;
		this.result = r;
	}

	public LoginInfo(User user) {
		super();
		this.user = user;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LoginResult getResult() {
		return result;
	}

	public void setResult(LoginResult result) {
		this.result = result;
	}

}
