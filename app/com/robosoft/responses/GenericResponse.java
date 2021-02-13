package com.robosoft.responses;

import com.robosoft.dto.returnValues.BaseReturnValue;
import com.robosoft.dto.returnValues.ServiceReturnValue;

public class GenericResponse extends Response {

	private Integer status;
	private String message;
	private String guid;
	private ServiceReturnValue data;
	public GenericResponse() {
		super();
		this.status = -1;
		this.data = null;
	}

	public GenericResponse(Integer status, ServiceReturnValue returnValue) {
		this.status = status;
		this.data = returnValue;
	}
	
	public GenericResponse(Integer status, String message, ServiceReturnValue returnValue) {
		this.status = status;
		this.data = returnValue;
		this.message = message; 
	}

	public GenericResponse(Integer status, String referenceNo, String requestTime, String message, ServiceReturnValue returnValue) {
		this.status = status;
		this.data = returnValue;
		this.message = message;
		if(returnValue!= null){
			((BaseReturnValue)this.getData()).setReferenceNo(referenceNo);
			((BaseReturnValue)this.getData()).setRequestTime(requestTime);
		}
	}
	
	public GenericResponse(Integer status, String referenceNo, String requestTime,  String message) {
		super();
		this.status = status;
		this.data = new Data(referenceNo, requestTime);
		this.message = message;
	}
	
	
	public GenericResponse(Integer status, String referenceNo, String requestTime, String message, ServiceReturnValue returnValue, String guid) {
		this.status = status;
		this.data = returnValue;
		this.message = message;
		this.guid = guid;
		if (returnValue != null) {
			((BaseReturnValue) this.getData()).setReferenceNo(referenceNo);
			((BaseReturnValue) this.getData()).setRequestTime(requestTime);
		}
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public ServiceReturnValue getData() {
		return data;
	}

	public void setData(ServiceReturnValue data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public class Data extends BaseReturnValue{

		public Data(String referenceNo, String requestTime) {
			super(referenceNo,requestTime);
		}
		
	}

}
