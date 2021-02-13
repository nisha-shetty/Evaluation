package com.robosoft.dto;

public class UrlDto {

	private String raw;
	private String[] host;
	public UrlDto() {
		super();
	}
	public UrlDto(String raw, String[] host) {
		super();
		this.raw = raw;
		this.host = host;
	}
	public String getRaw() {
		return raw;
	}
	public void setRaw(String raw) {
		this.raw = raw;
	}
	public String[] getHost() {
		return host;
	}
	public void setHost(String[] host) {
		this.host = host;
	}
	
	
	
}
