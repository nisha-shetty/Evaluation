package com.robosoft.constants;

public class StatusConstants {

	/**************** Service Level Status Codes ****************/
	public static final String SERVICE_STATUS_SUCCESS = "000";
	public static final String SERVICE_STATUS_FAILURE = "999";
	/************** End Service Level Status Codes **************/

	/**************** Session ****************/
	public static final int SESSION_ACTIVE = 0;
	public static final int SESSION_CREATION_FAILED = -111;
	public static final int SESSION_TIMEOUT = -112;
	public static final int SESSION_NOT_EXISTS = -113;
	public static final int SESSION_ERROR = -114;
	/**************** End Session ****************/

	/**************** Authentication ****************/
	public static final int AUTH_STATUS_AUTHENTICATED = 0;
	public static final int AUTH_STATUS_FAILED = 1;
	public static final int AUTH_STATUS_USER_TEMP_BLOCKED = 2;
	public static final int AUTH_STATUS_PASSWORD_EXPIRED = 3;
	public static final int LOGOUT_STATUS_LOGGED_OUT = 0;

	public static final int L2_AUTH_REQUIRED = 1000;
	public static final int L2_AUTH_PASSED = 1001;
	public static final int L2_AUTH_FAILED = 1002;
	public static final int L2_AUTH_OTP_FAILED = -1100;
	public static final int L2_AUTH_MPIN_FAILED = -1200;

	public static final int SERVER_ERROR = -115;
	public static final int CHANNEL_ERROR = -131;
	public static final int RETRY_AUTH = -141;
	public static final int INCORRECT_OTP = -1100;
	public static final int OTP_BLOCKED = -1101;
	
	/**********************************************/
	// status constants
	public static final int SUCCESS_STATUS = 100;
	public static final int ARN_SUCCESS_STATUS = 106;
	public static final int FAILURE_STATUS = -105;
	public static final int FAILURE_ST = -105;
	public static final int OTP_FAILURE = -107;
	public static final int SUCCESS = 0;
	public static final int TOKEN_EXPIRED = 401;
	public static final int INVALID_AMOUNT = 17000; 
	public static final int INVALID_BROKER = 13006;
	
	public static final int NO_BANK_DETAIL = 21001;
	public static final int FOLIO_STATUS = 21002;
	public static final int NOT_EMPANELLED = -100;
	public static final int EMPANELLED_SUCCESS = -101;
	public static final int FAILED_EMPANELMENT = -104;

	public static final int EMPANELLED_STATUS = 0;
	public static final int EMPANELLED_FAILURE = -101;

	public static final int REGISTER_SUCCESS = 0;
	public static final int REGISTER_FAIL = 1;

	public static final int REGISTRATION_SUCCESS = 102;
	public static final int SOMETHING_WENT_WRONG = -103;

	public static final int ALREADY_REGISTERED = -1;

	public static final int PASSWORD_SET_STATUS = 400;
	public static final int PASSWORD_NOT_SET_STATUS = -400;

	public static final int MPIN_SET_STATUS = 500;
	public static final int MPIN_NOT_SET_STATUS = -500;

	public static final int PASSWORD_ALREADY_SET = -401;

	public static final int PAN_NOT_MATCHED = -600;
	public static final int FAILED = -1;
	public static final int ALREADY_PRESENT = -801;
	public static final int NAME_PRESENT = -802;
	
	

	// transactions
	public static final int TRANSACTION_CONFIRMATION_LINK_INVALID = -1000;
	public static final int TRANSACTION_CONFIRMATION_LINK_EXPIRED = -1001;
	
	
	public static final int PASSWORD_PATTERN_MISMATCH=-2001;

}
