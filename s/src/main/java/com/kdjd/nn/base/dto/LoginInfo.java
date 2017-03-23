package com.kdjd.nn.base.dto;

public class LoginInfo {

	public enum LoginResult {
		success, noUser, wrongPassword
	}

	private boolean success = true;

	private String userId;

	private LoginResult result = LoginResult.success;

	public LoginInfo(boolean b, LoginResult r) {
		super();
		this.success = b;
		this.result = r;
	}

	public LoginInfo(String id) {
		super();
		this.userId = id;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public LoginResult getResult() {
		return result;
	}

	public void setResult(LoginResult result) {
		this.result = result;
	}
}
