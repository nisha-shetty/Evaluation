package com.robosoft.utils.scheduledJobs;

import akka.actor.Props;
import akka.actor.UntypedActor;

public class SessionCleaner extends UntypedActor {

	public static Props props = Props.create(SessionCleaner.class);

	public void onReceive(Object msg) throws Exception {
		//SessionManager.removeInvaildSessions();
	}

}