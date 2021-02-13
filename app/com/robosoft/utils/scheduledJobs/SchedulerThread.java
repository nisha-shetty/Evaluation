package com.robosoft.utils.scheduledJobs;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import play.Logger;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

import static com.robosoft.constants.AppConstants.*;

public class SchedulerThread implements Runnable {

	private final ActorRef hourlyActor;
	private final ActorRef onceInADayActor;
    private final ActorRef sessionCleanerActor;
    //private final ActorRef excelUploadActor;
	private final ActorSystem actorSystem;

	// private final ExecutionContext executionContext;

	public SchedulerThread(ActorSystem system) {
		Logger.info("Initializing Sheduled Tasks...");
		hourlyActor = system.actorOf(HourlyActor.props);
		onceInADayActor = system.actorOf(OnceInADayActor.props);
		sessionCleanerActor = system.actorOf(SessionCleaner.props);
		//excelUploadActor = system.actorOf(ExcelUploadActor.props);
		actorSystem = system;
	}

	@Override
	public void run() {int startTimeForHourly = ThreadStratTime.getInstance().startTimeForHourly();
	int startTimeForonceInADay = ThreadStratTime.getInstance().startTimeForonceInADay();
	int startTimeForSessionCleaner = ThreadStratTime.getInstance().startTimeForSessionCleaner();
	int startTimeForExcelUpload = ThreadStratTime.getInstance().startTimeForExcelUpload();
	if (startTimeForonceInADay > 0) {
		// if there is at least one hour gap
		startTimeForonceInADay = (startTimeForonceInADay * 60) + startTimeForHourly;
	} else {
		// only minutes to consider
 		startTimeForonceInADay = startTimeForHourly;
	}
	actorSystem.scheduler().schedule(
			// Initial delay 0 milliseconds
			Duration.create(startTimeForHourly, TimeUnit.MINUTES),
			// Frequency 30 minutes
			Duration.create(1, TimeUnit.HOURS), hourlyActor, "tick", actorSystem.dispatcher(), null);

	actorSystem.scheduler().schedule(
			// Initial delay 0 milliseconds
			Duration.create(startTimeForonceInADay, TimeUnit.MINUTES),
			// Frequency 30 minutes
			Duration.create(24, TimeUnit.HOURS), onceInADayActor, "tick", actorSystem.dispatcher(), null);

	actorSystem.scheduler().schedule(
			// Initial delay 0 milliseconds
			Duration.create(startTimeForSessionCleaner, TimeUnit.MINUTES),
			// Frequency 15 minutes
			Duration.create(Long.parseLong(appSettings.get(SESSION_CLEANER_INTERVAL_IN_MINS)), TimeUnit.MINUTES),
			sessionCleanerActor, "tick", actorSystem.dispatcher(), null);
	
//	actorSystem.scheduler().schedule(
//			// Initial delay 0 hours
//			Duration.create(startTimeForExcelUpload, TimeUnit.HOURS),
//			// Frequency 8 hours
//			Duration.create(Long.parseLong(appSettings.get(EXCEL_UPLOAD_INTERVAL_IN_HOURS)), TimeUnit.HOURS),
			//excelUploadActor, "tick", actorSystem.dispatcher(), null);

		}
}

