package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.GZIPInputStream;

import play.Logger;
import play.Logger.ALogger;
import play.mvc.Controller;
import play.mvc.Result;

import com.robosoft.constants.Constants;
import com.robosoft.utils.LogUtil;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

	private final static ALogger accessDebugger = Logger.of("access_debug");
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */

    
    public Result log(String id) {
		String archPath = "arch-access/access-log-xxx.gz";
		//return ok(getLogs(id, Constants.LOG_FILE_PATH, archPath).toString());
		return ok(getLogs(Constants.LOG_FILE_PATH, archPath).toString());

	}
    
	private StringBuilder getLogs(String filePath, String archPath) {
		BufferedReader br = null;
		FileReader fileReader = null;
		InputStream fileStream = null;
		InputStream gzipStream = null;
		Reader decoder = null;
		StringBuilder strBuilder = new StringBuilder();

		try {
			String sCurrentLine;
			response().setContentType("text/plain; charset=iso-8859-1");
			String date = request().uri().split("/")[4];
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = dateFormat.format(new Date());
			// Todays log
			if (date.equals(strDate)) {
				accessDebugger.debug("File Path:" + filePath);
				fileReader = new FileReader(filePath);
				br = new BufferedReader(fileReader);
			} else {// Archeived log
				archPath = archPath.replace("xxx", date);
				accessDebugger.debug("Arch File Path:" + (Constants.ABSLT_PATH + File.separatorChar + archPath));
				fileStream = new FileInputStream(Constants.ABSLT_PATH + File.separatorChar + archPath);
				gzipStream = new GZIPInputStream(fileStream);
				decoder = new InputStreamReader(gzipStream);
				br = new BufferedReader(decoder);
			}
			while ((sCurrentLine = br.readLine()) != null) {
				strBuilder.append("\n").append(sCurrentLine);
			}
			return strBuilder;
		} catch (FileNotFoundException e) {
			Logger.error("FileNotFoundException" + e);
			accessDebugger.debug("File Not Found Exception");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Logger.error("IOException" + e);
			accessDebugger.debug(LogUtil.getStackTrace(e));
		} finally {
			try {
				if (fileReader != null)
					fileReader.close();
			} catch (IOException e) {
				Logger.error("IOException" + e);
				accessDebugger.debug(LogUtil.getStackTrace(e));
			}
			if (fileStream != null) {
				try {
					fileStream.close();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					Logger.error("FileStream --> error---" + e2);
				}
			}
			if (gzipStream != null) {
				try {
					gzipStream.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					Logger.error("gzipStream --> error---" + e1);
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Logger.error(" --> error---" + e);
				}
			}
			if (decoder != null) {
				try {
					decoder.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Logger.error("Decoder --> error---" + e);
				}
			}

		}
		return strBuilder.append("no records found");
	}

}
