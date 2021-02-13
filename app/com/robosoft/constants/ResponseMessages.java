package com.robosoft.constants;

import java.util.Map;

import com.robosoft.models.ResponseMessageModel;

import com.robosoft.service.ErrorLogServices;
import com.robosoft.service.commons.ResponseServices;

public class ResponseMessages {

	public static Map<Integer, String> responseMessages;
	


	public static String getMessage(Integer key) {
		String msg = "";
		try {
			msg = responseMessages.get(key);
			if (msg != null && !msg.isEmpty()) {
				return msg;
			}
			ResponseMessageModel msgModel = ResponseServices.getInstance().getResponseMessageFromDB(key);
			if (msgModel != null) {
				responseMessages.put(msgModel.getId(), msgModel.getMessage());
				return msgModel.getMessage();
			}
		} catch (Exception e) {
			ErrorLogServices.logException(e, "response message");
		}
		return msg;
	}
}
