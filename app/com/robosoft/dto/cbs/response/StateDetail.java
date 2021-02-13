package com.robosoft.dto.cbs.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"State"
})
public class StateDetail {
@JsonProperty("State")
private String state;

/**
 * @return the state
 */
@JsonProperty("State")
public String getState() {
	return state;
}

/**
 * @param state the state to set
 */
@JsonProperty("State")
public void setState(String state) {
	this.state = state;
}




}
