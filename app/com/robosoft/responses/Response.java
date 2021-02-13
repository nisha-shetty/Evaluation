package com.robosoft.responses;

import play.libs.Json;

import com.fasterxml.jackson.databind.JsonNode;

public class Response {
	
	public JsonNode asJson(){
		return Json.toJson(this);
	}

}
