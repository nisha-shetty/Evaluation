/*
 * Robosoft Intellectual Property. Copyright © 1996-2017 Robosoft Technologies Pvt. Ltd.
 *
 * All the materials, ideas, concepts, knowledge, source code, software and 
 * techniques used and/or developed by Robosoft internally for its own work, as a part
 * of its internal R&D (referred to as Robosoft Intellectual Property (IP)) shall remain
 * the sole property of Robosoft. Robosoft might choose to include these Robosoft IP
 * into the software being developed for the Customer to speed up the project. 
 *
 * If the Customer receives the original source code pertaining to Robosoft IP as part 
 * of the final deliverable, Customer is free WITHOUT restrictions, to alter, extend, 
 * the Robosoft IP with that particular product/application (including future versions 
 * of this product/application) in any way, subject to the condition that the copyright 
 * notice is retained as it appears in the original IP. If the Customer does not receive 
 * the original source code pertaining to Robosoft IP as part of the final deliverable, 
 * but receives only the relevant library/component in binary form, the Customer is free 
 * WITHOUT restrictions to use the Robosoft IP as is with that particular product/application
 * (including future versions of this product/application), subject to the condition that 
 * the copyright notice is retained as it appears in the original IP. Customer means, an 
 * individual or company, who has a signed contract with Robosoft Technologies Pvt. Ltd. 
 * for carrying out Software development/reengineering work.
 *
 * This Copyright notice may not be removed or modified without prior written consent of 
 * Robosoft Technologies Pvt. Ltd. and the copyright of this Robosoft IP rests SOLELY with
 * Robosoft Technologies Pvt. Ltd.
 */

package com.robosoft.dto.returnValues;

import static com.robosoft.constants.Constants.SEND_SMS;

public class AuthResponseReturnValue extends BaseReturnValue {

	private String L2AuthReference;
	private String L2AuthKeys;
	private String otp;
	private String expiryTime;

	public String getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(String expiryTime) {
		this.expiryTime = expiryTime;
	}

	public AuthResponseReturnValue(String referenceNo, String requestTime, String L2AuthReference, String L2AuthKeys) {
		super(referenceNo, requestTime);
		this.L2AuthReference = L2AuthReference;
		this.L2AuthKeys = L2AuthKeys;
	}

	public AuthResponseReturnValue(String referenceNo, String requestTime, String L2AuthReference, String L2AuthKeys, String otp, String expiryTime) {
		super(referenceNo, requestTime);
		this.L2AuthReference = L2AuthReference;
		this.L2AuthKeys = L2AuthKeys;
		this.otp = SEND_SMS == true ? "" : otp;
		this.expiryTime = expiryTime;
	}

	public String getL2AuthReference() {
		return L2AuthReference;
	}

	public void setL2AuthReference(String l2AuthReference) {
		L2AuthReference = l2AuthReference;
	}

	public String getL2AuthKeys() {
		return L2AuthKeys;
	}

	public void setL2AuthKeys(String l2AuthKeys) {
		L2AuthKeys = l2AuthKeys;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

}
