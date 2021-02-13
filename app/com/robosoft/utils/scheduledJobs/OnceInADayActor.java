package com.robosoft.utils.scheduledJobs;

import akka.actor.Props;
import akka.actor.UntypedActor;
import com.robosoft.utils.DateUtils;
import play.Logger;

public class OnceInADayActor extends UntypedActor {

	public static Props props = Props.create(OnceInADayActor.class);

	public void onReceive(Object msg) throws Exception {
		Logger.debug("Once-In-A-Day-Actor " + DateUtils.getCurrentDateTime());

	}

}