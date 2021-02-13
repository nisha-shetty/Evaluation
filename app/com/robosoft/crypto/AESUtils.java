package com.robosoft.crypto;


//import static com.rs.aura.constants.AppConstants.AES_IVECTOR_KEY;
//import static com.rs.aura.constants.AppConstants.appSettings;
//import static com.rs.aura.constants.AppConstants.AES_PADDING_KEY;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

//import com.rs.aura.services.commons.ErrorLogServices;
//import com.rs.aura.utils.LogUtil;
import play.Logger;

import com.robosoft.service.ErrorLogServices;
import com.robosoft.utils.LogUtil;

public class AESUtils {
    private AESUtils() {
    }
/*
    public static void main(String[] args) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = generateSecretKey("kaypay");
        LogUtil.log(secretKey);
        Cipher cipher = Cipher.getInstance("AES");
        String plainText = "kaypay";
        Logger.info("Plain Text Before Encryption: " + plainText);
        String encryptedText = encrypt1(plainText, secretKey);
        Logger.info("Encrypted Text After Encryption: " + encryptedText);
        String decryptedText = decrypt(encryptedText, secretKey);
        Logger.info("Decrypted Text After Decryption: " + decryptedText);
    }
*/
    public static String encrypt1(String plainText, SecretKey secretKey) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			byte[] plainTextByte = plainText.getBytes();
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] encryptedByte = cipher.doFinal(plainTextByte);
			Base64.Encoder encoder = Base64.getEncoder();
			String encryptedText = encoder.encodeToString(encryptedByte);
			return encryptedText;
		} catch (Exception e) {
			ErrorLogServices.logException(e, "aes");
		}
		return null;
	}
	public static byte[] encrypt(String plainText, SecretKey secretKey) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			byte[] plainTextByte = plainText.getBytes();
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] encryptedByte = cipher.doFinal(plainTextByte);
			Base64.Encoder encoder = Base64.getEncoder();
			String encryptedText = encoder.encodeToString(encryptedByte);
			return encryptedText.getBytes();
		} catch (Exception e) {
			ErrorLogServices.logException(e, "encrypt");
		}
		return null;
	}
	public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] encryptedTextByte = decoder.decode(encryptedText);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
		String decryptedText = new String(decryptedByte);
		return decryptedText;
	}
	public static byte[] decrypt(byte[] encryptedByteArray, SecretKey secretKey) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] encryptedTextByte = decoder.decode(encryptedByteArray);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
		return decryptedByte;
	}
	public static SecretKey generateSecretKey() {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(128);
			SecretKey sk = keyGen.generateKey();
			return sk;
		} catch (NoSuchAlgorithmException NSAEx) {
			NSAEx.printStackTrace();
		} catch (Exception ex) {
			ErrorLogServices.logException(ex, "generateSecretKey");
		}
		return null;
	}
	public static SecretKeySpec generateSecretKey(String key) {
		try {
			if (key == null)
				return null;
			byte[] keyBytes = key.getBytes("UTF-8");
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			keyBytes = sha.digest(keyBytes);
			keyBytes = Arrays.copyOf(keyBytes, 16);
			return new SecretKeySpec(keyBytes, "AES");
		} catch (NoSuchAlgorithmException e) {
			ErrorLogServices.logException(e, "generate key");
		} catch (UnsupportedEncodingException e) {
			ErrorLogServices.logException(e, "generateSecretKey key");
		}
		return null;
	}
	public static SecretKeySpec generateSecretKey(byte[] keyBytes) {
		try {
			if (keyBytes == null)
				return null;
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			keyBytes = sha.digest(keyBytes);
			keyBytes = Arrays.copyOf(keyBytes, 16);
			return new SecretKeySpec(keyBytes, "AES");
		} catch (NoSuchAlgorithmException e) {
			ErrorLogServices.logException(e, "generate secrete key ");
		} catch (Exception e) {
			ErrorLogServices.logException(e, "generate secrete key 2");
		}
		return null;
	}
	/**
	 * Function is used to encrypt payment gateway details
	 * 
	 * @param plainText
	 * @return
	 * @throws Exception
	 */
	public static String encryptPGData(String plainText, String inEncryptionKey, String keyType) throws Exception {
		try {
			SecretKey secretKey = generateSecretKey(inEncryptionKey);
			byte[] iv = new byte[16];
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
			Cipher cipher = Cipher.getInstance(keyType);// "AES/CBC/PKCS5PADDING"
			byte[] plainTextByte = plainText.getBytes();
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
			byte[] encryptedByte = cipher.doFinal(plainTextByte);
			Base64.Encoder encoder = Base64.getEncoder();
			String encryptedText = encoder.encodeToString(encryptedByte);
			LogUtil.log("Encrypted Query String " + encryptedText);
			return encryptedText;
		} catch (Exception e) {
			Logger.error("encrypt1 --> error---" + e);
		}
		return null;
	}
	/**
	 * Function is used to decrypt payment gateway details
	 * 
	 * @param strToDecrypt
	 * @return
	 */
	public static String decryptPGData(String strToDecrypt, String inEncryptionKey, String keyType) {
		try {
			SecretKey secretKey = generateSecretKey(inEncryptionKey);
			byte[] iv = new byte[16];
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
			Cipher cipher = Cipher.getInstance(keyType);// "AES/CBC/PKCS5PADDING"
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			ErrorLogServices.logException(e, "decrypty pg data");
			LogUtil.log("Error while decrypting: " + e.toString());
		}
		return null;
	}
	
	
	
	
	
	
	public static String AESEncrypt(String textToEncrypt, String key)
	{
		String ENCRYPTION_ALGORITHM = "AES/CBC/PKCS5Padding";
		SecretKeySpec keySpec;
		byte[] keyBytes = GetKeyAsBytes(key);
		
			for(int i=0;i<keyBytes.length;i++) {
		    	  if(keyBytes[i] == -58) {
		    		  keyBytes[i] = -120;
		    	  }
		      }
			
		keySpec = new SecretKeySpec(keyBytes, "AES");
		byte[] unencrypted = null;
		try {
			unencrypted = textToEncrypt.getBytes("UTF8");
		} catch (UnsupportedEncodingException e) {
			ErrorLogServices.logException(e, "AES Encryption");
		}
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			ErrorLogServices.logException(e, "AES Encryption 2");
		} catch (NoSuchPaddingException e) {
			ErrorLogServices.logException(e, "AES Encryption 3");
		}
		IvParameterSpec iv = new IvParameterSpec(keyBytes);
		try {
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
		} catch (InvalidKeyException e) {
			ErrorLogServices.logException(e, "AES Encryption 4");
		} 
		catch (InvalidAlgorithmParameterException e) {
			
			ErrorLogServices.logException(e, "AES Encryption 5");
		}
		String encryptedPlainText = null;
		try {
			encryptedPlainText = DatatypeConverter.printBase64Binary(cipher.doFinal(unencrypted));
		} catch (IllegalBlockSizeException e) {
			ErrorLogServices.logException(e, "AES Encryption 6");
		} catch (BadPaddingException e) {
			ErrorLogServices.logException(e, "AES Encryption 7");
		}
		LogUtil.log("Encrypted Query String " + encryptedPlainText);
		return encryptedPlainText;
	}
	
	public static String AESDecrypt(String textToDecrypt, String key) throws Exception, BadPaddingException
	{
		LogUtil.log("textToDecrypt >>"+textToDecrypt);
		textToDecrypt = textToDecrypt.replaceAll(" ", "+");
		LogUtil.log("textToDecrypt after space replacement >>"+textToDecrypt);
		String decryptedString = "";
		
		String DECRYPTION_ALGORITHM = "AES/CBC/PKCS5Padding";
		SecretKeySpec keySpec;
		byte[] keyBytes = GetKeyAsBytes(key);
		LogUtil.log("Encrpt key== UU "+keyBytes);
		
		keySpec = new SecretKeySpec(keyBytes, "AES");
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(DECRYPTION_ALGORITHM);
			
		} catch (NoSuchAlgorithmException e) {
			ErrorLogServices.logException(e, "aes decryption 1");
		} catch (NoSuchPaddingException e) {
			ErrorLogServices.logException(e, "aes decryptoin 2");
		}
		IvParameterSpec iv = new IvParameterSpec(keyBytes);
		try {
			cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
		} catch (InvalidKeyException e) {
			ErrorLogServices.logException(e, "aes decryption 3");
		} 
		catch (InvalidAlgorithmParameterException e) {
			ErrorLogServices.logException(e, "aes decryption 4");
		}
		byte[] decryptedPlainText = null;
		decryptedString = new String(cipher.doFinal(DatatypeConverter.parseBase64Binary(textToDecrypt)));
		return decryptedString;
	}
	public static byte[] GetKeyAsBytes(String key) {
	    byte[] keyBytes = new byte[0x10]; 
	    for (int i = 0; i < key.length() && i < keyBytes.length; i++)  { 
	        keyBytes[i] = (byte) key.charAt(i);
	    }
	    
	    return keyBytes;
	}
	
	public static String CreateMD5Hash(String input)
	{
		byte[] inputBytes = null;
		byte[] hashBytes = null;
		try {
			inputBytes = input.getBytes("ASCII");
		} catch (UnsupportedEncodingException e1) {
			ErrorLogServices.logException(e1, "create md5 hash");
		}
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			ErrorLogServices.logException(e, "aes create md5 hash");
		}
		hashBytes = md.digest(inputBytes);
		String hash = new BigInteger(1, hashBytes).toString(16);
		hash = hash.toUpperCase();
		return hash;
	}
	
	
	public static String encryptIV(String dataToEncrypt, String inEncryptionKey, String iVector) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException  {
		 try 
		 {
			 SecretKey key = generateSecretKeyWithoutSHA(inEncryptionKey);
			 byte[] iv = new byte[16];
			iv = iVector.getBytes("UTF-8");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			 
	         cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv)); 
	         
	         byte[] encrypted = cipher.doFinal(dataToEncrypt.getBytes());
	         String encryptValue = new String(Base64.getEncoder().encode(encrypted));
			LogUtil.log(" encryptValue value AES **** " + encryptValue);
			return encryptValue;
	         
		 } catch (Exception e) {
			ErrorLogServices.logException(e, "encryptIV");
		}
        return null;

		}

	public static String decryptWithIV(String strToDecrypt, String inEncryptionKey, String keyType, String iVector) {
			try {
				SecretKey secretKey = generateSecretKeyWithoutSHA(inEncryptionKey);
				byte[] iv = new byte[16];
				iv = iVector.getBytes();
				IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
				Cipher cipher = Cipher.getInstance(keyType);
				cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
				byte[] test = Base64.getDecoder().decode(strToDecrypt);
				String decryptValue = new String(cipher.doFinal(test)).trim();
				LogUtil.log(" decrypt value AES **** " + decryptValue);
				return decryptValue;
			} catch (Exception e) {
				ErrorLogServices.logException(e, "decrypt With I V");
				LogUtil.log("Error while decrypting: " + e.toString());
			}
			return null;
		}
	
	public static String decryptWithIVForALL(String strToDecrypt, String inEncryptionKey, String keyType, String iVector) {
		try {
			//System.out.println("appSettings.get(AES_PADDING_KEY)"+appSettings.get(AES_PADDING_KEY));
			SecretKey secretKey = generateSecretKeyWithoutSHA(inEncryptionKey);
			//65d10945fa8779ea
			byte[] iv = new byte[16];
			iv = iVector.getBytes("UTF-8");
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
			byte[] test = Base64.getDecoder().decode(strToDecrypt);
			String decryptValue = new String(cipher.doFinal(test)).trim();
			LogUtil.log(" decrypt value AES **** " + decryptValue);
			return decryptValue;
		} catch (Exception e) {
			ErrorLogServices.logException(e, "decrypt With I V for aLL");
			LogUtil.log("Error while decrypting: " + e.toString());
		}
		return null;
	}
		
	public static SecretKeySpec generateSecretKeyWithoutSHA(String key) {
			try {
				if (key == null)
					return null;
				byte[] keyBytes = key.getBytes("UTF-8");
				keyBytes = Arrays.copyOf(keyBytes, 16);
				return new SecretKeySpec(keyBytes, "AES");
			} catch (UnsupportedEncodingException e) {
				ErrorLogServices.logException(e, "generateSecretKey key");
			}
			return null;
		}
	
	
	public static byte[] zeroPad(int length, byte[] bytes) {
		byte[] padded = new byte[length]; 
		System.arraycopy(bytes, 0, padded, 0, bytes.length);
		return padded;
		}
	
	public static void main(String[] args) throws  Exception {		
		
//		System.out.println(decryptWithIVForALL("GB/PXQDBCaxucR+OsEe7l9Nmzbf2Jvk9DSEejsrZRAQ=", "aar6tzij8o1snaar", "AES/CBC/PKCS7Padding", "0123456789ABCDEF"));
//		System.out.println(encryptIV("	{	\"arnCode\":\"ARN-88702\",	\"email\":\"shivakanth443@gmail.com\",	\"folio\":\"91029610006\",	\"source\":\"AW\"	}", "aar6tzij8o1snaar", "0123456789ABCDEF"));
		
//			String Key = "AXAMFBANKING2010";		
//			String val = "PRN~391271183$PID~000000008788$MD~P$ITC~NA$CRN~INR$AMT~50000.00$RESPONSE~AUTO$CG~Y";
//			String str=AES.AESEncrypt(val, Key);
			String str1="ILHsqSIBhIHa5UTprJDz7Q0hgvHLzbTp8A1un2MaTIMpSNcEyejM0U2HikD4ADV5soFW11y3PwUPwVMX+4lNBA==";
			String jsonString="{\r\n" + " \"user_id\":\""+"robo"+"\",\r\n"+
					"    \"password\":\""+"qwertyui"+"\",\r\n"+"    \"portal_type\":\""+"D"+"\r\n }";
			String encString="qohCym8v/LVwqcAqUbeL0b0/ssULsAn1g3TX3YYv1biDv2crgQo8MKQJbJNKLKKoSykyOpByff2agFZxgd1bTAWVw2eHvg5S7mA8fHZpSQDaRuq0g1MN/Y81YVqxkdU32r+sL5y9HmRaC+ZZDfN8QrMDR4GTKbML3203BEj6lX03eyW/7R6cCXBt5eV+tsexzebyWBIElLu1VmYGp/E7AxaZpXRqBWThpcz49GXmv/axCRoUVTFula+9w/Rn16YKwWyHTX8M4+gciwlrSFAw1vioKsjiXsgPpRPHJmU0KJg=";
			System.out.println("encrypted text:"+encString);
			String decryptText=decryptWithIVForALL(encString, "ICICILombardGenK", "AES/CBC/PKCS5Padding", "0123456789ABCDEF");
			System.out.println("decryptText"+decryptText);
			
			//LogUtil.log("enc Str>>>>>>>>"+str);		
			
        }

}


