package com.robosoft.dto;

public class BodyDto {

	private String mode;
	private String[] raw;
	public BodyDto() {
		super();
	}
	public BodyDto(String mode, String[] raw) {
		super();
		this.mode = mode;
		this.raw = raw;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String[] getRaw() {
		return raw;
	}
	public void setRaw(String[] raw) {
		this.raw = raw;
	}
	
	
}
