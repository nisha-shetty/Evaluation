package com.robosoft.service;

import com.robosoft.dto.UserDto;

public class CommonServices {
	
	private static CommonServices instance;
	
	public static CommonServices getInstance() {
		if (instance == null) {
			instance = new CommonServices();
		}
		return instance;
	}


	public static UserDto getResult() {
		UserDto userDTO = new UserDto();
		userDTO.setUserId("");
		userDTO.setPassword("");
		return userDTO;
	}

	public static UserDto getResult(UserDto sampleDTO) {
		if (sampleDTO == null)
			sampleDTO = getResult();
		return sampleDTO;
	}
	
	public UserDto getSampleResult() {
		return null;
	}
}
