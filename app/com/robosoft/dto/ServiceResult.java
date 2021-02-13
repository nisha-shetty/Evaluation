package com.robosoft.dto;

import com.robosoft.dto.returnValues.ServiceReturnValue;

public class ServiceResult {
	
	private Integer responseCode;
	private String responseMessage;
	private ServiceReturnValue returnValue;
	
	public boolean isSuccess(){
		if(responseCode != null && responseCode >= 0){
			return true;
		}
		return false;
	}
	
	public Integer getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public ServiceReturnValue getReturnValue() {
		return returnValue;
	}
	public void setReturnValue(ServiceReturnValue returnValue) {
		this.returnValue = returnValue;
	}
}
