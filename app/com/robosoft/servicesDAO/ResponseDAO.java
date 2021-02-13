package com.robosoft.servicesDAO;

import io.ebean.Ebean;
import com.robosoft.models.ResponseMessageModel;

public class ResponseDAO {

	public static ResponseDAO userServicesDAOInstance;
	public static ResponseDAO getInstance() {
		if (userServicesDAOInstance == null) {
			userServicesDAOInstance = new ResponseDAO();
		}
		return userServicesDAOInstance;
	}

	private ResponseDAO() {
	}

	public String getMessage(int status) {
		return Ebean.find(ResponseMessageModel.class).where().eq("code", status).findOne().getMessage();
	}
	
	
	
}
