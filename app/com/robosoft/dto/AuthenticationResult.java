package com.robosoft.dto;

import com.fasterxml.jackson.databind.JsonNode;

public class AuthenticationResult {
	private JsonNode result;
	private Boolean status;
	
	public AuthenticationResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthenticationResult(JsonNode result, Boolean status) {
		super();
		this.result = result;
		this.status = status;
	}

	public JsonNode getResult() {
		return result;
	}

	public void setResult(JsonNode result) {
		this.result = result;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
	
	

}
