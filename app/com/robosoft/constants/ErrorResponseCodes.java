package com.robosoft.constants;

public class ErrorResponseCodes {
private ErrorResponseCodes() {}
	
//common errors
	public static final int SERVER_ERROR_STCODE = -100;
	public static final int INVALID_INPUTS_STCODE = -101;
	public static final int BAD_REQUEST_STCODE = -102;
	public static final int MSG_NOT_AVAILABLE_STCODE = -103;
	public static final int INVALID_USER_INPUTS_STATUS = -104;

	// common error from IIB & BILLDESK
	public static final int SERVER_ERROR_STCODE_IIB = -200;
	public static final int SERVER_ERROR_STCODE_IIB_FALUT = -201;
	public static final int SERVER_ERROR_STCODE_IIB_GET_MSG_FAILURE = -202; 
	public static final int SERVER_ERROR_STCODE_SP = -205;
	public static final int SERVER_ERROR_STCODE_SP_FAULT = -206;

	public static final int FAILURE_STATUS = -105;
	public static final int SERVICE_SUCCESS = 100;
	public static final int REGION_NOT_AVAILABLE = -300;
	public static final int DATA_NOT_FOUND = -204;
	public static final int  NULL_DEV_ID = -501;
	
	

	// input validation
	public static final int VALIDATION_ERROR_STCODE = -300;
	public static final int VALIDATION_ERROR_RUNTIME_STCODE = -301;
	public static final int VALIDATION_ERROR_JSON_PARSING_STCODE = -302;
	public static final int VALIDATION_SUCCESS_STCODE = 303;

	// common
	public static final int ATTEMPT_REMAINING_TAIL = 1001;
	public static final int ATTEMPT_REMAINING_TAIL_M = 1002;

	// Request Authentication
	public static final int AUTH_L2_FAILED_STCODE = -2120;
	public static final int L2_AUTH_INCORRECT_PART_1 = -2125;
	public static final int AUTH_L2_FAILED_PART_1_STCODE = -2126;
	public static final int AUTH_L2_FAILED_PART_2_STCODE = -2127;
	public static final int AUTH_L2_REQUIRED_PART_1_STCODE = -2128;
	public static final int AUTH_L2_REQUIRED_PART_2_STCODE = -2129;

	// registration
	public static final int REG_FAIL_STCODE = -2001;
	public static final int CUSTOMER_NOT_FOUND_STCODE = -2002;
	public static final int CUSTOMER_NOT_VERIFIED_OTP_STCODE = -2003;
	public static final int CUSTOMER_NOT_VERIFIED_ATM_STCODE = -2004;
	public static final int REG_ALREADY_EXIST_STCODE = -2005;
	public static final int ACT_FAIL_STCODE = -2006;
	public static final int INVALID_OTP_STCODE = -2007;
	public static final int INVALID_PASSWORD_STCODE = -2008;
	public static final int OTP_EXPIRED_STCODE = -2009;
	public static final int MPIN_DOES_NOT_MATCH_STCODE = -2010;
	public static final int OTP_BLOCKED_STCODE = -2011;
	public static final int INVALID_ATM_DETAILS_STCODE = -2012;
	public static final int CUSTOMER_NOT_FOUND_WITH_DEVICE_STCODE = -2013;
	public static final int INVALID_MPIN_STCODE = -2014;

	public static final int REG_SUCCESS_STCODE = 2001;
	public static final int ACT_SUCCESS_STCODE = 2002;

	// login
	public static final int AUTH_ERROR_STCODE = -2100;
	public static final int AUTH_FAILED_STCODE = -2101;

	public static final int AUTH_USER_TEMP_BLOCKED_STCODE = -2102;
	public static final int AUTH_USER_BLOCKED_STCODE = -2103;
	//public static final int AUTH_PASSWORD_NOT_MATCHING_STCODE = -2104;

	public static final int AUTH_CHANGE_MPIN_FAILED_STCODE = -2105;
	public static final int AUTH_CHANGE_MPIN_ERR_2_STCODE = -2106;
	public static final int AUTH_CHANGE_MPIN_CUR_NEW_SAME_STCODE = -2107;
	public static final int AUTH_CHANGE_PASS_FAILED_STCODE = -2108;
	public static final int AUTH_CHANGE_PASS_ERR_2_STCODE = -2109;
	public static final int AUTH_CHANGE_PASS_CUR_NEW_SAME_STCODE = -2110;


	public static final int AUTH_FORGOT_MPIN_FAILED_STCODE = -2112;

	public static final int SESSION_CRESTION_FAILED_STCODE = -2113;

	public static final int MPIN_CHANGE_STATUS_CURR_MPIN_MATCH_FAILED_STCODE = -2114;
	public static final int PASS_CHANGE_STATUS_CURR_PASS_MATCH_FAILED_STCODE = -2115;

	public static final int AUTH_PASSWORD_EXPIRED_STCODE = -2116;

	public static final int AUTH_FORGOT_PASSWORD_FAILED_STCODE = -2117;
	public static final int AUTH_FORGOT_PASSWORD_CUSTOMER_NOT_YET_ACTIVATED_STCODE = -2118;
	public static final int AUTH_FORGOT_PASSWORD_NOT_A_VALID_DEVICE_STCODE = -2119;

	public static final int AUTH_USER_IS_NOT_BLOCKED_STCODE = -2123;
	public static final int AUTH_USER_IS_NOT_UNBLOCKED_STCODE = -2124;
	public static final int AUTH_SERVER_ERROR_STCODE = -2130;
	public static final int AUTH_SERVER_ERROR_MOBILE_NUMBER_CHANGED = -2131;

	public static final int AUTH_USER_IS_BLOCKED_STCODE = 2108;
	public static final int AUTH_USER_IS_UNBLOCKED_STCODE = 2109;

	public static final int AUTH_CREATE_MPIN_USING_TEMPIN_FAILED_STCODE = -2151;
	public static final int AUTH_CREATE_MPIN_USING_WRONG_TEMPIN_FAILED_STCODE = -2152;

	public static final int AUTH_CHANGE_PASS_BLOKED_STCODE = -2121;
	public static final int AUTH_CHANGE_MPIN_BLOKED_ON_MAX_WRONG_ATTEMPT_STCODE = -2122;

	public static final int AUTH_AUTHENTICATED_STCODE = 2100;
	public static final int AUTH_CHANGE_MPIN_SUCCESS_STCODE = 2101;
	public static final int AUTH_CHANGE_PASS_SUCCESS_STCODE = 2102;

	public static final int AUTH_FORGOT_PASSWORD_REQUESTED_STCODE = 2104;
	public static final int AUTH_SET_PASSWORD_SUCCESS_STCODE = 2105;
	public static final int AUTH_FORGOT_MPIN_REQUESTED_STCODE = 2106;
	public static final int AUTH_SET_MPIN_SUCCESS_STCODE = 2107;

	public static final int RESET_TEMPPIN_SUCCESS_STCODE = 2153;
	public static final int RESET_TEMPPIN_FAILURE_STCODE = -2153;

	// Account
	public static final int LINK_ACCOUNT_FAILED_MSG_STCODE = -2200;
	public static final int LINK_ACCOUNT_INVALID_ACCOUNT_STCODE = -2201;
	public static final int INVALID_ACCOUNT_STCODE = -2202;
	public static final int INVALID_ACCOUNT_REG_STCODE = -2203;
	public static final int INVALID_ACCOUNT_BEN_STCODE = -2204;
	public static final int CHEQUE_BOOK_REQ_SUBMIT_FAILURE_STCODE = -2205;
	public static final int LINK_ACCOUNT_CAN_NOT_LINK_STCODE = -2206;
	public static final int ACCOUNT_TYPE_NOT_FOUND_STCODE = -2207;
	public static final int CUSTOMER_ALREADY_REGISTERED_STCODE = -2208;
	public static final int ACCOUNT_NOT_FOUND_STCODE = -2209;

	public static final int LINK_ACCOUNT_SUCCESS_MSG_STCODE = 2200;
	public static final int LINKED_ACCOUNT_ALREADY_EXISTS_STCODE = 2201;
	public static final int CHEQUE_BOOK_REQ_SUBMIT_SUCCESS_STCODE = 2203;

	// MMID
	public static final int IMPS_MMID_GEN_ERR_2_MSG_STCODE = -2300;
	public static final int IMPS_MMID_GEN_FAILED_MSG_STCODE = -2301;
	public static final int IMPS_MMID_GET_FAILED_MSG_STCODE = -2302;
	public static final int IMPS_MMID_CAN_FAILED_MSG_STCODE = -2303;

	public static final int IMPS_MMID_CAN_CANCELLED_MSG_STCODE = 2300;
	public static final int IMPS_MMID_GENRATED_MSG_STCODE = 2301;
	public static final int IMPS_MMID_STATUS_ACTIVE = 2302;
	public static final int IMPS_MMID_STATUS_CANCELLED = 2303;
	public static final int IMPS_MMID_GEN_STATUS_GENERATED = 2304;
	public static final int IMPS_MMID_CAN_STATUS_CANCELLED = 2305;

	// IMPS P2A
	public static final int IMPS_P2A_GET_MSG_FAILED_STCODE = -2400;
	public static final int IMPS_P2A_FAILED_STCODE = -2401;
	public static final int IMPS_P2A_SUCCESS_STCODE = 2401;

	// Beneficiary
	public static final int BNF_NO_BNF_FOUND_STCODE = -3001;
	public static final int BNF_GET_FAILURE_STCODE = -3002;
	public static final int BNF_ADD_FAILURE_STCODE = -3003;
	public static final int BNF_EDIT_FAILURE_STCODE = -3004;
	public static final int BNF_DELETE_FAILURE_STCODE = -3005;
	public static final int BNF_UPDATE_FAILURE_STCODE = -3006;
	public static final int BNF_ACNO_EXISTING_STCODE = -3007;
	public static final int BNF_NICKNAME_EXISTING_STCODE = -3008;
	public static final int HOME_BANK_IFSC_STCODE = -3009;

	public static final int BNF_ADD_SUCCESS_STCODE = 3001;
	public static final int BNF_EDIT_SUCCESS_STCODE = 3002;
	public static final int BNF_DELETE_SUCCESS_STCODE = 3003;
	public static final int BNF_UPDATE_SUCCESS_STCODE = 3006;
	public static final int BNF_ADD_ACTIVATE_STCODE = 3009;
	public static final int BNF_ADD_SMS_STCODE = 3010;
	public static final int BNF_EDIT_SMS_STCODE = 3011;

	// Fund Transfer

	public static final int FUND_TRNS_STATUS_FAILED_STCODE = -3100;
	public static final int FUND_TRNS_ERR_CODE_INVALID_REF_STCODE = -3102;
	public static final int FUND_TRNS_ERR_CODE_CANNOT_CONFIRM_STCODE = -3103;
	public static final int FUND_TRNS_ERR_CODE_BANK_REJECT_STCODE = -3104;
	public static final int FUND_TRNS_RECUR_SAVE_FAILURE_STCODE = -3105;
	public static final int FUND_TRNS_RECUR_GET_FAILURE_STCODE = -3106;
	public static final int FUND_TRNS_RECUR_EMPTY_GETLIST_STCODE = -3107;
	public static final int FUND_TRNS_RECUR_UPDATE_FAILURE_STCODE = -3110;
	public static final int FUND_TRNS_RECUR_DELETE_FAILURE_STCODE = -3111;
	public static final int FUND_TRNS_RECUR_INPUT_ERR_RECUR_DATE_INVALID_STCODE = -3112;
	public static final int FUND_TRNS_INPUT_ERR_SAME_SRC_DEST_ACNO_STCODE = -3113;
	public static final int FUND_TRNS_INPUT_ERR_INCOMPATIBLE_ACNOS_STCODE = -3114;
	public static final int FUND_TRNS_FAILURE_STCODE = -3115;
	public static final int FUND_TRNS_INPUT_ERR_INCOMPATIBLE_ACNOS1_STCODE = -3116;
	public static final int FUND_TRNS_RECUR_EXCEEDS_LIMIT = -3117;
	public static final int FUND_TRNS_INPUT_ERR_SIBL_STCODE = -3118;
	public static final int FUND_TRNS_CUSTOMER_ID_FAILURE_STCODE = -3119;
	public static final int FUND_TRNS_OPERATION_MODE_FAILURE_STCODE = -3120;

	public static final int FUND_TRNS_STATUS_INITIATED_STCODE = 3100;
	public static final int FUND_TRNS_STATUS_CONFIRMED_STCODE = 3101;
	public static final int FUND_TRNS_STATUS_ACCEPTED_STCODE = 3102;
	public static final int FUND_TRNS_SUCCESS_STCODE = 3103;

	public static final int FUND_TRNS_STATUS_ERROR_STCODE = 3105;
	public static final int FUND_TRNS_RECUR_DEL_STCODE = 3106;
	public static final int FUND_TRNS_RECUR_EDT_STCODE = 3107;
	public static final int FUND_TRNS_RECUR_SAVE_SUCCESS_STCODE = 3108;
	public static final int FUND_TRNS_RECUR_GET_SUCCESS_STCODE = 3109;
	public static final int FUND_TRNS_RECUR_UPDATE_SUCCESS_STCODE = 3110;
	public static final int FUND_TRNS_RECUR_DELETE_SUCCESS_STCODE = 3111;
	public static final int FUND_TRNS_SUCCESS_SMS = 3121;
	public static final int FUND_TRNS_FAIL_SMS = 3122;
	public static final int FUND_TRNS_COMPLETE_FAIL_SMS = 3123;
	public static final int RECUR_FUND_TRNS_SUCCESS_SMS = 3124;
	public static final int RECUR_FUND_TRNS_FAIL_SMS = 3125;
	public static final int RECUR_FUND_TRNS_COMPLETE_FAIL_SMS = 3126;
	public static final int FUND_TRNS_UTILITY_SUCCESS_SMS = 3127;
	public static final int FUND_TRNS_UTILITY_FAIL_SMS = 3128;
	public static final int FUND_TRNS_UTLITY_COMPLETE_FAIL_SMS = 3129;

	public static final int IMPS_FUND_TRNS_SUCCESS_SMS = 3130;
	public static final int IMPS_FUND_TRNS_FAIL_SMS = 3131;
	public static final int IMPS_FUND_TRNS_COMPLETE_FAIL_SMS = 3132;

	public static final int RECUR_IMPS_FUND_TRNS_SUCCESS_SMS = 3133;
	public static final int RECUR_IMPS_FUND_TRNS_FAIL_SMS = 3134;
	public static final int RECUR_IMPS_FUND_TRNS_COMPLETE_FAIL_SMS = 3135;

	public static final int FUND_TRNS_AMOUNT_LIMIT_EXEEDED_STCODE = -3200;
	public static final int FUND_TRNS_AMOUNT_DAY_LIMIT_EXEEDED_STCODE = -3210;
	public static final int BANK_HOLIDAY_STCODE = -3311;
	public static final int BANK_NEFT_TIME_OVER_STCODE = -3312;
	public static final int RECUR_NO_TRANSFER_STCODE = -3313;
	public static final int RECUR_FAILED_TO_TRIGGER_STCODE = -3314;

	// feedback
	public static final int FEED_SUBMIT_FAILURE_STCODE = -4001;

	public static final int FEED_SUBMIT_SUCCESS_STCODE = 4001;

	// Grievance
	public static final int GRVN_SUBMIT_FAILURE_STCODE = -4100;
	public static final int GRVN_GET_FAILURE_STCODE = -4101;
	public static final int GRVN_GET_NO_GRVN_FOUND_STCODE = 4102;
	public static final int GRVN_DEL_FAILURE_STCODE = -4103;

	public static final int GRVN_SUBMIT_SUCCESS_STCODE = 4100;
	public static final int GRVN_DEL_SUCCESS_STCODE = 4103;

	// locations
	public static final int BAL_NO_LOCATIONS_FOUND_STCODE = 4200;

	// user
	public static final int USER_PROFILE_GET_FAILED_STCODE = -4300;

	// push
	public static final int PUSH_SUB_SUCCESS_STCODE = 4400;
	public static final int PUSH_SUB_FAILURE_STCODE = 4401;
	public static final int PUSH_GET_SUCCESS_STCODE = 4402;
	public static final int PUSH_GET_FAILURE_STCODE = -4403;

	// IIB

	public static final int IIB_ERROR_CODE_MQ_GET_FAILURE_ST_CODE = -4902;
	public static final int IIB_ERROR_CODE_MQ_PUT_FAILURE_ST_CODE = -4901;

	// IFSC
	public static final int IFSC_NO_BANKS_FOUND_STCODE = 5000;
	public static final int INVALID_IFSC_STCODE = -5001;

	// Utility Services
	public static final int US_MOBILE_RECHARGE_SUCCESS_STCODE = 6000;
	public static final int US_MOBILE_RECHARGE_FAILURE_STCODE = 6001;
	public static final int US_MOBILE_RECHARGE_PENDING_STCODE = 6002;
	public static final int US_MOBILE_RECHARGE_VAL_FAILURE_STCODE = -6003;
	public static final int US_MOBILE_RECHARGE_PAYMENT_FAILURE_STCODE = -6004;

	// account open
	public static final int ACCOUNT_OPEN_FAIL_STCODE = -7001;
	public static final int ACCOUNT_OPEN_SUCCESS_STCODE = 7001;

	// expenses
	public static final int ACCOUNT_EXPENSES_FAIL_STCODE = -7501;
	public static final int ACCOUNT_EXPENSES_NO_TRANSACTIONS_FAIL_STCODE = -7502;

	public static final int BILL_FETCH_FAIL_STCODE = -8001;

	public static final int BILLER_ADD_SUCCESS_STCODE = 9001;
	public static final int BILLER_ADD_FAIL_STCODE = -9001;
	public static final int BILLER_DELETE_SUCCESS_STCODE = 9002;
	public static final int BILLER_DELETE_FAIL_STCODE = -9002;
	public static final int BILL_PAY_SUCCESS_STCODE = 9003;
	public static final int BILL_PAY_FAIL_STCODE = -9003;

	public static final int ELOCK_STATUS_MODIFIED_SUCCESS_STCODE = 10001;
	public static final int ELOCK_STATUS_MODIFIED_FAIL_STCODE = -10001;

	public static final int BHARAT_QR_SUCCESS_STCODE = 11001;
	public static final int BHARAT_QR_FAILURE_STCODE = -11001;

	// KYC status
	public static final int KYC_STATUS_FAIL_STCODE = -12001;
	public static final int INVALID_KYC_STATUS = -12002;
	public static final int KYC_STATUS_SUCCESS_STCODE = 12001;

	// Biller status
	public static final int BILLER_GETBILL_FAIL_STCODE = -13001;
	public static final int BILLER_PAYBILL_FAIL_STCODE = -13002;
	public static final int BILLER_GENERAL_ERROR = -13003;

	// Transaction
	public static final int RECEIVER_NOT_REG_STCODE = -1300;
	public static final int INSUFFICIENT_SRC_ACC_BAL_STCODE = -1301;
	public static final int TRANSACTION_ID_NOT_FOUND = -1302;
	
	// region
	public static final int REGION_EXIST_STCODE = -2153;
	public static final int REGION_NOT_FOUND_STCODE = -2154;
	public static final int DATA_IGNORED = -105;
	public static final int REGION_NULL_STCODE = -21521;
	

	//branch
	public static final int BRANCH_EXIST_STCODE = -2155;
	public static final int BRANCH_NOT_FOUND_STCODE = -2156;
	
	//department
	public static final int DEPARTMENT_NOT_FOUND_STCODE = -2157;
	public static final int DEPARTMENT_EXIST_STCODE = -2258;
	public static final int DEPARTMENT_NULL_STCODE = -22591;
	
	//role
	public static final int ROLE_EXIST_STCODE = -2151;
	public static final int ROLE_NOT_FOUND_STCODE = -2152;
	public static final int ROLE_NULL_STCODE = -21511;
	
	
	//feature
	public static final int FEATURES_NOT_AVAILABLE_STCODE = -2150;
	
	//user
	public static final int USER_EXIST_STCODE = -2157;
	public static final int USER_NOT_FOUND_STCODE = -2158;
	public static final int USER_BLOCKED_STCODE = -2159;
	public static final int FAILED_ST_CODE = -1100;
	
	public static final int EMAIL_EXIST_STCODE = -2160;
	public static final int USERiD_EXIST_STCODE = -2161;
	public static final int USERNAME_EXIST_STCODE = -2164;
	
	public static final int USER_NOT_PRESENT = -2162;
	
	public static final int USER_AVAILABLE_IN_TAG =-2163;
	
	//auth
	public static final int AUTH_PASSWORD_NOT_MATCHING_STCODE = -2105;
	public static final int INVALID_AUTH_STCODE = -2110;
	
	public static final int INVALID_SESSION_STCODE = -2106;
	
	public static final int AUTH_LOGOUT_SUCCESS_STCODE = 2101;
	public static final int AUTH_LOGOUT_FAILED_STCODE = -2103;
	
	public static final String DOMAIN_SETTING = "com.rs.suvarnacms.setting";
	
	public static final int DEVICEID_NULL = -2111;
	public static final int DEVICEID_NOT_FOUND = -2099;

	public static final int INVALID_ROLE_ASSIGNED_STCODE = -2107;
	public static final int INVALID_ACCESS_TOKEN_STCODE = -2108;
	
	//password
	public static final int SET_PASSWORD_OTP_NOT_VALIDATED = -4002;
	public static final int PASSWORD_MISMATCH = -4009;
	 public static final int PASSWORD_PATTERN_MISMATCH = -6012;
	
	//TAG
	public static final int TAG_EXIST_STCODE = -5000;
	public static final int TAG_NOT_FOUND_STCODE = -5001;

	
	//PUSH NOTIFICATIONS
	public static final int PUSH_NOTIFICATION_ADDED_SUCCESSFULLY = 7004;
	public static final int DELETE_PUSH_NOTIFICATION_ERROR=-7000;
	public static final int SAVE_PUSH_NOTIFICATION_ERROR=-7002;
	public static final int SEND_PUSH_NOTIFICATION_ERROR=-7003;
	public static final int PUSH_NOTIFICATION_SENT_SUCCESSFULLY = 7005;
	public static final int INVALID_SECTION = -5002;
	
	public static final int PDF_EXIST = -5003;
	public static final int PDF_NOT_AVAILABLE = -5004;
	public static final int VIDEO_NOT_AVAILABLE = -5005;
	public static final int PDF_ADDED_SUCCESSFULLY = 5003;
	public static final int PDF_UPDATED_SUCCESSFULLY = 5004;

	public static final int PDF_DELETED_SUCCESSFULLY = 5005;


	//Images
	public static final int IMAGE_ADDED_SUCCESSFULLY = 7500;
	public static final int EXCEL_ADDED_SUCCESSFULLY = 7501;
	public static final int VIDEO_ADDED_SUCCESSFULLY = 7502;
	public static final int VIDEO_UPDATED_SUCCESSFULLY = 7503;
	public static final int VIDEO_DELETED_SUCCESSFULLY = 7504;

	//Section subsection
	public static final int SECTION_ADDED_SUCCESSFULLY = 7600;
	public static final int SECTION_UPDATED_SUCCESSFULLY = 7601;
	public static final int SECTION_DELETED_SUCCESSFULLY = 7602;
	public static final int SUB_SECTION_ADDED_SUCCESSFULLY = 7603;
	public static final int SUB_SECTION_UPDATED_SUCCESSFULLY = 7604;
	public static final int SUB_SECTION_DELETED_SUCCESSFULLY = 7605;
	
	public static final int UPLOAD_IN_PROGRESS = 2050;

}
