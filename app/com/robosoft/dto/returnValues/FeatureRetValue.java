package com.robosoft.dto.returnValues;

import java.util.List;

public class FeatureRetValue extends BaseReturnValue {
	
	private List<FeatureVal> feature;
	private String guid;

	public List<FeatureVal> getFeature() {
		return feature;
	}

	public void setFeature(List<FeatureVal> feature) {
		this.feature = feature;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
	
}
