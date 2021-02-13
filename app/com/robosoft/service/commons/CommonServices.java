package com.robosoft.service.commons;

import static com.robosoft.constants.ErrorResponseCodes.MSG_NOT_AVAILABLE_STCODE;
import static com.robosoft.constants.AppConstants.FILE_SERVER_ROOT_PATH;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.robosoft.models.AppSettingsModel;
import com.robosoft.models.ResponseMessageModel;
import play.Logger;

import com.robosoft.constants.AppConstants;
import com.robosoft.dto.ServiceResult;
import com.robosoft.service.Service;
import com.robosoft.servicesDAO.CommonServicesDAO;

public class CommonServices extends Service {

	private static CommonServices instance;
	private static String KEY = "TEST";

	public static CommonServices getInstance() {
		if (instance == null)
			instance = new CommonServices();
		return instance;
	}

	private CommonServices() {
	}

	public Map<String, String> loadAppSettings() {
		List<AppSettingsModel> appSettingsList = CommonServicesDAO.getInstance().getAppSettings();
		if (appSettingsList != null && !appSettingsList.isEmpty()) {
			Map<String, String> tmpMap = new HashMap<String, String>();
			for (AppSettingsModel setting : appSettingsList) {
				tmpMap.put(setting.getCode(), setting.getValue());
			}
			return Collections.unmodifiableMap(tmpMap);
		}
		return new HashMap<String, String>();
	}

	public Map<Integer, String> loadResponseMessages() {
		List<ResponseMessageModel> messageList = CommonServicesDAO.getInstance().getResponseMessages();
		try {
			if (messageList != null && !messageList.isEmpty()) {
				Map<Integer, String> tmpMap = new HashMap<Integer, String>();
				for (ResponseMessageModel msg : messageList) {
					tmpMap.put(msg.getKey(), msg.getMessage());
				}
				return Collections.unmodifiableMap(tmpMap);
			}
		} catch (Exception e) {
			Logger.debug("Error while loading response message");
			e.printStackTrace();
		}
		return new HashMap<Integer, String>();
	}

	public ResponseMessageModel getResponseMessageFromDB(Integer key) {
		try {
			return CommonServicesDAO.getInstance().getResponseMessage(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public ServiceResult verifyTimeStampDifference(boolean status){

			return createServiceResult(MSG_NOT_AVAILABLE_STCODE);
		
	}
	
	public void addExcel() {
	}
}
