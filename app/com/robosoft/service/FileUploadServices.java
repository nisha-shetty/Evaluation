package com.robosoft.service;

import static com.robosoft.constants.AppConstants.FILE_SERVER_ROOT_PATH;
import static com.robosoft.constants.AppConstants.FILE_SERVER_ROOT_PATH_DEV;
import static com.robosoft.constants.AppConstants.IMAGE_DIR_PATH;
import static com.robosoft.constants.AppConstants.NAVIGATION_PATH_DOCUMENT;
import static com.robosoft.constants.AppConstants.NAVIGATION_PATH_IMAGE;
import static com.robosoft.constants.AppConstants.UNIX_PATH_SEPARATOR;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import play.Logger;
import play.Mode;

import com.robosoft.constants.AppConstants;
import com.robosoft.constants.ImageUploadConstants;
import com.robosoft.constants.UploadFilePaths;
import com.robosoft.utils.DateUtils;

public class FileUploadServices extends Service {

	private static FileUploadServices instance;

	public static FileUploadServices getInstance() {
		if (instance == null) {
			instance = new FileUploadServices();
		}
		return instance;
	}

	public String getUpdatedFileName(String filename) {
		if (filename != null)
			return new StringBuilder().append(DateUtils.getUnixTimestampString()).append("_").append(filename)
					.toString();
		return filename;
	}

	public String getRootExcelPath() {
		if (ImageUploadConstants.APP_ENV_MODE.equals(Mode.PROD))
			return new StringBuilder().append(AppConstants.appSettings.get(FILE_SERVER_ROOT_PATH))
					.append(File.separatorChar).toString();
		else
			return new StringBuilder().append(AppConstants.appSettings.get(FILE_SERVER_ROOT_PATH_DEV))
					.append(File.separatorChar).toString();
	}

	public boolean isValidImageFile(String fileName, String contentType, File file) {
		String ext = checkValidation(file);
		System.out.println("ext=== " +ext+" coontent type == "+contentType);
		if ((ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("jpeg")
				|| ext.equalsIgnoreCase("gif"))
				&& (contentType.equals("image/png") || contentType.equals("image/jpeg")
						|| contentType.equals("image/gif"))) {
			return true;
		}
		return false;
	}

	public boolean isValidFile(String contentType, File file) {
		String ext = checkValidation(file);
		Logger.debug("contentType :" + contentType);

		if ((ext.equalsIgnoreCase("pdf")) && (contentType.equals("application/pdf"))) {
			return true;
		} else if ((ext.equalsIgnoreCase("mp4")) && (contentType.equals("video/mp4"))) {
			return true;
		} else if (ext.equals("xlsx")
				&& (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))) {
			return true;
		}
			else if(ext.equals("xlsx") 
				&& (contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))) {
			return true;
			//used ext as xlsx since both xlsx and docx has same signature
		}
			else if(ext.equals("xlsx") 
				&& (contentType.equals("application/octet-stream"))) {									
				return true;
				//used ext as xlsx since both xlsx and docx has same signature
			}
//			else if ((ext.equalsIgnoreCase("xlsx") || ext.equalsIgnoreCase("xls"))
//				&& (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") || 
//					contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")
//					|| contentType.equals("application/octet-stream")
//					|| contentType.equals("application/vnd.ms-excel"))) {
//			return true;
//		}

		return false;
	}

	public String checkValidation(File file) {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			byte[] bytes = new byte[4];
			String signature = "";
			inputStream.read(bytes);
			for (byte b : bytes) {
				String str = Integer.toHexString(b & 0xff);
				signature += str;

			}
			String ext = null;
			switch (signature) {
			case "89504e47":
				ext = "png";
				break;
			case "ffd8ffe0":
			case "ffd8ffe1":
			case "ffd8ffe2":
				ext = "jpeg";
				break;
			case "25504446":
				ext = "pdf";
				break;
			case "00020":
				ext = "mp4";
				break;

			case "504b34":
				ext = "xlsx";
				break;
			case "3c3f786d":
				ext = "svg";
				break;
			case "3c746162":
				ext = "xls";
				break;
			default:
				ext = null;
				break;

			}
			Logger.debug("file :" + signature);
			Logger.debug("ext :" + ext);

			return ext;
		} catch (Exception e) {

		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block

			}
		}
		return null;

	}

	private String getRootPath() {
		// return "F://doc_root";
		if (ImageUploadConstants.APP_ENV_MODE.equals(Mode.PROD))
			return AppConstants.appSettings.get(FILE_SERVER_ROOT_PATH);
		else
			return AppConstants.appSettings.get(FILE_SERVER_ROOT_PATH_DEV);
	}

	private String getFullFilePath(String filePath) {
		return new StringBuilder().append(getRootPath()).append(File.separatorChar).append(filePath).toString();
	}

	private String getImageFilePath(String basePath, String fileName, Integer imageType) {
		String dirPath = null;
		switch (imageType) {
		case 1:
			dirPath = UploadFilePaths.getImageDirPath(IMAGE_DIR_PATH);
			break;
		}
		createDirectory(new StringBuilder(basePath).append(File.separatorChar).append(dirPath).toString());
		return getfilePath(dirPath, fileName);
	}

	private boolean deleteFileFromPath(String filePath) {
		boolean status = false;
		try {
			Path fileToDelete = Paths.get(filePath);
			status = Files.deleteIfExists(fileToDelete);
		} catch (Exception e) {

		}
		return status;
	}

	private boolean saveFileTodisk(File file, String filePath) {
		boolean status = false;
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(file);
			File newfile = new File(filePath);
			newfile.createNewFile();
			out = new FileOutputStream(newfile);

			int c;
			while ((c = in.read()) != -1) {
				out.write(c);
			}
			status = true;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeFile(in, out);
		}
		return status;
	}

	private void closeFile(FileInputStream in, FileOutputStream out) {
		try {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		} catch (IOException e) {
		}
	}

	private String getfilePath(String path, String fileName) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(path).append(File.separatorChar).append(fileName);
		return strBuilder.toString();
	}

	private void createDirectory(String dirname) {
		File dir = new File(dirname);
		if (!dir.exists())
			dir.mkdirs();
	}

	public boolean isValidDocumentFile(String fileName, String contentType) {
		String ext = fileName.split("\\.")[1];
		if (ext.equalsIgnoreCase("doc") || ext.equalsIgnoreCase("docx") || ext.equalsIgnoreCase("pdf")
				|| ext.equalsIgnoreCase("txt")) {
			return true;
		}
		return false;
	}

	public void deleteFile(String filePath) {
		try {
			deleteFileFromPath((filePath));
		} catch (Exception e) {
		}
	}

	private String getDocumentFilePath(String basePath, String fileName) {

		StringBuilder dirPath = new StringBuilder(UploadFilePaths.getPushImageDirPath());
		createDirectory(new StringBuilder(basePath).toString());
		return getfilePath(dirPath.toString(), fileName);
	}

	private String getFileServerPathPrefix() {
		if (ImageUploadConstants.APP_ENV_MODE.equals(Mode.PROD))
			return UploadFilePaths.getFileServerPathPrefixProd();
		else
			return UploadFilePaths.getFileServerPathPrefixDev();
	}

	public String getFullPathForImageURL(String uri) {
		StringBuilder path = new StringBuilder(getPathPrefixForImages());
		path.append(File.separatorChar).append(uri);
		return path.toString();
	}

	public String getFullPathForDocumentURL(String uri) {
		StringBuilder path = new StringBuilder(getPathPrefixForDocuments());
		path.append(File.separatorChar).append(uri);
		return path.toString();
	}

	private String getPathPrefixForImages() {
		StringBuilder path = new StringBuilder(getFileServerPathPrefix());
		path.append(UNIX_PATH_SEPARATOR).append(NAVIGATION_PATH_IMAGE);
		return path.toString();
	}

	private String getPathPrefixForDocuments() {
		StringBuilder path = new StringBuilder(getFileServerPathPrefix());
		path.append(UNIX_PATH_SEPARATOR).append(NAVIGATION_PATH_DOCUMENT);
		return path.toString();
	}

	/**
	 * @param file
	 * @param fileName
	 * @param title
	 * @param description
	 * @param link
	 * @param id
	 * @return
	 */
	public boolean uploadImage(File file, String fileName, String title, String description, String link, String id) {
		boolean status = false;
		try {
			String filePath = null;
			String ext = com.google.common.io.Files.getFileExtension(fileName);
			String fileNameWithoutExtension = com.google.common.io.Files.getNameWithoutExtension(fileName);
			StringBuilder newFileName = new StringBuilder(fileNameWithoutExtension);
			newFileName.append("_").append(DateUtils.formatDate(DateUtils.TIME_FORMAT_IIB_MSG_REQ_TIME, new Date()))
					.append(".").append(ext);
			filePath = getDocumentFilePath(getRootPath(), newFileName.toString());
			Logger.debug("filePath :" + getFullFilePath(filePath));
			if (saveFileTodisk(file, getFullFilePath(filePath))) {
				Logger.debug("Save File Successful");
				Logger.debug("filePath :" + getFullFilePath(filePath));
//				status = PushNotificationServices.getInstance().savePushNotification(newFileName.toString(), title,
//						description, link, id);
			}
			return status;
		} catch (Exception e) {
			ErrorLogServices.logException(e, "upload Image");
		}
		return status;
	}
}
