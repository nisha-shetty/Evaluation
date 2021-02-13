package com.robosoft.service;

import play.Logger;

import com.robosoft.utils.LogUtil;

public class ErrorLogServices extends Service {

	private static ErrorLogServices instance;

	public static void logException(Exception inException, String inFunction) {
		Logger.info("*************** Error ******************* in function => " + inFunction);
		inException.printStackTrace();
		Logger.info("**********************************");
		LogUtil.log("*************** Error ******************* in function => " + inFunction);
		LogUtil.log(LogUtil.getStackTrace(inException));
		LogUtil.log("**********************************");

	}
}
