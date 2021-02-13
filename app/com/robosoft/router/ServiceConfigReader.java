package com.robosoft.router;

import static com.robosoft.constants.StringConstants.STR_SERVER_ISSUE;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.robosoft.models.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robosoft.constants.Constants;
import com.robosoft.dto.InputPattern;
import com.validation.ValidationPattern;

public class ServiceConfigReader {

	private HashMap<String, Service> mSerivesMap;
	private HashMap<String, ValidationPattern> mInputMap;
	private static ServiceConfigReader mInstance;

	private ServiceConfigReader() {
		mSerivesMap = new HashMap<>();
		readConfig();
		mInputMap = new HashMap<>();
		readPattern();
	}

	public static ServiceConfigReader getInstance() {
		if (mInstance == null) {
			mInstance = new ServiceConfigReader();
		}
		return mInstance;
	}

	private void readConfig() {
		String fileName = Constants.CONFIG_FILE_PATH;
		try {
			ObjectMapper mapper = new ObjectMapper();
			List<Service> list = mapper.readValue(new File(fileName), new TypeReference<ArrayList<Service>>() {
			});
			int size = list.size();

			for (int i = 0; i < size; i++) {
				mSerivesMap.put(list.get(i).api, list.get(i));
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
			throw new RuntimeException(STR_SERVER_ISSUE);

		} catch (JsonMappingException e) {
			e.printStackTrace();
			throw new RuntimeException(STR_SERVER_ISSUE);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(STR_SERVER_ISSUE);
		} finally {

		}
	}

	public Service getService(String serviceUrl) {
		System.out.println("......serviceUrl....."+serviceUrl);
		return mSerivesMap.get(serviceUrl);
	}

	private void readPattern() {
		String fileName = Constants.PATTERN_FILE_PATH;
		try {
			ObjectMapper mapper = new ObjectMapper();System.out.println("....test 1....");
			System.out.println(".......mapper ... "+mapper);
			List<InputPattern> list = mapper.readValue(new File(fileName), new TypeReference<ArrayList<InputPattern>>() {				
			});System.out.println(".....test2... "+list);
			int size = list.size();

			for (int i = 0; i < size; i++) {
				InputPattern inputPattern = list.get(i);
				mInputMap.put(list.get(i).getKey(), new ValidationPattern(inputPattern.getReqKey(), inputPattern.getPattern()));
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
			throw new RuntimeException(STR_SERVER_ISSUE);

		} catch (JsonMappingException e) {
			e.printStackTrace();
			throw new RuntimeException(STR_SERVER_ISSUE);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(STR_SERVER_ISSUE);
		} finally {

		}
	}

	public static String getPattern(String key) {
		return ServiceConfigReader.getInstance().mInputMap.get(key).getPattern();
	}

	public static ValidationPattern getValidationPattern(String key) {
		return ServiceConfigReader.getInstance().mInputMap.get(key);
	}

	public static ValidationPattern getValidationPattern(String key, boolean isOptional) {
		ValidationPattern validationPattern = ServiceConfigReader.getInstance().mInputMap.get(key);
		validationPattern.setOptional(isOptional);
		return validationPattern;
	}

	

}
