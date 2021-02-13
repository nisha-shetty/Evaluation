package com.robosoft.commands;

import static com.robosoft.constants.InputFieldConstants.L2_AUTH_VALUES;

import java.util.Map;
import java.util.Set;

import play.Logger;
import play.mvc.Http.Request;
import play.mvc.Http.RequestBody;

import com.fasterxml.jackson.databind.JsonNode;

@SuppressWarnings("deprecation")
public class CommandParams {

	private Request request;
	

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;	
	}
	
	public String getPath() {
		return this.request.path();
	}
	
	public RequestBody getRequestBody() {
		return this.request.body();
	}
	
	public String getQueryString(final String key) {
		return this.request.getQueryString(key);
	}
	
	public Map<String, String[]> getHeaders(){
		return this.request.headers();
	}
	

	public String getHeaderValue(final String headerKey) {
		return this.request.getHeader(headerKey);
	}
	
	public boolean hasHeader(final String sessionToken) {
		return this.request.hasHeader(sessionToken);
	}
	
	public boolean hasQueryParam(final String queryParam) {
		boolean hasParam = false;
		final Set<Map.Entry<String,String[]>> entries = this.request.queryString().entrySet();
        for (Map.Entry<String,String[]> entry : entries) {
            final String key = entry.getKey();
            if(queryParam.equalsIgnoreCase(key)) {
            	hasParam = true;
            	break;
            }
        }
        return hasParam;
	}
	
	public boolean isL2AuthRequest(){
		JsonNode body = request.body().asJson();
		if(body == null){
			Logger.info("Body is empty");
			return false;
		}
		return (body.hasNonNull(L2_AUTH_VALUES)) ? true : false;
	}
	
	 public String getRequestorIP() {
		    String RetVal = "";
		    try {
		        RetVal = request.remoteAddress();
		    }
		    catch (Exception Exc) {
		    }
		    return RetVal;
		 }
	
}
