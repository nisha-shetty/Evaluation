 package com.robosoft.service.commons;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.robosoft.models.ResponseMessageModel;
import play.Logger;

import com.robosoft.constants.ResponseMessages;
import com.robosoft.service.ErrorLogServices;
import com.robosoft.servicesDAO.CommonServicesDAO;
import com.robosoft.servicesDAO.ResponseDAO;

public class ResponseServices {


	private static ResponseServices instance;
	private static String KEY = "TEST";

	public static ResponseServices getInstance() {
		if (instance == null)
			instance = new ResponseServices();
		return instance;
	}

	private ResponseServices() {
	}

	public Map<Integer, String> loadResponseMessages() {
		List<ResponseMessageModel> messageList = CommonServicesDAO.getInstance().getResponseMessages();
		try {
			if (messageList != null && !messageList.isEmpty()) {
				Map<Integer, String> tmpMap = new HashMap<Integer, String>();
				for (ResponseMessageModel msg : messageList) {
					tmpMap.put(msg.getId(), msg.getMessage());
				}
				return Collections.unmodifiableMap(tmpMap);
			}
		} catch (Exception e) {
			Logger.debug("Error while loading response message");
			ErrorLogServices.logException(e, "loadResponseMessages");
		}
		return new HashMap<Integer, String>();
	}

	public ResponseMessageModel getResponseMessageFromDB(Integer key) {
		try {
			return CommonServicesDAO.getInstance().getResponseMessage(key);
		} catch (Exception e) {
			ErrorLogServices.logException(e, "getResponseMessageFromDB");
		}
		return null;
	}

	public static Object setMpinResponse(Integer key) {
		try {
			return ResponseMessages.getMessage(key);
		}catch(Exception e) {
			ErrorLogServices.logException(e, "set mpin ");
		}
		return null;
	}
	
	public static ResponseMessageModel getErrorARNErrorResponse(Integer key) {
		try {
			return CommonServicesDAO.getInstance().getResponseMessage(key);
					}catch(Exception e) {
						ErrorLogServices.logException(e, "getErrorARNErrorResponse");
		}
		return null;
	}

	public static ResponseMessageModel getSuccessARNResponse(int key) {
		try {
			return CommonServicesDAO.getInstance().getResponseMessage(key);
					}catch(Exception e) {
						ErrorLogServices.logException(e, "get arn");
		}
		return null;
	}

	public String getResponseMessageSFromDB(int status) {
		
			try {
				return ResponseDAO.getInstance().getMessage(status);
			} catch (Exception e) {
				ErrorLogServices.logException(e, "response from db");
			}
			return null;
	
	}
	
	
}
