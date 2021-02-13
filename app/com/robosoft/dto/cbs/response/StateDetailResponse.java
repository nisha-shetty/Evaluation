package com.robosoft.dto.cbs.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"States",
"City"
})
public class StateDetailResponse {

	@JsonProperty("States")
	private List<StateDetail> states = null;
	
	@JsonProperty("City")
	private List<CityDetail> cities = null;

	/**
	 * @return the states
	 */
	public List<StateDetail> getStates() {
		return states;
	}

	/**
	 * @return the cities
	 */
	public List<CityDetail> getCities() {
		return cities;
	}

	/**
	 * @param states the states to set
	 */
	public void setStates(List<StateDetail> states) {
		this.states = states;
	}

	/**
	 * @param cities the cities to set
	 */
	public void setCities(List<CityDetail> cities) {
		this.cities = cities;
	}


	
}
