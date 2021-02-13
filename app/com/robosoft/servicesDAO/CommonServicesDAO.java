package com.robosoft.servicesDAO;

import io.ebean.Ebean;

import java.util.List;

import com.robosoft.models.AppSettingsModel;
import com.robosoft.models.ResponseMessageModel;

import com.robosoft.service.ErrorLogServices;

public class CommonServicesDAO {

	public static CommonServicesDAO instance;

	public static CommonServicesDAO getInstance() {
		if (instance == null)
			instance = new CommonServicesDAO();
		return instance;
	}

	private CommonServicesDAO() {
	}


}
