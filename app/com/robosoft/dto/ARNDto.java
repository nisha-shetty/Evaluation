package com.robosoft.dto;

public class ARNDto {

	public String arnCode;
	public String source;
	
	
	
	//constructor
	public ARNDto() {
		super();
	}




	public ARNDto(String arnCode, String source) {
		super();
		this.arnCode = arnCode;
		this.source = source;
	}




	public String getArnCode() {
		return arnCode;
	}




	public void setArnCode(String arnCode) {
		this.arnCode = arnCode;
	}




	public String getSource() {
		return source;
	}




	public void setSource(String source) {
		this.source = source;
	}
	
	
	
	
}
