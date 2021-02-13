package com.robosoft.crypto;

import java.util.Base64;

import com.robosoft.service.ErrorLogServices;
import com.robosoft.utils.LogUtil;


public class Crypto {
	
	

	public static String encrypt(String text)  {
		return text;
	}
	
	public static String encoding(String encryptedText){
		String strmsg = "";
        try
        {
            byte[] encode = new byte[encryptedText.length()];                
            encode = encryptedText.getBytes("UTF-8");
            strmsg = Base64.getEncoder().encodeToString(encode);
        }
        catch (Exception ex)
        {
			ErrorLogServices.logException(ex, "decrypt");

        }
        
        return strmsg;
	}
	
	
	
	public static Integer decrypts(Integer encryptedText) {
		return encryptedText;
	}

	public static String decode(String password) {
		Base64.Decoder decoder = Base64.getDecoder(); 
		 LogUtil.log(">>>> password ------> "+password);
		 String dStr = new String(decoder.decode(password)); 
		 LogUtil.log(">>>> dstr ------> "+dStr);
		return dStr.trim();
	}
	
    public static void main(String args[])
    {
//		System.out.println(decryptWithIVForALL("GB/PXQDBCaxucR+OsEe7l9Nmzbf2Jvk9DSEejsrZRAQ=", "aar6tzij8o1snaar", "AES/CBC/PKCS7Padding", "0123456789ABCDEF"));
		
    	String decoded = decode("YWRtaW4LCwsLCwsLCwsLCw==");
				System.out.println("decoded >> "+decoded);
        
    }
     

}
