package com.robosoft.responses;


public class ResponseConstants {

	public static final String SUCCESS = "Success";
	public static final String FAILURE = "Failure";
	public static final String STATUS = "status";
	public static final String MESSAGE = "message";
	public static final String CODE = "code";
	public static final String DATA = "data";
	public static final int SUCCESS_STATUS = 0;
	public static final int FAILURE_STATUS = -1;
	public static final String SERVER_ERROR = "Server error occured";
	public static final String BAD_REQUEST = "Bad Request";
	public static final String USER_EXISTS = "Mobile Number / Email Id already exists";
	public static final String INTERNAL_SERVER_ERROR="Internal Server Error";
	public static final String CARD="Card";
	public static final String ACCOUNT="Account";
	public static final String DBNAME="default";
	public static final String REQUESTED="Requested";
	public static final String DATEFORMAT="yyyy-MM-dd HH:mm:ss";
	public static final String ORACLEDATEFORMAT="dd-MMM-yy hh.mm.ss.SSSSSSSSS aaa";
	public static final String REQUEST_STRING = "Request";
	public static final String SEND_STRING = "Send";
	public static final String POST_STRING = "POST";
	public static final String GET_STRING = "GET";
	public static final String WEEKLY_STRING = "Weekly";
	public static final String MONTHLY_STRING = "Monthly";
	public static final String REQUESTED_STRING = "Requested";
		
	public static final int STATUS_NO_SESSION = 101;
	public static final int STATUS_INVALID_PARAMS = 102;
	public static final int STATUS_ERROR = 103;
	public static final int STATUS_OK = 100;
	public static final int STATUS_CREATED = 201;
	public static final int STATUS_NO_CONTENT = 204;
	public static final int STATUS_NO_URL_FOUND = 404;
	public static final int STATUS_BAD_REQUEST = 400;
	public static final int STATUS_UNAUTHORIZED = 401;
	public static final int STATUS_INTERNAL_SERVER_ERROR =500;
	public static final int STATUS_MISSING_PARAMETERS = 999;
	
	/*
	 * Registration
	 */
	public static final int STATUS_SMS_COULD_NOT_SENT =-1101;
	public static final int STATUS_SMS_SENT =1101;
	public static final int STATUS_WRONG_OTP =-1102;
	public static final int STATUS_VALID_OTP =1102;
	public static final int STATUS_OTP_EXPIRED =-1103;
	public static final int STATUS_EMAIL_EXISTS =-1104;
	public static final int STATUS_VIRTUAL_ADDRESS_EXISTS =-1105;
	public static final int STATUS_REGISTRATION_FAILED =-1106;
	public static final int STATUS_RESEND_OTP =1107;
	public static final int STATUS_OTP_SENT =1108;
	public static final int STATUS_OTP_CONFIRMED =1109;
	public static final int STATUS_REGISTRATION_SUCCESSFUL = 1106;
	public static final int STATUS_VALID_CREDENTIALS= 1111;
	public static final int STATUS_INVALID_CREDENTIALS = -1111;
	public static final int STATUS_INVALID_MOBILE_NUMBER =-1113;
	public static final int STATUS_VALID_MOBILE_NUMBER =1113;
	
	
	/**Status Codes**/
	
	public static final int STATUS_OTHER_BANK_ACCOUNT_LINKED =1201;
	public static final int STATUS_OTHER_BANK_ACCOUNT_NOTLINKED =-1201;
	public static final int STATUS_OTHER_BANK_ACCOUNT_EXISTS =-1202;
	public static final int STATUS_SET_PRIMARY_ACCOUNT_CARD_SUCCESS =1203;
	public static final int STATUS_SET_PRIMARY_ACCOUNT_CARD_FAILURE =-1203;
	public static final int STATUS_INVALID_MMID =-1204;
	public static final int STATUS_VALID_MMID =1204;
	public static final int STATUS_OTHER_BANK_CARD_LINKED =1205;
	public static final int STATUS_OTHER_BANK_CARD_NOTLINKED =-1205;
	public static final int STATUS_OTHER_BANK_CARD_EXISTS =-1206;

		
	/**Status Msg**/
	public static final String SMS_COULD_NOT_SENT ="SMS Not Sent";
	public static final String WRONG_OTP ="Invalid OTP";
	public static final String OTP_EXPIRED ="OTP Expired";
	public static final String EMAIL_EXISTS ="Email alredy exists. Please try with differnt Email Address!";
	public static final String VIRTUAL_ADDRESS_EXISTS ="Virtual Address alredy exists. Please try with differnt Virtual Address!";
	public static final String REGISTRATION_FAILED ="Registration Failed";
	public static final String RESEND_OTP ="An OTP has been resent to you";
	public static final String OTP_SENT ="An OTP has been sent to you";
	public static final String OTP_CONFIRMED ="Your number has been confirmed";
	public static final String REGISTRATION_SUCCESSFUL = "Registraion Successful";
	public static final String VALID_CREDENTIALS = "Valid Credentials";
	public static final String INVALID_CREDENTIALS = "Invalid Credentials";
	public static final String OTHER_BANK_ACCOUNT_LINKED ="Account Linked ";
	public static final String OTHER_BANK_ACCOUNT_EXISTS ="Account Exists";
	public static final String SET_PRIMARY_ACCOUNT_CARD_SUCCESS ="Set Primary Successful";
	public static final String SET_PRIMARY_ACCOUNT_CARD_FAILURE ="Set Primary Failed";
	public static final String INVALID_MMID ="Invalid MMID";
	public static final String OTHER_BANK_CARD_LINKED ="Card Linked ";
	public static final String OTHER_BANK_CARD_EXISTS ="Card Exists";

	
	/** Authentication failure messages **/
	public static final String MISSING_MANDATORY = "Missing mandatory parameters";
	public static final String NO_SESSION = "NO session found";
	public static final String INVALID_DEVICE = "Invalid device";
	public static final String INVALID_AADHAR = "Invalid aadhar";
	public static final String INVALID_OTP = "Invalid OTP";
	public static final String INVALID_CREDENTIAL = "Invalid Credentials";
	public static final String OTP_NOT_SENT = "OTP not sent please try again";
	
	
}
