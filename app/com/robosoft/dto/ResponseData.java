package com.robosoft.dto;

import com.fasterxml.jackson.databind.JsonNode;

public class ResponseData {
	private JsonNode result;
	private int status;

	public ResponseData(JsonNode result, int status) {
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public boolean getAuthStatus() {
		return status == 1 ? true : false;
	}
	
}
