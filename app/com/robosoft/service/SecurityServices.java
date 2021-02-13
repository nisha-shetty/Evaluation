package com.robosoft.service;


import static com.robosoft.constants.ErrorResponseCodes.SERVICE_SUCCESS;
import static com.robosoft.constants.ResponseConstants.STATUS_OK;
import static com.robosoft.constants.ResponseConstants.STATUS_INTERNAL_SERVER_ERROR;
import static com.robosoft.constants.ErrorResponseCodes.SERVER_ERROR_STCODE;
import static com.robosoft.constants.AppConstants.appSettings;
import static com.robosoft.constants.AppConstants.ENABLE_ENC;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.apache.commons.codec.binary.Base64;

import play.Logger;

//import com.robosoft.crypto.RSA;
import com.robosoft.dto.ServiceResult;
import com.robosoft.dto.returnValues.RSAPublicKeyReturnValue;

//import models.SecurityModel;

import com.robosoft.service.SecurityServices;
import com.robosoft.service.Service;
//import com.robosoft.servicesDAO.SecurityDAO;
import com.robosoft.utils.LogUtil;


public class SecurityServices extends Service {

	private static SecurityServices instance;

	private SecurityServices() {
	}

	public static SecurityServices getInstance() {
		if (instance == null) {
			instance = new SecurityServices();
		}
		return instance;
	}

	public ServiceResult fetchRSAPublicKey() {
		try {
			RSAPublicKeyReturnValue rsaPublicKeyReturnValue=new RSAPublicKeyReturnValue();

			String text="GyI2l5D9n+JOQPGF6ulQ9SMv/ak7M/ko7tk6b/YMk9lDsVwkUI0B9q3+0gZyhhq/Rz+LDNajwlKfcGUhZdvDeUTZ2732l1plAehHz2xrqloM9YXIzPN45qSDLoume16agkrMDT9oz5Z1OccyqGZWpVuc29e/xFkNS8Z2CwzpFIetGxasL647RI8qJO0ghtdIAmU6ADwJf+77+nr4vSoVEVe+ijtljx1+bsgHizHjMMkUIs++h4biEXmcY/b/IXCxgEKlmBSBh2UHxjtJPpkL9ruJNQH3dlhRWD+higHzQRjg0ynYLZq4trRGzjgLYobFBlJJrm6Wk/DwyBq07TsNBQ==";
			rsaPublicKeyReturnValue.setEnableEnc(Integer.valueOf(appSettings.get(ENABLE_ENC)) == 1 ? true : false);
			return createServiceResult(SERVICE_SUCCESS,rsaPublicKeyReturnValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return createServiceResult(SERVER_ERROR_STCODE);
	}

}
