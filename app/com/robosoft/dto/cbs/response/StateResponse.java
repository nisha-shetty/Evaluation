package com.robosoft.dto.cbs.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"STATUS_CODE",
"Message",
"Data"
})
public class StateResponse {

	@JsonProperty("STATUS_CODE")
	private Integer status;
	@JsonProperty("Message")
	private String message;
	@JsonProperty("Data")
	private StateDetailResponse data;

	
	@JsonProperty("Status")
	public Integer getStatus() {
	return status;
	}

	@JsonProperty("Status")
	public void setStatus(Integer status) {
	this.status = status;
	}

	@JsonProperty("Message")
	public String getMessage() {
	return message;
	}

	@JsonProperty("Message")
	public void setMessage(String message) {
	this.message = message;
	}
	
	@JsonProperty("Data")
	public StateDetailResponse getData() {
		return data;
	}
	
	@JsonProperty("Data")
	public void setData(StateDetailResponse data) {
		this.data = data;
	}

	
}
