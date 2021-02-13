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

import io.ebean.Model;

import javax.persistence.*;


//@CacheStrategy(useBeanCache = true)
@Entity
@Table(name = "tblappsettings")
public class AppSettingsModel extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AppSettingsSeq")
	@SequenceGenerator(name = "AppSettingsSeq", sequenceName = "tblAppSettings_SEQ", allocationSize = 1)
	@Column(name = "fldId")
	private Integer id;

	@Column(name = "fldCode")
	private String code;

	@Column(name = "fldValue")
	private String value;

	public AppSettingsModel() {
	}

	public AppSettingsModel(String code, String value) {
		this.code = code;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
