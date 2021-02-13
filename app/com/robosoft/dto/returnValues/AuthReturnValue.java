package com.robosoft.dto.returnValues;


public class AuthReturnValue extends BaseReturnValue {

	Integer authStatus;
	String authMessage;
	String guid;
	
	
	
	public AuthReturnValue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthReturnValue(Integer authStatus, String authMessage) {
		this.authStatus = authStatus;
		this.authMessage = authMessage;
	}
	
	public Integer getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}
	public String getAuthMessage() {
		return authMessage;
	}
	public void setAuthMessage(String authMessage) {
		this.authMessage = authMessage;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public AuthReturnValue(Integer authStatus, String authMessage, String guid) {
		super();
		this.authStatus = authStatus;
		this.authMessage = authMessage;
		this.guid = guid;
	}
	
	
}
