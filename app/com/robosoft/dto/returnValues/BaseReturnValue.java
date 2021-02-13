package com.robosoft.dto.returnValues;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseReturnValue implements ServiceReturnValue {
	
	private String referenceNo;
	@JsonIgnore
	private String requestTime;
	
	
	public BaseReturnValue() {
		super();
	}

	public BaseReturnValue(String referenceNo, String requestTime) {
		super();
		this.referenceNo = referenceNo;
		this.requestTime = requestTime;
	}
	
	
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

}
