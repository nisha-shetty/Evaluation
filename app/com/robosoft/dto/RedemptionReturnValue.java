package com.robosoft.dto;

import com.robosoft.dto.returnValues.BaseReturnValue;

public class RedemptionReturnValue extends BaseReturnValue{
	
	private String scheme;
	private String plan;
	private Integer folio;
	private Integer eligibleAmount;
	private String ifscCode;
	private String eligibleFlag;
	private String remark;
	private String insta_schallowFlag;
	
	public RedemptionReturnValue() {
		super();
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public Integer getEligibleAmount() {
		return eligibleAmount;
	}

	public void setEligibleAmount(Integer eligibleAmount) {
		this.eligibleAmount = eligibleAmount;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getEligibleFlag() {
		return eligibleFlag;
	}

	public void setEligibleFlag(String eligibleFlag) {
		this.eligibleFlag = eligibleFlag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInsta_schallowFlag() {
		return insta_schallowFlag;
	}

	public void setInsta_schallowFlag(String insta_schallowFlag) {
		this.insta_schallowFlag = insta_schallowFlag;
	}

	

}
