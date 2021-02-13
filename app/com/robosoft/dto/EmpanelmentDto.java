package com.robosoft.dto;

public class EmpanelmentDto {

	private Integer userId;
	private Integer arnCode;
	private String password;
	private Integer mpin;
	private String name;
	private Integer oldMpin;
	private String source;
	private String fingerprint;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getArnCode() {
		return arnCode;
	}
	public void setArnCode(Integer arnCode) {
		this.arnCode = arnCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getMpin() {
		return mpin;
	}
	public void setMpin(Integer mpin) {
		this.mpin = mpin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOldMpin() {
		return oldMpin;
	}
	public void setOldMpin(Integer oldMpin) {
		this.oldMpin = oldMpin;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getFingerprint() {
		return fingerprint;
	}
	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}
	
	
	
	
}
