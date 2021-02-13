package com.robosoft.constants;

import static com.robosoft.constants.StringConstants.STR_CONFIG;

import java.io.File;

public class Constants {

	private Constants() {}
	//Play.application().configuration() in play 2.4
	public static final boolean DUMMY_TEST_RESPONSE = Boolean
			.valueOf(ConfConstants.dummy_test_response());
	public static final boolean SERVER_DOWN = Boolean
			.valueOf(ConfConstants.server_down());
	//Play.application().path().getAbsolutePath() in play 2.4
	public static final String CONFIG_FILE_PATH = ConfConstants.applicationServerAbsolutePath() + File.separatorChar + "conf" + File.separatorChar + STR_CONFIG + File.separatorChar
			+ "ServiceHandler.config";

	public static final String UTILITY_CONFIG_FILE_PATH = ConfConstants.applicationServerAbsolutePath() + File.separatorChar + "conf" + File.separatorChar + STR_CONFIG
			+ File.separatorChar + "UtilityServiceHandler.config";

	public static final String SERVICE_HEADERS_CONFIG_FILE_PATH = ConfConstants.applicationServerAbsolutePath()
			+ File.separatorChar + "conf" + File.separatorChar + STR_CONFIG + File.separatorChar
			+ "ServiceHeaders.config";

	public static final String PATTERN_FILE_PATH = ConfConstants.applicationServerAbsolutePath() + File.separatorChar
			+ "conf" + File.separatorChar + "inputPattern.config";

	public static final String LOG_FILE_PATH = ConfConstants.applicationServerAbsolutePath() + File.separatorChar + "web"
			+ File.separatorChar + "logs" + File.separatorChar + "access.log";

	public static final String ABSLT_PATH = ConfConstants.applicationServerAbsolutePath();

	public static final String DATA_FILES_PATH = "files/";
	public static final String FOREX_XML = "forex.xml";
	public static final String IFSC_XLSX = "IFSC codes.xlsx";

	public static final boolean SEND_SMS = Boolean.valueOf(ConfConstants.send_sms());
	public static final String BCC_MAIL_ID = ConfConstants.bcc_mail_id();

	public static final boolean BILLDESK_UAT = Boolean.valueOf(ConfConstants.billdesk_uat());
	
	public static final String FROM_MAIL_ID = ConfConstants.from_mail_id();
	public static final String TO_MAIL_ID = ConfConstants.to_mail_id();
	
	public static final String MOBILE_NUMBER = ConfConstants.mobile_number();
	
	
}
