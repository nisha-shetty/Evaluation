package com.robosoft.constants;

import static com.robosoft.constants.AppConstants.appSettings;

public class CBSConstants {
	private CBSConstants() {
	}

	// header keys
	public static final String USER_ID_KEY = "UserId";
	public static final String CBS_PASS_KEY = "Password";

	// content type
	public static final String CONTENT_TYPE_JSON = "application/json";
	public static final String CONTENT_TYPE_XML = "application/xml";

	
	public static final String PAN_CHECK_KEY = "panNo";
		
	public static final String SOLICIT_XML_KEY = "inputXML";
		
				
	public static final String APP_USER_NAME = "userName";
	public static final String APP_POS_CODE = "PosCode";
	public static final String PASSVALUE_KEY = "password";
	public static final String PASS_KEY = "PassKey";
		
		
	// arn details header keys
	public static final String APP_USER_NAME_KEY = "APP_USER_NAME";
	public static final String APP_PASS_KEY = "APP_PWD";
	public static final String ARN_NUMBER_KEY = "ARN_NUMBER";
	public static final String ARN_KEY = "arn";
	public static final String PAN_KEY = "PAN";
	public static final String EMAIL_KEY = "EMAIL";
	public static final String MOBILE_NO_KEY = "MOBILE_NO";

	// arn details header values
	public static final String APP_USER_NAME_VALUE = "AXISMF";
	public static final String APP_PASS_VALUE = "chennai$5";

	// cbs values
	public static final String SOURCE_VALUE = "AW";
	public static final String FLAG_VALUE = "MP";

	// public static final String JEETH_BASE_URL =
	// appSettings.get(AppConstants.JEETH_BASE_URL);

	// jeeth cbs base url
	public static final String JEETH_BASE_URL = appSettings.get(AppConstants.JEETH_BASE_URL);

	public static final String JEETH_CBS_STATE_URL = JEETH_BASE_URL + "StateCityList";

	public static final String JEETH_CBS_STATUTORY_URL = "https://investonline.itimf.com/itimfapiuat/Itimfservice.svc/GetValidateExistingInvestor";//JEETH_BASE_URL + "GetValidateExistingInvestor";
}
