package com.robosoft.utils;

import static com.robosoft.constants.StringConstants.STR_UTF8;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import play.Logger;

import com.robosoft.service.ErrorLogServices;
/**
Aes encryption
*/
public class AES
{
    
    private static SecretKeySpec secretKey ;
    private static byte[] key ;
    
    private static String decryptedString;
    private static String encryptedString;
    
    public static void setKey(String myKey){
        
   
        MessageDigest sha = null;
        try {
            key = myKey.getBytes(STR_UTF8);
            System.out.println(key.length);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); // use only first 128 bit
            System.out.println(key.length);
            Logger.info(new String(key,STR_UTF8));
            secretKey = new SecretKeySpec(key, "AES");
            
            
        } catch (NoSuchAlgorithmException e) {
        	ErrorLogServices.logException(e, "set key");
        } catch (UnsupportedEncodingException e) {
        	ErrorLogServices.logException(e, "set key");
        }             
    
    }
    
    public static String getDecryptedString() {
        return decryptedString;
    }
    public static void setDecryptedString(String decryptedString) {
        AES.decryptedString = decryptedString;
    }
    public static String getEncryptedString() {
        return encryptedString;
    }
    public static void setEncryptedString(String encryptedString) {
        AES.encryptedString = encryptedString;
    }
    public static String encrypt(String strToEncrypt)
    {
        try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        
         
            setEncryptedString(Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes(STR_UTF8))));
        
        }
        catch (Exception e)
        {
           
           Logger.info("Error while encrypting: "+e.toString());
        }
        return null;
    }
    public static String decrypt(String strToDecrypt)
    {
        try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
           
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            setDecryptedString(new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt))));
            
        }
        catch (Exception e)
        {
         
            Logger.info("Error while decrypting: "+e.toString());
        }
        return null;
    }
    public static void main(String args[])
    {
//		System.out.println(decryptWithIVForALL("GB/PXQDBCaxucR+OsEe7l9Nmzbf2Jvk9DSEejsrZRAQ=", "aar6tzij8o1snaar", "AES/CBC/PKCS7Padding", "0123456789ABCDEF"));
		
			String Key = "AXAMFBANKING2010";		
			String val = "PRN~391271183$PID~000000008788$MD~P$ITC~NA$CRN~INR$AMT~50000.00$RESPONSE~AUTO$CG~Y";
//			String str=AES.AESEncrypt(val, Key);
//			LogUtil.log("enc Str>>>>>>>>"+str);		
        
    }
     
   
    
	public static SecretKeySpec generateSecretKeyWithoutSHA(String key) {
		try {
			if (key == null)
				return null;
			byte[] keyBytes = key.getBytes(STR_UTF8);
			keyBytes = Arrays.copyOf(keyBytes, 16);
			return new SecretKeySpec(keyBytes, "AES");
		} catch (UnsupportedEncodingException e) {
			ErrorLogServices.logException(e, "generateSecretKey key");
		}
		return null;
	}
	//"aar6tzij8o1snaar", "AES/CBC/NOPADDING", "0123456789ABCDEF"
	public static String encryptIV(String dataToEncrypt, String inEncryptionKey, String iVector) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException  {
		 try 
		 {
			 SecretKey key = generateSecretKeyWithoutSHA(inEncryptionKey);
			 byte[] iv = new byte[16];
			iv = iVector.getBytes(STR_UTF8);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			 
	         cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv)); 
	         
	         byte[] encrypted = cipher.doFinal(dataToEncrypt.getBytes());
	         String encryptValue = new String(Base64.encodeBase64Chunked(encrypted));
//	         String encryptValue = new String(Base64.getEncoder().encode(encrypted));
			LogUtil.log(" encryptValue value AES **** " + encryptValue);
			return encryptValue;
	         
		 } catch (Exception e) {
			ErrorLogServices.logException(e, "encryptIV");
		}
       return null;

	}
	
	


}
