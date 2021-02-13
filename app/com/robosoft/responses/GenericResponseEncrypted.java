package com.robosoft.responses;

import com.fasterxml.jackson.databind.JsonNode;

public class GenericResponseEncrypted extends Response {

	private JsonNode eData;

	public GenericResponseEncrypted(JsonNode eData) {
		super();
		this.eData = eData;
	}

	public JsonNode geteData() {
		return eData;
	}

	public void seteData(JsonNode eData) {
		this.eData = eData;
	}
}
