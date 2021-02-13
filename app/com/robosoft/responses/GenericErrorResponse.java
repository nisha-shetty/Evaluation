package com.robosoft.responses;

import static com.robosoft.constants.ErrorResponseCodes.SERVER_ERROR_STCODE;

import com.robosoft.dto.returnValues.BaseReturnValue;
import com.robosoft.dto.returnValues.ServiceReturnValue;

public class GenericErrorResponse extends Response {
	
	private Integer status;
	private ServiceReturnValue data;
	private Error error;	
		
	public GenericErrorResponse() {
		super();
	}

	public GenericErrorResponse(Integer status, Integer errorCode, String errorMessage) {
		super();
		this.status = status;
		this.error = new Error(errorCode, errorMessage);
	}
	
	public GenericErrorResponse(Integer status, String referenceNo, String requestTime, String errorMessage) {
		super();
		this.status = status;
		this.data = new Data(referenceNo, requestTime);
		this.error = new Error(SERVER_ERROR_STCODE, errorMessage);
	}
	
	public GenericErrorResponse(Integer status, String referenceNo, String requestTime, Integer errorCode, String errorMessage) {
		super();
		this.status = status;
		this.data = new Data(referenceNo, requestTime);
		this.error = new Error(errorCode, errorMessage);
	}

	public GenericErrorResponse(Integer status, Integer errorCode, String errorMessage, ServiceReturnValue returnValue) {
		super();
		this.status = status;
		this.error = new Error(errorCode, errorMessage);
		data = returnValue;
	}
	
	public GenericErrorResponse(Integer status, String referenceNo, String requestTime, Integer errorCode, String errorMessage, ServiceReturnValue returnValue) {
		super();
		this.status = status;
		this.error = new Error(errorCode, errorMessage);
		data = returnValue;
		if (data != null) {
			BaseReturnValue baseReturnValue = (BaseReturnValue) data;
			baseReturnValue.setReferenceNo(referenceNo);
			baseReturnValue.setRequestTime(requestTime);
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


	public Error getError() {
		return error;
	}


	public void setError(Error error) {
		this.error = error;
	}


	public class Data extends BaseReturnValue{

		public Data(String referenceNo, String requestTime) {
			super(referenceNo,requestTime);
		}
		
	}
	
	public class Error{
		private Integer errorCode;
		private String errorMessage;
		
		public Error(Integer errorCode, String errorMessage) {
			super();
			this.errorCode = errorCode;
			this.errorMessage = errorMessage;
		}
		public Integer getErrorCode() {
			return errorCode;
		}
		public void setErrorCode(Integer errorCode) {
			this.errorCode = errorCode;
		}
		public String getErrorMessage() {
			return errorMessage;
		}
		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}
		
	}

}
