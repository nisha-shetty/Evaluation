package com.robosoft.utils.scheduledJobs;

import java.util.Calendar;

import static com.robosoft.constants.AppConstants.*;

public class ThreadStratTime {

	private static ThreadStratTime instance;

	public static ThreadStratTime getInstance() {
		if (instance == null) {
			instance = new ThreadStratTime();
		}
		return instance;
	}

	public int startTimeForHourly() {
		Calendar cal = Calendar.getInstance();
		int sec = cal.get(Calendar.SECOND);
		int minute = cal.get(Calendar.MINUTE);
		int startMinute = 0;
		if (sec > 0) {
			try {
				Thread.sleep((60 - sec) * 1000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		startMinute = 59 - minute;
		return startMinute;
	}

	public int startTimeForonceInADay() {
		int startHour = 0;
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		startHour = 23 - hour;// as hour of day is 0 to 23
		return startHour;
	}

	public int startTimeForSessionCleaner() {
		try {
			final Integer SCHEDULER_INTERVAL = Integer.parseInt(appSettings.get(SESSION_CLEANER_INTERVAL_IN_MINS));
			int minute = Calendar.getInstance().get(Calendar.MINUTE);
			return (SCHEDULER_INTERVAL - (minute % SCHEDULER_INTERVAL));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int startTimeForExcelUpload() {
		try {
			final Integer SCHEDULER_INTERVAL = Integer.parseInt(appSettings.get(EXCEL_UPLOAD_INTERVAL_IN_HOURS));
			int hour = Calendar.getInstance().get(Calendar.HOUR);
			return (SCHEDULER_INTERVAL - (hour % SCHEDULER_INTERVAL));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
