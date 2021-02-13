package com.robosoft.dto.cbs.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"City",
"State"
})
public class CityDetail {
	@JsonProperty("State")
	private String state;
	@JsonProperty("City")
	private String city;
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

	/**
	 * @return the city
	 */
	@JsonProperty("City")
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	@JsonProperty("City")
	public void setCity(String city) {
		this.city = city;
	}





}
