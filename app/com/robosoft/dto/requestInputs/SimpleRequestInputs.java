package com.robosoft.dto.requestInputs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleRequestInputs {

	//private String guid;
	private String eData;
	private String validator;

//	public String getGuid() {
//		return guid;
//	}
//
//	public void setGuid(String guid) {
//		this.guid = guid;
//	}

	public String geteData() {
		return eData;
	}

	public void seteData(String eData) {
		this.eData = eData;
	}

	public String getValidator() {
		return validator;
	}

	public void setValidator(String validator) {
		this.validator = validator;
	}

}
