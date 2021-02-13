package com.robosoft.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.robosoft.service.ErrorLogServices;

public class DateUtils {

	public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
	public static final String DATE_FORMAT_GRVNCE = "dd-MM-yyyy";
	public static final String DATE_FORMAT = "MM-dd-yyyy";
	public static final String LAST_LOGIN_TIME_DATE_FORMAT = "dd/MM/yyyy hh:mm:ss a z";
	public static final String DATE_FORMAT_01_01_2015 = "d/M/yyyy";
	public static final String DATE_TIME_FULL_FORMAT = "dd/MM/y hh:mm:ss a z";
	public static final String DATE_TIME_FORMAT_01_01_2015__12_12_PM__IST = "d/M/y hh:mm:ss a z";
	public static final String DATE_AND_TIME_FORMAT = "YYYYMMddHHmmss";
	public static final String DATE_FORMAT_IMPSREF_NO = "YYYYMMddHHmm";
	public static final String TIME_FORMAT_IIB_MSG_REQ_TIME = "YYYYMMddHHmmssSSS";
	public static final String TIME_FORMAT_IIB_MSG_RES_YYYYMMDD = "yyyyMMdd";

	public static final String CBSFT_DATE_FORMAT = "yyyy-MM-dd";
	public static final String CBSFT_NARRATION_DATE_FORMAT = "ddMMyy";
	public static final String TIME_FORMAT_IN_HH_MM_A = "hh:mm a";
	
	public static final String TIMESTAMP_FORMAT = "YYYY-MM-dd HH:mm:ss.SSS";//dd-MM-yyyy HH:mm:ss.SSS
	public static final String DATE_TIMESTAMP_FORMAT = "dd-MM-yyyy HH:mm:ss.SSS";
	public static final String TIMESTAMP_FORMAT_DAY = "E MMM dd HH:mm:ss Z yyyy";
	public static final String _TIMESTAMP_FORMAT = "MM/dd/yyyy HH:mm:ss.SSSSSS";
	public static final String SMALL_TIMESTAMP_FORMAT = "dd-MM-yyyy HH:mm:ss";


	public static long getTimeInMili() {
		return System.currentTimeMillis();
	}

	public static long getUnixTimestamp() {
		long unixTimestamp = Instant.now().getEpochSecond();
		return unixTimestamp;
	}

	public static String getUnixTimestampString() {
		return "" + getUnixTimestamp();
	}

	public static String getCurrentDateTime() {
		DateFormat df = new SimpleDateFormat(DATE_TIME_FULL_FORMAT);
		Date dateobj = new Date();
		String currentDateTime = df.format(dateobj);
		return currentDateTime;
	}

	public static String getCurrentDate() {
		DateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		Date dateobj = new Date();
		String currentDateTime = df.format(dateobj);
		return currentDateTime;
	}

	public static Date getDateTimeStamp() {
		DateFormat df = new SimpleDateFormat(DATE_TIME_FULL_FORMAT);
		Date dateobj = new Date();
		String currentDateTime = df.format(dateobj);
		Date dateTimeStamp = null;
		try {
			dateTimeStamp = df.parse(currentDateTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateTimeStamp;
	}

	public static String getTime(String timeFormat) {
		return new SimpleDateFormat(timeFormat).format(new Date());
	}

	public static String formatDate(String timeFormat, Date date) {
		if (timeFormat == null || date == null) {
			return null;
		}
		return new SimpleDateFormat(timeFormat).format(date);
	}

	public static int getDayOfWeek() {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			return c.get(Calendar.DAY_OF_WEEK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int getDayOfWeek(Date date) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			return c.get(Calendar.DAY_OF_WEEK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/***
	 * Method to generate full time in 'YYYYMMddHHmmssSSS' format
	 * <p>
	 * 
	 * @return String : full time
	 */
	public static String getCurrentFullTime() {
		return new SimpleDateFormat(TIME_FORMAT_IIB_MSG_REQ_TIME).format(new Date());
	}

	public static String getCurrentFullTime(long miliSeconds) {
		return new SimpleDateFormat(TIME_FORMAT_IIB_MSG_REQ_TIME).format(new Date(miliSeconds));
	}

	public static Date getBeforeDate(int days, Date date) {
		Calendar cal = Calendar.getInstance();
		if (date != null)
			cal.setTime(date);
		cal.add(Calendar.DATE, -days);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.add(Calendar.SECOND, -1);
		System.out.println(cal.getTime());
		return cal.getTime();
	}

	public static Date getDayStartDateTime(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date == null)
			cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, -1);
		return cal.getTime();
	}

	public static Date getDayEndDateTime(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date == null)
			cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	public static String convertFormmatedDateToYYYYMMDD(String date) {
		if (date != null && date.length() == 10) {
			try {
				date = DateUtils.formatDate(DateUtils.TIME_FORMAT_IIB_MSG_RES_YYYYMMDD, new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return date;
	}

	public static Date getDateBeforeHours(int hours, Date date) {
		Calendar cal = Calendar.getInstance();
		if (date != null)
			cal.setTime(date);
		cal.add(Calendar.HOUR, -hours);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	public static Date getDateObject(String dateformat, String dateString) {
		DateFormat format = new SimpleDateFormat(dateformat, Locale.ENGLISH);
		try {
			return format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static java.sql.Date getSqlDateObject(String dateFormat, String dateString) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		Date parsed = format.parse(dateString);
		java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
		return sqlDate;
	}

	public static long getDateDifference(Date inDateLast, Date inDateFirst) {
		long dateDiff = 0;
		try {
			dateDiff = (inDateLast.getTime() - inDateFirst.getTime());

		} catch (Exception e) {
			ErrorLogServices.logException(e, "getDateDifference");
		}
		return dateDiff;
	}
	public static String getSimpleDateFormatView(Date date) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_GRVNCE);
	        return dateFormat.format(date);
		} catch(Exception e) {
			ErrorLogServices.logException(e, "getDate For UserView");
		}
		return null;	
	}
	
	public static String getSimpleDateView(Date date) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(TIMESTAMP_FORMAT);
	        return dateFormat.format(date);
		} catch(Exception e) {
			ErrorLogServices.logException(e, "getDate For UserView");
		}
		return null;		
	}
	
	public static String getSimpleTimestampView(Date date) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(DATE_TIMESTAMP_FORMAT);
	        return dateFormat.format(date);
		} catch(Exception e) {
			ErrorLogServices.logException(e, "getDate For UserView");
		}
		return null;		
	}
	
	public static String getSmallTimestampView(Date date) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(SMALL_TIMESTAMP_FORMAT);
	        return dateFormat.format(date);
		} catch(Exception e) {
			ErrorLogServices.logException(e, "getDate For UserView");
		}
		return null;		
	}

	public static String getTimeStampDateFormat(String dateString) {
		try {
			DateFormat formatter = new SimpleDateFormat(DATE_FORMAT_GRVNCE); 
			Date date = (Date)formatter.parse(dateString);
			SimpleDateFormat newFormat = new SimpleDateFormat(TIMESTAMP_FORMAT);
			return newFormat.format(date);
		} catch(Exception e) {
			ErrorLogServices.logException(e, "get TimeStamp Date Format");
		}
		return null;
	}
	
	
	public static Date getDateToString(String date) throws ParseException {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date dateobj = df.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateobj);
		cal.add(Calendar.HOUR, 23);
		cal.add(Calendar.MINUTE, 59);
		cal.add(Calendar.SECOND, 59);
		return cal.getTime();
	}
	
	public static Date getDateFromStringDefaultDateFormat(String date){
		try {
			DateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
			Date dateobj;
			dateobj = df.parse(date);
			return dateobj;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			ErrorLogServices.logException(e, "get Date From String Default DateFormat");
		}
		return null;
	}
	
	public static java.sql.Date getDateFromStringSqlDateFormat(String date){
		java.sql.Date sqlDate = new java.sql.Date(getDateFromStringDefaultDateFormat(date).getTime());
		return sqlDate;
	}
	
	/** To get date in EEE ,d (th/st/nd/rd) MMMM  yyyy
	 * @param date
	 * @return
	 */
	public static String getCurrentDateInSpecificFormat(Date date) {
	    @SuppressWarnings("deprecation")
		String dayNumberSuffix = getDayNumberSuffix(date.getDate());
	    StringBuilder dateString=new StringBuilder("EEE, d'").append(dayNumberSuffix).append("' MMMM yyyy");
	    DateFormat dateFormat = new SimpleDateFormat(dateString.toString());
	    return dateFormat.format(date);
	}
	
	private static String getDayNumberSuffix(int day) {
	    if (day >= 11 && day <= 13) {
	        return "th";
	    }
	    switch (day % 10) {
	    case 1:
	        return "st";
	    case 2:
	        return "nd";
	    case 3:
	        return "rd";
	    default:
	        return "th";
	    }
	}
	public static String getCurrentDateInFormat() {
		DateFormat df = new SimpleDateFormat(CBSFT_DATE_FORMAT);
		Date dateobj = new Date();
		String currentDateTime = df.format(dateobj);
		return currentDateTime;
	}
	
	public static String getPreviousStringDate(String dateString)
	{
		DateFormat dateFormat = new SimpleDateFormat(CBSFT_DATE_FORMAT);

        Date myDate = null;
		try {
			myDate = dateFormat.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Use the Calendar class to subtract one day
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(myDate);
        calendar.add(Calendar.DAY_OF_YEAR, -1);

        // Use the date formatter to produce a formatted date string
        Date previousDate = calendar.getTime();
        String result = dateFormat.format(previousDate);

        return result;
	}
	
	public static String getDateFormatView(Date date) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(CBSFT_DATE_FORMAT);
	        return dateFormat.format(date);
		} catch(Exception e) {
			ErrorLogServices.logException(e, "getDate For UserView");
		}
		return null;	
	}
	
	public static String getTimeForUser(String dateString) {
		try {
			DateFormat formatter = new SimpleDateFormat(TIMESTAMP_FORMAT_DAY); 			
			Date date = (Date)formatter.parse(dateString);
			System.out.println("date string ********* "+date);
			SimpleDateFormat newFormat = new SimpleDateFormat(_TIMESTAMP_FORMAT);
			return newFormat.format(date);
		} catch(Exception e) {
			ErrorLogServices.logException(e, "getSimpleDateFormat");
		}
		return null;
	}
	public static Date getDatedd_mmm_yy(String date) throws ParseException {
		DateFormat df = new SimpleDateFormat("dd-MMM-yy");
		Date dateobj = df.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateobj);
		cal.add(Calendar.HOUR, 23);
		cal.add(Calendar.MINUTE, 59);
		cal.add(Calendar.SECOND, 59);
		return cal.getTime();
	}
	
	public static Date getDateyyyyMMdd(String date) throws ParseException {
		DateFormat df = new SimpleDateFormat(CBSFT_DATE_FORMAT);
		Date dateobj = df.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateobj);
		cal.add(Calendar.HOUR, 23);
		cal.add(Calendar.MINUTE, 59);
		cal.add(Calendar.SECOND, 59);
		return cal.getTime();
	}
}
