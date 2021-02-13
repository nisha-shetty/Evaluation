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

import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.CreatedTimestamp;
import io.ebean.annotation.UpdatedTimestamp;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "tblOTP")
public class OTPModel extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OTPSeq")
	@SequenceGenerator(name = "OTPSeq", sequenceName = "tblOTP_SEQ", allocationSize = 1)
	@Column(name = "fldId")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "fldUser")
	private UserModel user;

	@Column(name = "fldOTP", length = 6)
	private String otp;

	@Column(columnDefinition = "integer default 0", name = "fldCount")
	private Integer count = 0;
	
	@Column(columnDefinition = "integer default 0", name = "fldResendCount")
	private Integer resendCount = 0;

	@Temporal(TemporalType.TIMESTAMP)
	@CreatedTimestamp
	@Column(name = "fldCreatedDate")
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@UpdatedTimestamp
	@Column(name = "fldModifiedDate")
	private Date modifiedDate;

	public static Finder<Long, OTPModel> find = new Finder<>(OTPModel.class);

	public OTPModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OTPModel(Integer id) {
		super();
		this.id = id;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "> OTP > - " + id + " --- " + otp;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public Integer getResendCount() {
		return resendCount;
	}

	public void setResendCount(Integer resendCount) {
		this.resendCount = resendCount;
	}

	public static Finder<Long, OTPModel> getFind() {
		return find;
	}

	public static void setFind(Finder<Long, OTPModel> find) {
		OTPModel.find = find;
	}

	
}
