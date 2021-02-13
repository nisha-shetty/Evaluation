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

package com.robosoft.models;

import com.robosoft.utils.RandomUtils;
import io.ebean.Model;

import javax.persistence.*;
import java.util.Date;

//import utils.RandomUtils;

@Entity
@Table(name = "tblauthrequests")
public class AuthRequestModel extends Model {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AuthRequestsSeq")
	@SequenceGenerator(name = "AuthRequestsSeq", sequenceName = "tblAuthRequests_SEQ", allocationSize = 1)
	@Column(name = "fldId")
	private Integer id;

	@Column(name = "fldReference")
	private String reference;

	@Column(name = "fldSession")
	private String sessionToken;

	@Column(name = "fldDevice")
	private String deviceId;

	@Column(name = "fldReqBody")
	private String requestBody;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fldDate")
	private Date date;

	@Column(name = "fldIsConsumed")
	private Boolean isConsumed;

	@Column(name = "fldRetryCount")
	private Integer retryCount;

	public AuthRequestModel() {
		super();
		this.reference = RandomUtils.generateUUID();
		this.date = new Date();
		this.isConsumed = false;
	}

	public AuthRequestModel(String requestBody, String sessionToken, String deviceId) {
		this.requestBody = requestBody;
		this.sessionToken = sessionToken;
		this.deviceId = deviceId;
		this.reference = RandomUtils.generateUUID();
		this.date = new Date();
		this.isConsumed = false;
		this.retryCount = 0;
	}

	/* Getters and Setters */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getIsConsumed() {
		return isConsumed;
	}

	public void setIsConsumed(Boolean isConsumed) {
		this.isConsumed = isConsumed;
	}

	public Integer getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}

}
