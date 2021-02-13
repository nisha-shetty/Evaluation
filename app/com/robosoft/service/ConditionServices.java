package com.robosoft.service;

import static com.robosoft.constants.ErrorResponseCodes.INVALID_INPUTS_STCODE;
import static com.robosoft.constants.ErrorResponseCodes.INVALID_USER_INPUTS_STATUS;

import com.robosoft.dto.ServiceResult;
import com.robosoft.models.UserModel;
import com.robosoft.service.commons.ResponseServices;

public class ConditionServices extends Service {

	public static ConditionServices instance;

	public static ConditionServices getInstance() {
		if (instance == null) {
			instance = new ConditionServices();
		}
		return instance;
	}




	public ServiceResult checkUserInputs(String input) {
		if(input == null || input.isEmpty()) {
			return createServiceResult(INVALID_INPUTS_STCODE,ResponseServices.getInstance().getResponseMessageSFromDB(INVALID_INPUTS_STCODE));
		}
		return null;			
	}



	public ServiceResult checkEuin(String euin) {
		if(euin == null || euin.isEmpty() || euin.charAt(0) != 'E') {
			return createServiceResult(INVALID_INPUTS_STCODE,ResponseServices.getInstance().getResponseMessageSFromDB(INVALID_INPUTS_STCODE));
		}
		return null;
	}



	public ServiceResult checkUserIntegerInputs(Integer input) {
		if(input == null ) {
			return createServiceResult(INVALID_INPUTS_STCODE,ResponseServices.getInstance().getResponseMessageSFromDB(INVALID_INPUTS_STCODE));
		}
		return null;		
	}
	
	

	
}
