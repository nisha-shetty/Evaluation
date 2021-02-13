/*
 * Robosoft Intellectual Property. Copyright © 1996-2017 Robosoft Technologies Pvt. Ltd.
 *
 * All the materials, ideas, concepts, knowledge, source code, software and 
 * techniques used and/or developed by Robosoft internally for its own work, as a part
 * of its internal R&D (referred to as Robosoft Intellectual Property (IP)) shall remain
 * the sole property of Robosoft. Robosoft might choose to include these Robosoft IP
 * into the software being developed for the Customer to speed up the project. 
 *
 * If the Customer receives the original source code pertaining to Robosoft IP as part 
 * of the final deliverable, Customer is free WITHOUT restrictions, to alter, extend, 
 * the Robosoft IP with that particular product/application (including future versions 
 * of this product/application) in any way, subject to the condition that the copyright 
 * notice is retained as it appears in the original IP. If the Customer does not receive 
 * the original source code pertaining to Robosoft IP as part of the final deliverable, 
 * but receives only the relevant library/component in binary form, the Customer is free 
 * WITHOUT restrictions to use the Robosoft IP as is with that particular product/application
 * (including future versions of this product/application), subject to the condition that 
 * the copyright notice is retained as it appears in the original IP. Customer means, an 
 * individual or company, who has a signed contract with Robosoft Technologies Pvt. Ltd. 
 * for carrying out Software development/reengineering work.
 *
 * This Copyright notice may not be removed or modified without prior written consent of 
 * Robosoft Technologies Pvt. Ltd. and the copyright of this Robosoft IP rests SOLELY with
 * Robosoft Technologies Pvt. Ltd.
 */

package com.robosoft.utils.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {

	private static FileManager instance;

	public static FileManager getInstance() {
		if (instance == null)
			instance = new FileManager();
		return instance;
	}

	private FileManager() {

	}

	public File readFile(String inFilePath) {
		File outFile = null;
		try {
			outFile = new File(inFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outFile;
	}

	public String getFileContentAsString(String filePath) {
		String fileContent = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			fileContent = sb.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return fileContent;
	}

	public byte[] getFileAsBytes(String filePath) {
		FileInputStream fileInputStream = null;
		File file = new File(filePath);
		byte[] fileBytes = new byte[(int) file.length()];
		try {
			// convert file into array of bytes
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(fileBytes);
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileBytes;
	}

	public Boolean writeFile(File inFile, Boolean inOverwrite) {
		Boolean outStatus = false;
		Boolean IsOverwritable = false;
		try {
			if (inOverwrite != null) {
				IsOverwritable = inOverwrite;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outStatus;
	}

	public Boolean deleteFile() {
		Boolean outStatus = false;
		return outStatus;

	}

	public Boolean clearFile() {
		Boolean outStatus = false;
		return outStatus;
	}

	public Boolean isFileExists(String inFileName) {
		Boolean outStatus = false;
		return outStatus;
	}

	public Boolean isReadOnly(String inFileName) {
		Boolean outStatus = false;
		return outStatus;
	}

	public Boolean setFilePermissions() {
		Boolean outStatus = false;
		return outStatus;
	}

}
