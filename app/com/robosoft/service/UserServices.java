package com.robosoft.service;

import com.robosoft.dto.ServiceResult;
import com.robosoft.dto.requestInputs.user.UserReqInput;
import com.robosoft.models.UserModel;

import static com.robosoft.constants.ErrorResponseCodes.SERVICE_SUCCESS;
import com.robosoft.servicesDAO.UserServicesDAO;

public class UserServices extends Service {


	private static UserServices userServicesInstance;
	public static UserServices getInstance() {
		if (userServicesInstance == null) {
			userServicesInstance = new UserServices();
		}
		return userServicesInstance;
	}

	private UserServices() {
	}


	public ServiceResult addUserDetails(UserReqInput mRequestInput,File file) {


		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String picUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("api/images/getImage/")
				.path(fileName)
				.toUriString();
		UserModel user =  setUserDetails(mRequestInput,picUrl);
		UserServicesDAO.getInstance().saveUserDetails(user);
		return createServiceResult(SERVICE_SUCCESS);
	}

	private UserModel setUserDetails(UserReqInput mRequestInput,String picUrl) {
		UserModel user = new UserModel();
		user.setUserName(mRequestInput.getNameofPhotographer());
		user.setProfilePicUrl(picUrl);
		return  user;
	}
}
