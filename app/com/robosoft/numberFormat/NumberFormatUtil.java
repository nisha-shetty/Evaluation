package com.robosoft.numberFormat;

import static com.robosoft.constants.AppConstants.DEFAULT_REF_NUMBER_PART_WIDTH;
import static com.robosoft.constants.AppConstants.TYPE_REFERENCE_NO;
import static com.robosoft.utils.DateUtils.DATE_AND_TIME_FORMAT;
import io.ebean.Ebean;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.robosoft.models.NumberFormatModel;

import com.robosoft.utils.DateUtils;

public class NumberFormatUtil {

	private static NumberFormatUtil instance = null;
	private static final String NUMBER_TYPE = "NumberType";
	private static final String DATE_TAG = "<Date>";
	private static final String MONTH_TAG = "<Month>";
	private static final String YEAR_TAG = "<Year>";
	private static final String HOUR_TAG = "<Hour>";
	private static HashMap<String, NumberFormatModel> numberFormatMap = null;
	private int DATE_WISE = 0;

	private NumberFormatUtil() {
		numberFormatMap = new HashMap<String, NumberFormatModel>();
	}

	public static NumberFormatUtil getInstance() {
		if (instance == null)
			instance = new NumberFormatUtil();
		return instance;
	}

	private NumberFormatModel getNumberFormatFromDB(Integer type) {
		return Ebean.find(NumberFormatModel.class).where().eq("type", type).eq("isActive", true).findOne();
	}

	private synchronized NumberFormatModel getNumberFormat(Integer type) {
		NumberFormatModel numberFormatModel = numberFormatMap.get(NUMBER_TYPE + type);
		if (numberFormatModel == null) {
			numberFormatModel = getNumberFormatFromDB(type);
			if (numberFormatModel != null) {
				numberFormatMap.put(NUMBER_TYPE + type, numberFormatModel);
				if (numberFormatModel.getType() == DATE_WISE && numberFormatModel.getEffectiveFrom().before(new Date())) {
					numberFormatModel = getNewNumberFormatModule(numberFormatModel);
					numberFormatMap.put(NUMBER_TYPE + type, numberFormatModel);
				}
			} else {
				numberFormatModel = new NumberFormatModel();
				numberFormatModel.setType(type);
				numberFormatModel.setActive(true);
				numberFormatModel.setRestart(true);
				numberFormatModel.setPreFillZero(true);
				numberFormatModel.setNumberPartWidth(getDefaultNumberPartWidth(type));
				numberFormatModel.setStartNumber(1);
				numberFormatModel.setPrefix("");
				numberFormatModel.setSuffix("");
				numberFormatModel.setEffectiveFrom(getEffectiveDate(type));
				numberFormatMap.put(NUMBER_TYPE + type, numberFormatModel);
			}
		}
		int lastGeneratedNumber;
		if (numberFormatModel.getLastGenerated() != null) {
			lastGeneratedNumber = numberFormatModel.getLastGenerated();
		} else {
			lastGeneratedNumber = numberFormatModel.getStartNumber() - 1;
		}
		numberFormatModel.setLastGenerated(++lastGeneratedNumber);
		return numberFormatModel;
	}

	private int getDefaultNumberPartWidth(Integer type) {
		int numberPartWidth = 0;
		switch (type) {
		case TYPE_REFERENCE_NO:
			numberPartWidth = DEFAULT_REF_NUMBER_PART_WIDTH;
			break;
			default:break;
		}
		return numberPartWidth;
	}

	private Date getEffectiveDate(int type) {
		if (DATE_WISE == type) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			cal.add(Calendar.DATE, 1);
			return cal.getTime();
		} else {
			return new Date();
		}

	}

	private NumberFormatModel getNewNumberFormatModule(NumberFormatModel numberFormatModel) {
		NumberFormatModel newNumberFormatModel = new NumberFormatModel();
		newNumberFormatModel.setType(numberFormatModel.getType());
		newNumberFormatModel.setActive(numberFormatModel.isActive());
		newNumberFormatModel.setRestart(numberFormatModel.isRestart());
		newNumberFormatModel.setPreFillZero(numberFormatModel.isPreFillZero());
		newNumberFormatModel.setNumberPartWidth(numberFormatModel.getNumberPartWidth());
		newNumberFormatModel.setStartNumber(numberFormatModel.getStartNumber());
		newNumberFormatModel.setPrefix(numberFormatModel.getPrefix());
		newNumberFormatModel.setSuffix(numberFormatModel.getSuffix());
		newNumberFormatModel.setEffectiveFrom(getEffectiveDate(numberFormatModel.getType()));
		numberFormatModel.setActive(false);
		saveLastGeneratedNumber(numberFormatModel);

		return newNumberFormatModel;
	}

	public int getLastGeneratedNumber(Integer type) {
		NumberFormatModel numberFormatModel = getNumberFormat(type);
		if (numberFormatModel != null)
			return numberFormatModel.getLastGenerated();
		else
			return 0;
	}

	public String getFormattedNumber(Integer type, long miliSeconds) {
		String formattedString = null;
		String numberString = null;
		String prefixString = null;
		String suffixString = null;

		NumberFormatModel numberFormatModel = getNumberFormat(type);
		if (numberFormatModel != null) {

			if (numberFormatModel.isPreFillZero()) {
				numberString = String.format(new StringBuilder().append("%0").append(numberFormatModel.getNumberPartWidth()).append("d").toString(),
						numberFormatModel.getLastGenerated());
			} else {
				numberString = String.valueOf(numberFormatModel.getLastGenerated());
			}
			prefixString = numberFormatModel.getPrefix() != null ? numberFormatModel.getPrefix() : "";
			suffixString = numberFormatModel.getSuffix() != null ? numberFormatModel.getSuffix() : "";
			formattedString = new StringBuilder().append(prefixString).append(numberString).append(suffixString).toString();
			formattedString = replaceDateTags(formattedString, miliSeconds);
			saveLastGeneratedNumber(numberFormatModel);
		}
		return formattedString;

	}

	private String replaceDateTags(String formattedString, long miliSeconds) {
		String dateString = null;
		if (miliSeconds > 0) {
			dateString = getFormattedDateString(miliSeconds);
			formattedString = formattedString.replaceAll(DATE_TAG, getDayString(dateString, 6, 8));
			formattedString = formattedString.replaceAll(MONTH_TAG, getMonthString(dateString, 4, 6));
			formattedString = formattedString.replaceAll(YEAR_TAG, getYearString(dateString, 2, 4));
			formattedString = formattedString.replaceAll(HOUR_TAG, getHourString(dateString, 8, 10));
		}
		return formattedString;
	}

	private void saveLastGeneratedNumber(NumberFormatModel numberFormatModel) {
		Ebean.save(numberFormatModel);
	}

	public String getFormattedDateString(long miliSeconds) {
		if (miliSeconds == 0)
			return null;

		Date date = new Date(miliSeconds);
		return DateUtils.formatDate(DATE_AND_TIME_FORMAT, date);
	}

	public String getYearString(String dateString, int from, int to) {
		return dateString.substring(from, to);
	}

	public String getMonthString(String dateString, int from, int to) {
		return ReferenceNumberConstants.MONTHS[Integer.parseInt(dateString.substring(from, to)) - 1];
	}

	public String getDayString(String dateString, int from, int to) {
		return ReferenceNumberConstants.DAYS[Integer.parseInt(dateString.substring(from, to)) - 1];
	}

	public String getHourString(String dateString, int from, int to) {
		return ReferenceNumberConstants.HOURS[Integer.parseInt(dateString.substring(from, to))];
	}

}
