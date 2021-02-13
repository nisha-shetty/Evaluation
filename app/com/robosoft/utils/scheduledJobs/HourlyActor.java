package com.robosoft.utils.scheduledJobs;

import akka.actor.Props;
import akka.actor.UntypedActor;
import com.robosoft.utils.DateUtils;
import play.Logger;

public class HourlyActor extends UntypedActor {

	public static Props props = Props.create(HourlyActor.class);

	public void onReceive(Object msg) throws Exception {
		Logger.info("Hourly-Actor " + DateUtils.getCurrentDateTime());
		// TODO
	}

}