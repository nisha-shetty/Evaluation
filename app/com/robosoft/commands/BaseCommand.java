package com.robosoft.commands;

import static com.robosoft.constants.AppConstants.AES_ENCRYPTION_KEY;
import static com.robosoft.constants.AppConstants.AES_IVECTOR_KEY;
import static com.robosoft.constants.AppConstants.AES_PADDING_KEY;
import static com.robosoft.constants.AppConstants.ENABLE_ENC;
import static com.robosoft.constants.AppConstants.TYPE_REFERENCE_NO;
import static com.robosoft.constants.AppConstants.appSettings;
import static com.robosoft.constants.ErrorResponseCodes.ATTEMPT_REMAINING_TAIL;
import static com.robosoft.constants.ErrorResponseCodes.ATTEMPT_REMAINING_TAIL_M;
import static com.robosoft.constants.ErrorResponseCodes.AUTH_L2_FAILED_STCODE;
import static com.robosoft.constants.ErrorResponseCodes.AUTH_L2_REQUIRED_PART_1_STCODE;
import static com.robosoft.constants.ErrorResponseCodes.AUTH_L2_REQUIRED_PART_2_STCODE;
import static com.robosoft.constants.ErrorResponseCodes.AUTH_PASSWORD_EXPIRED_STCODE;
import static com.robosoft.constants.ErrorResponseCodes.IMPS_P2A_FAILED_STCODE;
import static com.robosoft.constants.ErrorResponseCodes.IMPS_P2A_GET_MSG_FAILED_STCODE;
import static com.robosoft.constants.ErrorResponseCodes.L2_AUTH_INCORRECT_PART_1;
import static com.robosoft.constants.ErrorResponseCodes.SERVER_ERROR_STCODE;
import static com.robosoft.constants.InputFieldConstants.CHANNEL;
import static com.robosoft.constants.InputFieldConstants.L2_AUTH_REFERENCE;
import static com.robosoft.constants.InputFieldConstants.SESSION_TOKEN;
import static com.robosoft.constants.Messages.VALIDATION_ERROR;
import static com.robosoft.constants.Messages.VALIDATION_ERROR_RUNTIME;
import static com.robosoft.constants.ResponseConstants.AUTH_RESPONSE;
import static com.robosoft.constants.ResponseConstants.FAILURE_STATUS;
import static com.robosoft.constants.ResponseConstants.L2_AUTH_FAILURE;
import static com.robosoft.constants.ResponseConstants.L2_AUTH_RETRY;
import static com.robosoft.constants.ResponseConstants.STATUS_ERROR;
import static com.robosoft.constants.ResponseConstants.STATUS_OK;
import static com.robosoft.constants.ResponseConstants.SUCCESS_STATUS;
import static com.robosoft.constants.StatusConstants.L2_AUTH_FAILED;
import static com.robosoft.constants.StatusConstants.L2_AUTH_PASSED;
import static com.robosoft.constants.StatusConstants.L2_AUTH_REQUIRED;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.robosoft.models.AuthRequestModel;
import com.robosoft.models.Service;
import play.Logger;
import play.libs.Json;
import play.mvc.Http.Request;
import play.mvc.Http.RequestBuilder;
import scala.collection.mutable.StringBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.robosoft.constants.ResponseMessages;
import com.robosoft.dto.AuthenticationResult;
import com.robosoft.dto.RequestFields;
import com.robosoft.dto.ResponseData;
import com.robosoft.dto.ServiceResult;
import com.robosoft.dto.requestInputs.SimpleRequestInputs;
import com.robosoft.dto.returnValues.AuthResponseReturnValue;
import com.robosoft.numberFormat.NumberFormatUtil;
import com.robosoft.responses.GenericErrorResponse;
import com.robosoft.responses.GenericResponse;
import com.robosoft.responses.GenericResponseEncrypted;
import com.robosoft.router.ServiceConfigReader;
import com.robosoft.service.AuthenticationServices;
import com.robosoft.service.ErrorLogServices;
import com.robosoft.utils.DateUtils;
import com.robosoft.utils.LogUtil;
import com.validation.ValidationPattern;
import com.validation.Validator;
import com.robosoft.models.AuthRequestModel;
public class BaseCommand {

	String mReferenceNumber;
	String mRequestTime;
	String channel;
	private String mSessionToken;
	private String mDeviceId;
	public Integer l2AuthSmsType;

	public BaseCommand() {
		long miliSeconds = System.currentTimeMillis();
		String referenceNumber = NumberFormatUtil.getInstance().getFormattedNumber(TYPE_REFERENCE_NO, miliSeconds);
		mRequestTime = DateUtils.getCurrentFullTime(miliSeconds);
		channel = play.mvc.Controller.request().getHeader(CHANNEL);
		if (channel != null)
			mReferenceNumber = channel + referenceNumber;
		else
			mReferenceNumber = referenceNumber;
		setSessionToken();
		setDeviceId();
		requestLogging();
	}

	protected ResponseData prepareJsonReponse(ServiceResult serviceResult) {
		JsonNode result = null;
		int status = STATUS_ERROR;
		Integer statusCode = -1;
		ResponseData responseData = null;

		if (serviceResult != null) {
			statusCode = serviceResult.getResponseCode() >= 0 ? SUCCESS_STATUS : FAILURE_STATUS;
			if (serviceResult.isSuccess()) {
				result = new GenericResponse(statusCode, mReferenceNumber, mRequestTime, serviceResult.getResponseMessage(), serviceResult.getReturnValue()).asJson();
				status = STATUS_OK;
			} else {
				result = new GenericErrorResponse(statusCode, mReferenceNumber, mRequestTime, serviceResult.getResponseCode(), serviceResult.getResponseMessage()).asJson();
				status = STATUS_OK;
			}
		} else {
			result = new GenericErrorResponse(statusCode, mReferenceNumber, mRequestTime, ResponseMessages.getMessage(SERVER_ERROR_STCODE)).asJson();
		}
		responseData = new ResponseData(result, status);
		return responseData;
	}

	protected JsonNode prepareJsonReponse(Integer responseCode, String statusMsg) {
		JsonNode result = null;
		Integer statusCode = -1;
		if (statusMsg == null)
			statusMsg = ResponseMessages.getMessage(responseCode);
		if (responseCode >= 0) {
			statusCode = 0;
			result = new GenericResponse(statusCode, mReferenceNumber, mRequestTime, statusMsg).asJson();
		} else {
			statusCode = -1;
			result = new GenericErrorResponse(statusCode, mReferenceNumber, mRequestTime, responseCode, statusMsg).asJson();
		}
		return result;
	}

	protected JsonNode prepareJsonReponse(Integer responseCode) {
		return prepareJsonReponse(responseCode, null);
	}

	protected JsonNode prepareJsonReponse(Integer statusCode, String statusMsg, String data) {
		JsonNode result = getObjectNode(statusCode, statusMsg, data);
		return result;
	}

	protected <T> ArrayNode prepareArrayReponse(T jsonNode) {
		ArrayNode result = (ArrayNode) Json.toJson(jsonNode);
		return result;
	}

	private JsonNode getObjectNode(Integer statusCode, String statusMsg, String data) {
		ObjectNode result = Json.newObject();
		result.put("status", statusCode);
		result.put("message", statusMsg);
		if (data != null)
			result.put("data", data);
		return result;
	}

	public String getReferenceNumber() {
		return mReferenceNumber;
	}

	public String getStan() {
		//return NumberFormatUtil.getInstance().getFormattedNumber(TYPE_STAN, 0);
		return "";
	}

	public String getTraceId() {
		//return NumberFormatUtil.getInstance().getFormattedNumber(TYPE_TRACEID, 0);
		return "";
	}

	public String getRequestTime() {
		return mRequestTime;
	}

	public String getChannel() {
		return channel;
	}

	protected Request resumeStoredRequest(CommandParams currentRequest) {
		try {
			AuthRequestModel authReq = AuthenticationServices.getInstance().getAuthRequest(currentRequest.getRequestBody().asJson().get(L2_AUTH_REFERENCE).asText(),
					getSessionToken());
			if (authReq == null) {
				return null;
			}
			JsonNode storedReqBody = new ObjectMapper().readTree(authReq.getRequestBody());
			RequestBuilder rb = new RequestBuilder();
			rb.headers(currentRequest.getHeaders());
			rb.bodyJson(storedReqBody);
			return rb.build();
		} catch (Exception e) {
			e.printStackTrace();
			Logger.error("" + e);
		}
		return null;
	}

	protected AuthenticationResult manageAuthFlow(Integer status, CommandParams params, Service service) {
		JsonNode result = null;
		boolean authStatus = false;
		try {
			if (status < 0) {
				//return AuthErrorHandler.handleAuthFail(status, params, service, getReferenceNumber(), getRequestTime());
			} else if (status == L2_AUTH_REQUIRED) {
				result = showL2AuthRequiredErrorResponse(params, service);
				authStatus = false;// Authentication is still not completed
			} else if (status == L2_AUTH_PASSED) {
				/* authentication success for otp or mpin */
				Request storedReq = resumeStoredRequest(params);
				if (storedReq == null) {
					result = new GenericErrorResponse(FAILURE_STATUS, AUTH_L2_FAILED_STCODE, ResponseMessages.getMessage(AUTH_L2_FAILED_STCODE)).asJson();
					authStatus = false;
				} else {
					boolean closeStatus = AuthenticationServices.getInstance().closeAuthRequest(params.getRequestBody().asJson().get(L2_AUTH_REFERENCE).asText(),
							getSessionToken());
					if (!closeStatus) {
						result = new GenericErrorResponse(FAILURE_STATUS, AUTH_L2_FAILED_STCODE, ResponseMessages.getMessage(AUTH_L2_FAILED_STCODE)).asJson();
					} else {
						params.setRequest(storedReq);
						authStatus = true;
					}
				}
			} else if (status == L2_AUTH_FAILED) {
				return showL2AuthFailedErrorResponse(params, service);
			} else {
				authStatus = true;
			}
			return new AuthenticationResult(result, authStatus);

		} catch (Exception e) {
			Logger.error("" + e);
		}
		return null;
	}

	private AuthenticationResult showL2AuthFailedErrorResponse(CommandParams params, Service service) {
		JsonNode result = null;
		boolean authStatus = false;
		try {
			AuthRequestModel authReq = AuthenticationServices.getInstance().getAuthRequest(params.getRequestBody().asJson().get(L2_AUTH_REFERENCE).asText(), getSessionToken());
			if (authReq == null) {
				return new AuthenticationResult(new GenericErrorResponse(FAILURE_STATUS, AUTH_L2_FAILED_STCODE, ResponseMessages.getMessage(AUTH_L2_FAILED_STCODE)).asJson(),
						false);

			}
			if (authReq.getRetryCount() >= Integer.parseInt(appSettings.get("MAX_ALLOWED_L2_AUTH_RETRY"))) {
				result = new GenericErrorResponse(L2_AUTH_FAILURE, AUTH_L2_FAILED_STCODE, AuthenticationServices.getInstance().getL2AuthFailureMessage(service.L2AuthKeys))
						.asJson();
			} else {
				result = new GenericErrorResponse(L2_AUTH_RETRY, L2_AUTH_RETRY, getL2WrongCredentialMessage(service.L2AuthKeys, authReq.getRetryCount()),
						new AuthResponseReturnValue(getReferenceNumber(), getRequestTime(), params.getRequestBody().asJson().get(L2_AUTH_REFERENCE).asText(), service.L2AuthKeys))
								.asJson();
			}
			return new AuthenticationResult(result, authStatus);
		} catch (Exception e) {
			Logger.error("" + e);
			// TODO: handle exception
		}
		return null;
	}

	private JsonNode showL2AuthRequiredErrorResponse(CommandParams params, Service service) {
		JsonNode result = null;
		String expiryTime = "";
		try {
			String authRef = AuthenticationServices.getInstance().saveRequest(params.getRequestBody().asJson().toString(), getSessionToken(), getDeviceId());
			if (authRef == null) {
				result = new GenericErrorResponse(SUCCESS_STATUS, SERVER_ERROR_STCODE, ResponseMessages.getMessage(SERVER_ERROR_STCODE)).asJson();
			} else {

				String otp = null;
				if (service.L2AuthKeys.equals("otp")) {
				/*	CustomerModel customerModel = CustomerServices.getInstance().getCustomer(getCustId());
					otp = OTPServices.getInstance().generateOTP();
					OTPServices.getInstance().saveOTP(otp, customerModel);
					Integer userId = UserServices.getInstance().getUserWithCustId(getCustId()).getId();
					UserAuthenticationServices.getInstance().clearFailedCredentials(userId, OTP);
					SMSUtil.sendL2AuthSMS(l2AuthSmsType, params, otp, customerModel.getMobileNo());
					expiryTime = SMSUtil.getOTPExpiryTime();*/
				}
				result = new GenericResponse(AUTH_RESPONSE, getAuthReqMessage(service.L2AuthKeys),
						new AuthResponseReturnValue(getReferenceNumber(), getRequestTime(), authRef, service.L2AuthKeys, otp, expiryTime)).asJson();
			}
		} catch (Exception e) {
			Logger.error("" + e);
			// TODO: handle exception
		}
		return result;
	}

	private String getL2WrongCredentialMessage(String L2AuthKeys, Integer retryCount) {
		StringBuilder msg = new StringBuilder();
		try {
			msg.append(ResponseMessages.getMessage(L2_AUTH_INCORRECT_PART_1));
			msg.append(L2AuthKeys.replace(",", " or ")).append(", ");
			msg.append(Integer.parseInt(appSettings.get("MAX_ALLOWED_L2_AUTH_RETRY")) - retryCount);
			if (retryCount > 1) {
				msg.append(ResponseMessages.getMessage(ATTEMPT_REMAINING_TAIL_M));
			} else {
				msg.append(ResponseMessages.getMessage(ATTEMPT_REMAINING_TAIL));
			}
		} catch (Exception e) {
			msg.clear();
			msg.append("Incorrect ").append(L2AuthKeys);
			Logger.error("" + e);
		}
		return msg.toString();
	}

	private String getAuthReqMessage(String L2AuthKeys) {
		StringBuilder msg = new StringBuilder();
		try {
			msg.append(ResponseMessages.getMessage(AUTH_L2_REQUIRED_PART_1_STCODE));
			msg.append(L2AuthKeys.replace(",", " and "));
			msg.append(ResponseMessages.getMessage(AUTH_L2_REQUIRED_PART_2_STCODE));
		} catch (Exception e) {
			msg.clear();
			msg.append("Please provide authentication");
			Logger.error("" + e);
		}
		return msg.toString();
	}

	public <T> boolean validate(RequestFields[] requestFields, T requestObj) {
		return validate(requestFields, requestObj, null);
	}

	public <T> boolean validateWithVal(RequestFields[] requestFields, T requestObj, Validator val) {
		return validate(requestFields, requestObj, null, val);
	}

	private ValidationPattern setValidationPattern(RequestFields requestField) {
		ValidationPattern validationPattern = null;
		try {
			validationPattern = ServiceConfigReader.getValidationPattern(requestField.getName());
			if (validationPattern != null) {
				validationPattern.setOptional(requestField.isOptional());
			} else {
				validationPattern = new ValidationPattern();
				validationPattern.setFieldName(requestField.getName());
				validationPattern.setOptional(requestField.isOptional());
			}
		} catch (Exception e) {
			Logger.error("" + e);
			Logger.error(e.getMessage());
		}
		return validationPattern;
	}

	public <T> boolean validate(RequestFields[] requestFields, T requestObj, String[] keyname) {
		Validator val = new Validator();
		ValidationPattern validationPattern = null;
		if (requestFields != null) {
			boolean isValid = false;
			try {
				for (RequestFields requestField : requestFields) {
					validationPattern = setValidationPattern(requestField);
					val.addParams(validationPattern);
				}
				val.setReportType(Validator.ALL_FAILED_KEYS);
				isValid = val.valid(requestObj);
				if (val.getFailedKeys() != null) {
					String keys = Arrays.toString(val.getFailedKeys());
					if (keyname != null) {
						keyname[0] = val.getFailedKeys()[0];
					}
					LogUtil.log(getFormattedMessage("failed keys " + keys));
				}
			} catch (RuntimeException e) {
				Logger.error("RuntimeException--> error---" + e);
				Logger.info(VALIDATION_ERROR_RUNTIME + e.getMessage());
			} catch (Exception e) {
				Logger.error("Exception--> error---" + e);
				Logger.info(VALIDATION_ERROR + e.getMessage());
			}
			return isValid;
		}
		return false;
	}

	public <T> boolean validate(RequestFields[] requestFields, T requestObj, String[] keyname, Validator val) {
		if (requestFields != null) {
			ValidationPattern validationPattern = null;
			boolean isValid = false;
			try {
				for (RequestFields requestField : requestFields) {
					validationPattern = setValidationPattern(requestField);
					val.addParams(validationPattern);
				}
				val.setReportType(Validator.ALL_FAILED_KEYS);
				isValid = val.valid(requestObj);
				if (val.getFailedKeys() != null) {
					String keys = Arrays.toString(val.getFailedKeys());
					if (keyname != null) {
						keyname[0] = val.getFailedKeys()[0];
					}
					LogUtil.log(getFormattedMessage("failed keys " + keys));
				}
			} catch (RuntimeException e) {
				Logger.error("RuntimeException--> error---" + e);
				Logger.info(VALIDATION_ERROR_RUNTIME + e.getMessage());
			} catch (Exception e) {
				Logger.error("Exception--> error---" + e);
				Logger.info(VALIDATION_ERROR + e.getMessage());
			}
			return isValid;
		}
		return false;

	}

	private void setSessionToken() {
		try {
			mSessionToken = play.mvc.Http.Context.current().request().getHeader(SESSION_TOKEN);
		} catch (Exception e) {
			Logger.error("" + e);
		}
	}

	private void setDeviceId() {
		try {
			//mDeviceId = play.mvc.Http.Context.current().request().getHeader(DEVICE_ID);
		} catch (Exception e) {
			Logger.error("" + e);
		}
	}

	protected void setSessionToken(String sessionToken) {
		if (sessionToken == null || sessionToken.isEmpty()) {
			return;
		}
		this.mSessionToken = sessionToken;
	}

	protected String getSessionToken() {
		if (mSessionToken != null && !mSessionToken.isEmpty()) {
			return mSessionToken;
		}
		return null;
	}

	protected String getDeviceId() {
		if (mDeviceId != null && !mDeviceId.isEmpty()) {
			return mDeviceId;
		}
		return null;
	}


	protected void requestLogging() {
		Request request = play.mvc.Http.Context.current().request();
		LogUtil.log(new StringBuilder().append("REQ Headers: DeviceId: ").append(request.getHeader("device-id")).append(", SessionToken: ").append(request.getHeader(SESSION_TOKEN))
				.append(", Channel: ").append(request.getHeader(CHANNEL)).toString());
		if (request.body() != null) {
			JsonNode jsonNode = request.body().asJson();
			LogUtil.log("REQ: " + getFormattedMessage(request.uri(), jsonNode != null ? jsonNode.toString() : "empty body"));
		}
	}

	protected String getFormattedMessage(String uri, String msg) {
		String Space = " ";
		return new StringBuilder(uri).append(Space).append(mReferenceNumber).append(Space).append(msg).toString();
	}

	protected String getFormattedMessage(String msg) {
		String uri = play.mvc.Http.Context.current().request().uri();
		return getFormattedMessage(uri, msg);
	}
	

	
	public boolean isAPIEncryptionEnabled() {
		boolean encEnabled = false;
		try {
			encEnabled = (appSettings.get(ENABLE_ENC) != null && Integer.valueOf(appSettings.get(ENABLE_ENC)) == 1) ? true : false;
			LogUtil.log("enc enabled >>>>>>>>>>>>>>>>>>>>>>>  "+encEnabled);
		} catch (Exception e) {
			ErrorLogServices.logException(e, "isAPIEncryptionEnabled");
		}
		return encEnabled;
	}
	
	public Request setDecryptedRequest(CommandParams currentRequest, String inReferenceNo, SimpleRequestInputs inReqInputs, String AESEncKey) {
		try {
			//LogUtil.log("edata " + inReqInputs.geteData());
			//String requestBody = com.robosoft.crypto.AES.decryptWithIVForALL(inReqInputs.geteData(), appSettings.get(AES_ENCRYPTION_KEY), appSettings.get(AES_PADDING_KEY), AES_IVECTOR_KEY);
			String requestBody = com.robosoft.crypto.AES.decryptWithIVForALL(inReqInputs.geteData(), appSettings.get(AES_ENCRYPTION_KEY), appSettings.get(AES_PADDING_KEY), appSettings.get(AES_IVECTOR_KEY));
			LogUtil.log("\ninReferenceNo==>" + inReferenceNo + " Decrypted RequestBody==>" + requestBody);
			JsonNode decryptedReqBody = new ObjectMapper().readTree(requestBody);
			// set request
			RequestBuilder rb = new RequestBuilder();
			rb.headers(currentRequest.getHeaders());
			rb.bodyJson(decryptedReqBody);
			return rb.build();
		} catch (Exception e) {
			ErrorLogServices.logException(e, "setDecryptedRequest");
		}
		return null;
	}
	
	
	protected ResponseData prepareJsonReponseEncrypted(ServiceResult serviceResult, String guid, boolean isRespEncryptionRequired, String AESEncKey, String mReferenceNumber) {
		JsonNode result = null;
		int status = STATUS_ERROR;
		Integer statusCode = -1;
		ResponseData responseData = null;

		if (serviceResult != null) {
			statusCode = serviceResult.getResponseCode() >= 0 ? SUCCESS_STATUS : FAILURE_STATUS;
			if (serviceResult.isSuccess()) {
				result = new GenericResponse(statusCode, mReferenceNumber, mRequestTime, serviceResult.getResponseMessage(), serviceResult.getReturnValue(), guid).asJson();
				status = STATUS_OK;
			} else {
				if (serviceResult.getResponseCode() == AUTH_PASSWORD_EXPIRED_STCODE) {
					result = new GenericErrorResponse(AUTH_PASSWORD_EXPIRED_STCODE, mReferenceNumber,mRequestTime, AUTH_PASSWORD_EXPIRED_STCODE,
							serviceResult.getResponseMessage(), serviceResult.getReturnValue()).asJson();

				} else if (IMPS_P2A_GET_MSG_FAILED_STCODE == serviceResult.getResponseCode() || IMPS_P2A_FAILED_STCODE == serviceResult.getResponseCode()) {// for
																																							// imps
																																							// get
																																							// message
																																							// timeout
					result = new GenericErrorResponse(serviceResult.getResponseCode(), serviceResult.getResponseCode(), serviceResult.getResponseMessage(),
							serviceResult.getReturnValue()).asJson();

				} else {
					result = new GenericErrorResponse(statusCode,mReferenceNumber, mRequestTime, serviceResult.getResponseCode(), serviceResult.getResponseMessage(),serviceResult.getReturnValue()).asJson();
				}
				status = STATUS_OK;
			}
		} else {
			result = new GenericErrorResponse(statusCode,mReferenceNumber, mRequestTime, ResponseMessages.getMessage(SERVER_ERROR_STCODE)).asJson();
		}
		if (isRespEncryptionRequired) {
			LogUtil.log("\n\n<br/> RES: Reference No=>" + mReferenceNumber + " Response==>" + result.toString() + " \n<br/> ");
			try {
				responseData = new ResponseData(new GenericResponseEncrypted(Json.toJson(com.robosoft.crypto.AES.encryptIV(result.toString(), appSettings.get(AES_ENCRYPTION_KEY), appSettings.get(AES_IVECTOR_KEY)))).asJson(), status);
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
					| BadPaddingException | InvalidAlgorithmParameterException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				ErrorLogServices.logException(e, "prepareJsonReponseEncrypted");
			}
		} else {
			responseData = new ResponseData(result, status);
		}
		return responseData;
	}
	
	protected JsonNode prepareJsonReponseEncrypted(Integer responseCode, String statusMsg, boolean isRespEncryptionRequired, String AESEncKey) {
		JsonNode result = null;
		Integer statusCode = -1;
		if (statusMsg == null)
			statusMsg = ResponseMessages.getMessage(responseCode);
		if (responseCode >= 0) {
			statusCode = 0;
			result = new GenericResponse(statusCode, mReferenceNumber,mRequestTime, statusMsg).asJson();
		} else {
			statusCode = -1;
			result = new GenericErrorResponse(statusCode,mReferenceNumber, mRequestTime, responseCode, statusMsg).asJson();
		}
		if (isRespEncryptionRequired) {
			LogUtil.log("\n\n<br/> RES:Promise result Reference No=>" + mReferenceNumber + " Response==>" + result.toString() + " \n<br/> ");
			try {
				ResponseData responseData = new ResponseData(new GenericResponseEncrypted(Json.toJson(com.robosoft.crypto.AES.encryptIV(result.toString(), appSettings.get(AES_ENCRYPTION_KEY), appSettings.get(AES_IVECTOR_KEY)))).asJson(), statusCode);
				result = responseData.getResult();
			} catch(Exception e) {
				ErrorLogServices.logException(e, "prepareJsonReponseEncrypted");
			}
		}
		return result;
	}

	
	public JsonNode getEncryptedAuthResult(AuthenticationResult authResult, String AESEncKey, int authStatus) {
		try {
			if (isAPIEncryptionEnabled() && authResult != null && authResult.getResult() != null) {
				LogUtil.log("\n\n<br/> RES:Authentication Reference No=>" + mReferenceNumber + " Response==>" + authResult.getResult().toString() + " \n<br/> ");
				try {
					ResponseData responseData = new ResponseData(new GenericResponseEncrypted(Json.toJson(com.robosoft.crypto.AES.encryptIV(authResult.getResult().toString(), appSettings.get(AES_ENCRYPTION_KEY), appSettings.get(AES_IVECTOR_KEY)))).asJson(),
							authStatus);
					return responseData.getResult();
				} catch(Exception e) {
					ErrorLogServices.logException(e, "getEncryptedAuthResult");
				}
			
			} else {
				return authResult.getResult();
			}
		} catch (Exception e) {
			ErrorLogServices.logException(e, "getEncryptedAuthResult");
		}
		return null;
	}
	
	
	public boolean checkTimeStampDifference(String timeStamp){
		
		long updatedTimeStamp;
		if(timeStamp!=null)
			updatedTimeStamp=Long.parseLong(timeStamp);
		else	
			updatedTimeStamp=DateUtils.getTimeInMili()-1;
				
		long currentTimeStamp=DateUtils.getTimeInMili();
		long difference=currentTimeStamp-updatedTimeStamp;	
		long differenceInMin=difference/(1000*60);	
		//TIME_STAMP_DIFFERENCE
		if (differenceInMin<30){	
			return true;
		}else{
//			//boolean isLogout = SessionManager.stopSession(getSessionToken());
//			LogUtil.log("isLogout" +isLogout);
			return false;
		}
		
				
	}
	
	
}