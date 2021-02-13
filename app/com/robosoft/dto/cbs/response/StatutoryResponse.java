package com.robosoft.dto.cbs.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatutoryResponse {
	
	@JsonProperty("Status")
	private String status;
	@JsonProperty("Message")
	private String message;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    
    
}
