package com.robosoft.constants;

import static com.robosoft.constants.Messages.AUTHENTICATION_MESSAGE;

import java.util.Map;

import com.robosoft.service.ErrorLogServices;

public class AppConstants {
	private AppConstants() {
	}

	public static Map<String, String> appSettings;
	public static Map<String, String> hashKeys;
	public static String SECRET_KEY_DEFAULT = "a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p6q7r8s9t0u1v2w3x4y5z6";

	public static final Integer MASTER_ADMIN_ID = 3;
//SESSION_CLEANER_INTERVAL_IN_MINS
	public static final String SESSION_CLEANER_INTERVAL_IN_MINS = "SESSION_CLEANER_INTERVAL_IN_MINS";
	public static final String SESSION_TIMEOUT_IN_MINS = "SESSION_TIMEOUT_IN_MINS";
	public static final String EXCEL_UPLOAD_INTERVAL_IN_HOURS = "EXCEL_UPLOAD_INTERVAL_IN_HOURS";
	public static final String CREATE_SESSION_RETRY_MAX_COUNT = "CREATE_SESSION_RETRY_MAX_COUNT";
	public static final String MAX_ALLOWED_FAILED_LOGIN_ATTEMPT = "MAX_ALLOWED_FAILED_LOGIN_ATTEMPT";

	public static final String MOBILE_CHANNEL_ID = "352";

	public static final int TYPE_REFERENCE_NO = 1;
	public static final int DEFAULT_REF_NUMBER_PART_WIDTH = 8;

	public static final String NAVIGATION_PATH_IMAGE = "image";
	public static final String NAVIGATION_PATH_DOCUMENT = "doc";
	public static final String UNIX_PATH_SEPARATOR = "/";
	public static final String IMAGE_DIR_PATH = "IMAGE_DIR_PATH";
	public static final String DOCUMENT_DIR_PATH = "DOCUMENT_DIR_PATH";
 
	public static final String FILE_SERVER_ROOT_PATH = "FILE_SERVER_ROOT_PATH";
	public static final String FILE_SERVER_ROOT_PATH_DEV = "FILE_SERVER_ROOT_PATH_DEV";

	public static final String FILE_SERVER_BASE_URL = "FILE_SERVER_BASE_URL";
	
	public static final String API_SERVER_PATH_PREPIX = "API_SERVER_PATH_PREPIX";
	public static final String API_SERVER_PORT = "API_SERVER_PORT";
	public static final String API_SERVER_IP = "API_SERVER_IP";
	public static final String FILE_SERVER_PATH_PREPIX_DEV = "FILE_SERVER_PATH_PREPIX_DEV";
	public static final String FILE_SERVER_PATH_PREPIX = "FILE_SERVER_PATH_PREPIX";
	public static final String IMAGE_URL="http://localhost:9000/api/v1/image/";
	
	
	public static final String DEFAULT_SCHEME_FOR_LATEST_NAV = "DEFAULT_SCHEME_FOR_LATEST_NAV";
	public static final String DEFAULT_PLAN_FOR_LATEST_NAV = "DEFAULT_PLAN_FOR_LATEST_NAV";
	
	//public static final String TRANS_CONFIRMATION_LINK = "<<shouldFetchFromAppSettingsTable>>";// should read from app settings
	//public static final String TRANS_CONFIRMATION_LINK = "https://uattestserver.robosoftin.com/#/transaction-approval";// should read from app settings
	public static final String TRANS_CONFIRMATION_LINK = "http://localhost:8100/#/transaction-approval";
	public static final Integer TRANS_CONFIRMATION_LINK_TTL_IN_HOURS = 72;// should read from app settings

	public static final String IMAGE_FILE_SIZE = "IMAGE_FILE_SIZE";
	public static final String PUSH_SERVER_KEY ="PUSH_SERVER_KEY";
	public static final String PUSH_NOTIFICATION_STATUS="PUSH_NOTIFICATION_STATUS";
	public static final String  PUSH_IMAGE_DIR_PATH="PUSH_IMAGE_DIR_PATH";
	public static final String PUSH_IMAGE_END_POINT="PUSH_IMAGE_END_POINT";
	public static final String BASE_URL="BASE_URL";
	public static final String MAXIMUM_FAILURE_LOGIN_ATTEMPT_COUNT="MAXIMUM_FAILURE_LOGIN_ATTEMPT_COUNT";
	public static final String TIME_STAMP_DIFFERENCE="TIME_STAMP_DIFFERENCE";
	public static final String EXCEL_DIR_PATH = "EXCEL_DIR_PATH";
	public static final String MAX_NO_OF_TRENDING_FUNDS = "MAX_NO_OF_TRENDING_FUNDS";
	public static final String MIN_NO_OF_TRENDING_FUNDS = "MIN_NO_OF_TRENDING_FUNDS";
	public static final String IMAGE_DOWNLOAD_API = "IMAGE_DOWNLOAD_API";
	//public static final boolean IS_LOGGING_ENABLED = Boolean.valueOf(Play.application().configuration().getString("IS_LOGGING_ENABLED"));
	public static final String PDF_FILE_DOWNLOAD_PATH = "PDF_FILE_DOWNLOAD_PATH";
	public static final String JEETH_BASE_URL = "JEETH_BASE_URL";

	
	
	public static Integer getValueInInteger(String key) {
		Integer value = null;
		try {
			value = Integer.valueOf(getValue(key));
		} catch (Exception e) {
			ErrorLogServices.logException(e, AUTHENTICATION_MESSAGE);
		}
		return value;
	}

	public static String getValue(String key) {
		String value = "";
		try {
			value = AppConstants.appSettings.get(key);
		} catch (Exception e) {
			ErrorLogServices.logException(e, AUTHENTICATION_MESSAGE);
		}
		return value;
	}
	
	//encryption
		public static final String ENABLE_ENC = "ENABLE_ENC";
		public static final String AES_ENCRYPTION_KEY = "AES_ENCRYPTION_KEY";
		public static final String AES_IVECTOR_KEY = "AES_IVECTOR_KEY";
		public static final String AES_PADDING_KEY = "AES_PADDING_KEY";

}
