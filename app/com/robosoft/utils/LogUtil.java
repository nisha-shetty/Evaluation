package com.robosoft.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import play.Logger;
import play.Logger.ALogger;

import com.robosoft.service.ErrorLogServices;


public class LogUtil {
	private final static ALogger accessLogger = Logger.of("access");
	private final static ALogger xmlAccessLogger = Logger.of("xmlAccessLogger");
	
	public static void log(String logMessage) {
		try {
			accessLogger.info(logMessage);
		} catch(Exception e) {
			ErrorLogServices.logException(e, "log");
		}
	}

	public static String getStackTrace(Exception inEx) {
		StringWriter Sw = new StringWriter();
		inEx.printStackTrace(new PrintWriter(Sw));
		return Sw.toString();
	}
	
	public static void logXMLrequest(String referenceNo, String url, String logMessage, Long timeTaken) {
		xmlAccessLogger.info(
				new StringBuilder().append(referenceNo).append(" ").append(timeTaken != null ? timeTaken : "-").append(" ").append(url).append(": ").append(logMessage).toString());
	}
	
	public static void errorLog(String logFrom, String logMessage) {
			accessLogger.info(new StringBuilder().append(logFrom).append(": ").append(logMessage).toString());
	}


}
