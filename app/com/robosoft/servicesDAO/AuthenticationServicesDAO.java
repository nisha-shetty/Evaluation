package com.robosoft.servicesDAO;

import io.ebean.Ebean;
import com.robosoft.models.AuthRequestModel;
import play.Logger;

public class AuthenticationServicesDAO {

	private static AuthenticationServicesDAO instance;

	/**
	 * Function is used to get instance of the class
	 * 
	 * @return
	 */
	public static AuthenticationServicesDAO getInstance() {
		if (instance == null) {
			instance = new AuthenticationServicesDAO();
		}
		return instance;
	}


	public AuthRequestModel getAuthRequest(String reference, String sessionToken) {
		try {
			if(sessionToken != null) {
			return Ebean.find(AuthRequestModel.class).where().like("reference", reference).like("sessionToken", sessionToken).findOne();
			}
			else {
				return Ebean.find(AuthRequestModel.class).where().like("reference", reference).findOne();
			}
		} catch (Exception e) {
			Logger.error("getAuthRequest" + e);
		}
		return null;
	}

	public boolean saveAuthRequest(AuthRequestModel authRequest) {
		try {
			authRequest.save();
			return true;
		} catch (Exception e) {
			Logger.error("saveAuthRequest" + e);
		}
		return false;
	}

}
