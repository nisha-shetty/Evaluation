package com.robosoft.utils;

import static com.robosoft.constants.AppConstants.TYPE_REFERENCE_NO;
import static com.robosoft.constants.InputFieldConstants.CHANNEL;

import com.robosoft.numberFormat.NumberFormatUtil;

public class NumberUtils {

	public static String getReferenceNumber() {
		@SuppressWarnings("deprecation")
		String channel = play.mvc.Controller.request().getHeader(CHANNEL);
		return getReferenceNumber(channel);
	}

	public static String getReferenceNumber(String channel) {
		String referenceNumber = NumberFormatUtil.getInstance().getFormattedNumber(TYPE_REFERENCE_NO, System.currentTimeMillis());
		if (channel != null)
			referenceNumber = channel + referenceNumber;
		return referenceNumber;
	}

}
