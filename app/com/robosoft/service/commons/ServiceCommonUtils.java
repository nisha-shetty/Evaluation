/**
 * Project			: Axillo
 * Module			: Commons
 * File name		: ServiceCommonUtils.java
 * Description		: This Utils Class is used for common activities
 * Author			: Ganesh Kalkura
 * Comments			: NA
 * Copyright     	: Robosoft Intellectual Property. Copyright © 1996-2018 Robosoft Technologies Pvt. Ltd.
 * History     		: Initial Draft
 **/
package com.robosoft.service.commons;

public final class ServiceCommonUtils {

	private ServiceCommonUtils() {

	}

	/**
	 * Function is used to check for not null and non empty string
	 * 
	 * @param inValue
	 * @return
	 */
	public static boolean checkForNotNullAndNonEmpty(String inValue) {
		if (inValue != null && !inValue.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Function is used to check for not null and non zero value
	 * 
	 * @param inValue
	 * @return
	 */
	public static boolean checkForNotNullAndGrtThanZero(Integer inValue) {
		if (inValue != null && inValue > 0) {
			return true;
		} else {
			return false;
		}
	}
}
