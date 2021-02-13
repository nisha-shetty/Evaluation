package com.robosoft.service;

import static com.robosoft.constants.ErrorResponseCodes.MSG_NOT_AVAILABLE_STCODE;
import static com.robosoft.constants.ErrorResponseCodes.SERVER_ERROR_STCODE;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import play.Logger;

import com.robosoft.constants.ResponseMessages;
import com.robosoft.dto.ServiceResult;
import com.robosoft.dto.returnValues.ServiceReturnValue;
import com.robosoft.dto.returnValues.SimpleReturnValue;
import com.robosoft.utils.LogUtil;

public class Service {
	/* Base class for Services */

	protected ServiceResult getServiceFailureResult() {
		try {
			ServiceResult failureResult = new ServiceResult();
			failureResult.setResponseCode(SERVER_ERROR_STCODE);
			failureResult.setResponseMessage(ResponseMessages.getMessage(SERVER_ERROR_STCODE));
			return failureResult;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected ServiceResult getServiceFailureResult(String message) {
		try {
			if (message == null || message.isEmpty())
				message = ResponseMessages.getMessage(MSG_NOT_AVAILABLE_STCODE);

			ServiceResult failureResult = new ServiceResult();
			failureResult.setResponseCode(SERVER_ERROR_STCODE);
			failureResult.setResponseMessage(message);
			return failureResult;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected ServiceReturnValue getServiceFailureReturnValue() {
		try {
			return new SimpleReturnValue(ResponseMessages.getMessage(SERVER_ERROR_STCODE));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected SimpleReturnValue createSimpleReturnValue(String message) {
		return new SimpleReturnValue(message);
	}

	protected SimpleReturnValue createSimpleReturnValue(String message, String referenceNo, String requestTime) {
		return new SimpleReturnValue(message, referenceNo, requestTime);
	}

	protected SimpleReturnValue createSimpleReturnValue(Integer ststusCode, String referenceNo, String requestTime) {
		return new SimpleReturnValue(ResponseMessages.getMessage(ststusCode), referenceNo, requestTime);
	}

	protected ServiceResult createServiceResult(Integer responseCode) {
		try {
			ServiceResult result = null;
			if (responseCode != null) {
				result = new ServiceResult();
				result.setResponseCode(responseCode);
				result.setResponseMessage(ResponseMessages.getMessage(responseCode));
				result.setReturnValue(new SimpleReturnValue(ResponseMessages.getMessage(responseCode)));
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected CompletionStage<ServiceResult> createServiceResultAsync(Integer responseCode) {
		try {
			ServiceResult result = null;
			if (responseCode != null) {
				result = new ServiceResult();
				result.setResponseCode(responseCode);
				result.setResponseMessage(ResponseMessages.getMessage(responseCode));
			}
			return CompletableFuture.completedFuture(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected ServiceResult createServiceResult(Integer responseCode, ServiceReturnValue serviceReturnValue) {
		try {
			LogUtil.log(">>>>>>>>>>>>>>>>>>>>>>>>    create service result");
			ServiceResult result = null;
			if (responseCode != null) {
				result = new ServiceResult();
				result.setResponseCode(responseCode);
				result.setResponseMessage(ResponseMessages.getMessage(responseCode));
				result.setReturnValue(serviceReturnValue);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected ServiceResult createServiceResult(Integer responseCode, String message, ServiceReturnValue serviceReturnValue) {
		try {
			ServiceResult result = new ServiceResult();
			result.setResponseCode(responseCode);
			if (message == null) {
				message = ResponseMessages.getMessage(responseCode);
			}
			result.setResponseMessage(message);
			result.setReturnValue(serviceReturnValue);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected ServiceResult createServiceResult(Integer responseCode, String message) {
		try {
			ServiceResult result = null;
			if (responseCode != null) {
				System.out.println(message + "=========================");
				result = new ServiceResult();
				result.setResponseCode(responseCode);
				if (message == null) {
					message = ResponseMessages.getMessage(responseCode);
				}
				result.setResponseMessage(message);
				result.setReturnValue(new SimpleReturnValue(message));
			}
			return result;
		} catch (Exception e) {
			Logger.error("createServiceResult with message" + e);
		}
		return null;
	}
	
	

}
