package com.robosoft.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.robosoft.dto.RequestFields;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Service {

	public String api;
	public String apiClass;
	public String L1AuthKeys;
	public String L2AuthKeys;
	public RequestFields[] requestFields;
	public String featureName;

}
