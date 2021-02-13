package com.robosoft.dto;

public class UserDto {

	private String userId;
	private String password;
	private String content_type;
	
	
	public UserDto() {
		super();
	}


	public UserDto(String userId, String password, String content_type) {
		super();
		this.userId = userId;
		this.password = password;
		this.content_type = content_type;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getContent_type() {
		return content_type;
	}


	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}
	
	
	
	
}
