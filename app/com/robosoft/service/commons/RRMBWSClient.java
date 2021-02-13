package com.robosoft.service.commons;


import play.Logger;
import play.libs.ws.WSClient;

import com.google.inject.Singleton;

@Singleton
public class RRMBWSClient {
	
	
	private RRMBWSClient() {
		Logger.info("Utility Class");
	}
	
	public static WSClient ws;

}
