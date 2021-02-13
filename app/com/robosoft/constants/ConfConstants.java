package com.robosoft.constants;

import play.Logger;
import play.api.Application;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.typesafe.config.Config;

@Singleton
public class ConfConstants {

	private final Config config;
	private static String applicationServerAbsolutePath;
	private static String dummy_test_response;
	private static String server_down;
	private static String send_sms;
	private static String billdesk_uat;
	private static String from_mail_id;
	private static String to_mail_id;
	private static String mobile_number;
	private static String bcc_mail_id;

	@Inject
	private ConfConstants(Config config, Application app) {
		this.config = config;
		applicationServerAbsolutePath = app.asJava().getWrappedApplication().path().getAbsolutePath();
		Logger.info("********-Constants-********");
		initialize();
	}

	private void initialize() {
		try {
			dummy_test_response = config.getString("dummy_test_response");
			server_down = config.getString("server_down");
			send_sms = config.getString("send_sms");
			billdesk_uat = config.getString("billdesk_uat");
			from_mail_id = config.getString("from_mail_id");
			to_mail_id = config.getString("to_mail_id");
			mobile_number = config.getString("mobile_number");
			bcc_mail_id = config.getString("bcc_mail_id");
			
//			send_sms = config.getString("send_sms");
			// dummy_test_response
			// server_down
			// send_sms

			// ServiceHandler.config
			// UtilityServiceHandler.config
			// ServiceHeaders.config
			// inputPattern.config
			// access.log
			Logger.info(applicationServerAbsolutePath);
			Logger.info(dummy_test_response);
		} catch (Exception e) {
			Logger.info("Exception: " + e.getMessage());
		}

	}

	public static String applicationServerAbsolutePath() {
		return applicationServerAbsolutePath;
	}

	public static String dummy_test_response() {
		return dummy_test_response;
	}

	public static String server_down() {
		return server_down;
	}

	public static String send_sms() {
		return send_sms;
	}
	
	

	public static String billdesk_uat() {
		return billdesk_uat;
	}


	public static String from_mail_id() {
		return from_mail_id;
	}

	public static String to_mail_id() {
		return to_mail_id;
	}
	
	public static String mobile_number() {
		return mobile_number;
	}

	
	public static String bcc_mail_id() {
		return bcc_mail_id;
	}

}
