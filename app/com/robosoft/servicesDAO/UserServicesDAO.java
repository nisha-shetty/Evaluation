package com.robosoft.servicesDAO;

import com.robosoft.models.UserModel;
import com.robosoft.service.ErrorLogServices;
import io.ebean.Ebean;
import play.Logger;

public class UserServicesDAO{

	public static UserServicesDAO userServicesDAOInstance;
	public static UserServicesDAO getInstance() {
		if (userServicesDAOInstance == null) {
			userServicesDAOInstance = new UserServicesDAO();
		}
		return userServicesDAOInstance;
	}

	private UserServicesDAO() {
	}

	public Boolean saveUserDetails(UserModel user) {
		try {

			Ebean.save(user);
			return true;
		} catch (Exception e) {
			Logger.error("saveUserDetails" + e);
			ErrorLogServices.logException(e, "saveUserDetails");
		}
		return false;
	}


}
