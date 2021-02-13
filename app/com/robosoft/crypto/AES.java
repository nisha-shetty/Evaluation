package com.robosoft.crypto;


import static com.robosoft.constants.AppConstants.AES_PADDING_KEY;
import static com.robosoft.constants.AppConstants.appSettings;
import static com.robosoft.constants.StringConstants.STR_UTF8;

import java.io.UnsupportedEncodingException;
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

import play.Logger;

import com.robosoft.service.ErrorLogServices;
import com.robosoft.utils.LogUtil;

public class AES {

	private static final String ENC_KEY = "AxilloCmsSecKey";
	private AES() {

	}

	public static void main(String[] args) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		SecretKey secretKey = generateSecretKey("kaypay");
		System.out.println(secretKey);
		Cipher cipher = Cipher.getInstance("AES");

		String plainText = "kaypay";
		Logger.info("Plain Text Before Encryption: " + plainText);

		String encryptedText = encrypt1(plainText, secretKey);
		Logger.info("Encrypted Text After Encryption: " + encryptedText);

		String decryptedText = decrypt(encryptedText, secretKey);
		Logger.info("Decrypted Text After Decryption: " + decryptedText);
	}

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
			// TODO Auto-generated catch block
			ErrorLogServices.logException(e, "generate key");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			ErrorLogServices.logException(e, "generateSecretKey key");
		}
		return null;
	}
	
	public static SecretKeySpec generateSecretKey(byte[] keyBytes) {
		try {
			if (keyBytes == null)
				return null;
//			byte[] keyBytes = key.getBytes("UTF-8");
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			keyBytes = sha.digest(keyBytes);
			keyBytes = Arrays.copyOf(keyBytes, 16);
			return new SecretKeySpec(keyBytes, "AES");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String getEncryptedText(String password) {
		 try {
		        String encryptedText;
		        
	            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
	            keyGenerator.init(128);
	            SecretKey secretKey = generateSecretKey(ENC_KEY);
	    
	            encryptedText = encrypt1(password, secretKey);
	            //System.out.println("Encrypted Text After Encryption: " + encryptedText);
	            
	            return encryptedText;
		    }
		    catch (NoSuchAlgorithmException Ex) {
		        
		    }
	        catch (Exception Ex) {
	        }
		    return null;
	}
	
	 public static byte[] getEncryptedBytes(String plainText) {
	        
	        try {
	            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
	            keyGenerator.init(128);
	            SecretKey secretKey = generateSecretKey(ENC_KEY);
	    
	            Cipher cipher = Cipher.getInstance("AES");
	            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	            byte[] encryptedByte = cipher.doFinal(plainText.getBytes());
	            
	            return encryptedByte;
	        }
	        catch (NoSuchAlgorithmException Ex) {
	            
	        }
	        catch (Exception Ex) {
	        }
	        return null;
	    }
	 

		

		public static String decryptWithIV(String strToDecrypt, String inEncryptionKey, String keyType, String iVector) {
				try {
					SecretKey secretKey = generateSecretKeyWithoutSHA(inEncryptionKey);
					byte[] iv = new byte[16];
					iv = iVector.getBytes();
					IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
					Cipher cipher = Cipher.getInstance(keyType);// "AES/CBC/PKCS5PADDING"
					cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
					byte[] test = Base64.getDecoder().decode(strToDecrypt);
					return new String(cipher.doFinal(test));
					//return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Error while decrypting: " + e.toString());
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
					// TODO Auto-generated catch block
					ErrorLogServices.logException(e, "generateSecretKey key");
				}
				return null;
			}
		
		
		public static byte[] zeroPad(int length, byte[] bytes) {
			byte[] padded = new byte[length]; // initialized to zero by JVM
			System.arraycopy(bytes, 0, padded, 0, bytes.length);
			return padded;
			}
		
		public static String AESEncrypt(String textToEncrypt, String key)
		{
			String ENCRYPTION_ALGORITHM = "AES/CBC/PKCS5Padding";
			SecretKeySpec keySpec;
			byte[] keyBytes = GetKeyAsBytes(key);
			
//			for MP - Start
				for(int i=0;i<keyBytes.length;i++) {
			    	  //System.out.println("keyBytes[i] >>"+keyBytes[i]);
			    	  if(keyBytes[i] == -58) {
			    		  keyBytes[i] = -120;
			    	  }
			      }
				//	for MP - End
				
			keySpec = new SecretKeySpec(keyBytes, "AES");
			byte[] unencrypted = null;
			try {
				unencrypted = textToEncrypt.getBytes("UTF8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Cipher cipher = null;
			try {
				cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// IV = 0 is yet another issue, we'll ignore it here
			IvParameterSpec iv = new IvParameterSpec(keyBytes);
			try {
				cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //, iv);
			catch (InvalidAlgorithmParameterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String encryptedPlainText = null;
			try {
				//encryptedPlainText = cipher.doFinal(unencrypted);
				encryptedPlainText = DatatypeConverter.printBase64Binary(cipher.doFinal(unencrypted));
			} catch (IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			LogUtil.log("Encrypted Query String " + encryptedPlainText);
			return encryptedPlainText;
		}
		
		public static String AESDecrypt(String textToDecrypt, String key) throws Exception, BadPaddingException
		{
			System.out.println("textToDecrypt >>"+textToDecrypt);
			textToDecrypt = textToDecrypt.replaceAll(" ", "+");
			System.out.println("textToDecrypt after space replacement >>"+textToDecrypt);
			//byte[] undecrypted = Base64.decode(textToDecrypt);
			String decryptedString = "";
			
			String DECRYPTION_ALGORITHM = "AES/CBC/PKCS5Padding";
			SecretKeySpec keySpec;
			byte[] keyBytes = GetKeyAsBytes(key);
			System.out.println("Encrpt key== UU "+keyBytes);
			
			//	for MP - Start
			/*for(int i=0;i<keyBytes.length;i++) {
		    	  System.out.println("keyBytes[i] >>"+keyBytes[i]);
		    	  if(keyBytes[i] == -58) {
		    		  keyBytes[i] = -120;
		    	  }
		      }*/
			//	for MP - End
			
			keySpec = new SecretKeySpec(keyBytes, "AES");
			Cipher cipher = null;
			try {
				cipher = Cipher.getInstance(DECRYPTION_ALGORITHM);
				
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// IV = 0 is yet another issue, we'll ignore it here
			IvParameterSpec iv = new IvParameterSpec(keyBytes);
			try {
				cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //, iv);
			catch (InvalidAlgorithmParameterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			byte[] decryptedPlainText = null;
			//decryptedString = StringByteConverter.convertByteArrayToString(decryptedPlainText);
			decryptedString = new String(cipher.doFinal(DatatypeConverter.parseBase64Binary(textToDecrypt)));
			return decryptedString;
		}
		
		public static byte[] GetKeyAsBytes(String key) {
		    byte[] keyBytes = new byte[0x10]; 
		    for (int i = 0; i < key.length() && i < keyBytes.length; i++)  { 
		    	//System.out.println("key.charAt(i) >>>"+key.charAt(i));
		        keyBytes[i] = (byte) key.charAt(i);
		        //System.out.println("byte value of "+key.charAt(i)+" >>>"+keyBytes[i]);
		    }
		    /*keyBytes = key.getBytes();
		    for (int i = 0; i < keyBytes.length; i++)  { 
		    	System.out.println("str byte value >>>"+keyBytes[i]);
		    }*/
		    return keyBytes;
		}
		
		public static String decryptWithIVForALL(String strToDecrypt, String inEncryptionKey, String keyType, String iVector) {
			try {
				SecretKey secretKey = generateSecretKeyWithoutSHA(inEncryptionKey);
				byte[] iv = new byte[16];
				iv = iVector.getBytes(STR_UTF8);
				IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
				Cipher cipher = Cipher.getInstance(appSettings.get(AES_PADDING_KEY));
				cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
				byte[] test = Base64.getDecoder().decode(strToDecrypt);
				String decryptValue = new String(cipher.doFinal(test)).trim();
				LogUtil.log(" decrypt value AES **** " + decryptValue);
				return decryptValue;
			} catch (Exception e) {
				ErrorLogServices.logException(e, "decrypt With I V for aLL");
				LogUtil.log("Error while decrypting with iv for all: " + e.toString());
			}
			return null;
		}
		
		public static String encryptIV(String dataToEncrypt, String inEncryptionKey, String iVector) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException  {
			 try 
			 {
				 SecretKey key = generateSecretKeyWithoutSHA(inEncryptionKey);
				 byte[] iv = new byte[16];
//				iv = iVector.getBytes(STR_UTF8);
				iv = iVector.getBytes("UTF-8");
//				Cipher cipher = Cipher.getInstance(appSettings.get(AES_PADDING_KEY));
				// "AES/CBC/NOPADDING"
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

}
