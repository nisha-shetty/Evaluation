package com.robosoft.constants;

import static com.robosoft.constants.AppConstants.DOCUMENT_DIR_PATH;
import static com.robosoft.constants.AppConstants.FILE_SERVER_PATH_PREPIX;
import static com.robosoft.constants.AppConstants.FILE_SERVER_PATH_PREPIX_DEV;
import static com.robosoft.constants.AppConstants.PUSH_IMAGE_DIR_PATH;

import com.robosoft.service.ErrorLogServices;

public class UploadFilePaths {

	public static String getImageDirPath(String image_dir_path) {
		String path = "";
		try {
			path = AppConstants.appSettings.get(image_dir_path);
		} catch (Exception e) {
			ErrorLogServices.logException(e, "image path");
		}
		return path;
	}

	public static String getDocumentDirPath() {
		String path = "";
		try {
			path = AppConstants.appSettings.get(DOCUMENT_DIR_PATH);
		} catch (Exception e) {
			ErrorLogServices.logException(e, "directory path");
		}
		return path;
	}
	
	public static String getPushImageDirPath() {
		String path = "";
		try {
			path = AppConstants.appSettings.get(PUSH_IMAGE_DIR_PATH);
		} catch (Exception e) {
			ErrorLogServices.logException(e, "push image directory path");
		}
		return path;
	}

	public static String getFileServerPathPrefixDev() {
		String path = "";
		try {
			path = AppConstants.appSettings.get(FILE_SERVER_PATH_PREPIX_DEV);
		} catch (Exception e) {
			ErrorLogServices.logException(e, "file server");
		}
		return path;
	}

	public static String getFileServerPathPrefixProd() {
		String path = "";
		try {
			path = AppConstants.appSettings.get(FILE_SERVER_PATH_PREPIX);
		} catch (Exception e) {
			ErrorLogServices.logException(e, "server path");
		}
		return path;
	}

}
