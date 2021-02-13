package com.robosoft.dto.returnValues;


public class SimpleReturnValue extends BaseReturnValue{
	
	private String desc;
		
	public SimpleReturnValue() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SimpleReturnValue(String referenceNo, String requestTime) {
		super(referenceNo, requestTime);
		// TODO Auto-generated constructor stub
	}
	
	public SimpleReturnValue(String desc) {
		this.desc = desc;
	}
	
	public SimpleReturnValue(String desc, String referenceNo, String requestTime) {
		super();
		this.setReferenceNo(referenceNo);
		this.setRequestTime(requestTime);
		this.setDesc(desc);
	}
		
	/*Getters and Setters*/
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
