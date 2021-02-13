package com.robosoft.service;


import static com.robosoft.constants.ErrorResponseCodes.AUTH_L2_FAILED_PART_1_STCODE;
import static com.robosoft.constants.ErrorResponseCodes.AUTH_L2_FAILED_PART_2_STCODE;
import com.robosoft.models.AuthRequestModel;
import play.Logger;

import com.robosoft.constants.ResponseMessages;
import com.robosoft.servicesDAO.AuthenticationServicesDAO;


public class AuthenticationServices extends Service {

	private static AuthenticationServices instance;

	/**
	 * Function is used to get instance of the class
	 * 
	 * @return
	 */
	public static AuthenticationServices getInstance() {
		if (instance == null) {
			instance = new AuthenticationServices();
		}
		return instance;
	}

	public String saveRequest(String requestBody, String sessionToken, String deviceId) {
		try {
			AuthRequestModel authReq = new AuthRequestModel(requestBody, sessionToken, deviceId);
			authReq.save();
			return authReq.getReference();
		} catch (Exception e) {
			Logger.error("saveRequest" + e);
		}
		return null;
	}
	
	public boolean closeAuthRequest(String reference, String sessionToken) {
		try {
			AuthRequestModel authRequest = AuthenticationServicesDAO.getInstance().getAuthRequest(reference, sessionToken);
			if (authRequest == null) {
				// Logger.debug("Stored request cannot be found.");
				return false;
			}
			authRequest.setIsConsumed(true);
			boolean retryUpdateStatus = AuthenticationServicesDAO.getInstance().saveAuthRequest(authRequest);
			if (retryUpdateStatus)
				return true;
		} catch (Exception e) {
			Logger.error("closeAuthRequest" + e);
		}
		return false;
	}
	
	public AuthRequestModel getAuthRequest(String reference, String sessionToken) {
		try {
			// Logger.debug("DAO> reference : " + reference);
			AuthRequestModel authRequest = AuthenticationServicesDAO.getInstance().getAuthRequest(reference, sessionToken);
			if (authRequest == null) {
				// Logger.debug("Stored request cannot be found.");
				return null;
			}
			authRequest.setRetryCount(authRequest.getRetryCount() + 1);
			//if (authRequest.getRetryCount() >= Integer.parseInt(appSettings.get("MAX_ALLOWED_L2_AUTH_RETRY"))) {
			if (authRequest.getRetryCount() >= 3) {
				authRequest.setIsConsumed(true);
			}
			boolean retryUpdateStatus = AuthenticationServicesDAO.getInstance().saveAuthRequest(authRequest);
			if (retryUpdateStatus)
				return authRequest;
		} catch (Exception e) {
			Logger.error("getAuthRequest" + e);
		}
		return null;
	}
	
	public String getL2AuthFailureMessage(String l2AuthKeys) {
		StringBuilder msg = new StringBuilder();
		try {
			msg.append(ResponseMessages.getMessage(AUTH_L2_FAILED_PART_1_STCODE));
			msg.append(l2AuthKeys.replace(",", " or "));
			msg.append(ResponseMessages.getMessage(AUTH_L2_FAILED_PART_2_STCODE));
		} catch (Exception e) {
			Logger.error("getL2AuthFailureMessage" + e);
		}
		return msg.toString();
	}

}
