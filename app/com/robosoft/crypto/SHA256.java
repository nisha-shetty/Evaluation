package com.robosoft.crypto;

import java.security.MessageDigest;
import java.security.SecureRandom;

public class SHA256 {
	
	private final static int HASHING_ITERATION = 1;
	
	private static SHA256 SHA256instance;
	
	public static SHA256 getInstance(){
		if(SHA256instance == null){
			SHA256instance = new SHA256();
		}
		return SHA256instance;
	}
	
	private SHA256(){
		
	}
	
	
	public String hash(String stringToHash, byte[] salt){
		
		try {
			
			byte[] hash = getHash(stringToHash, salt);
			if( hash == null ){
				return null;
			}
			
			return hexToString(hash);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public byte[] getHash(String stringToHash, byte[] salt){
		
		try {
			
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(salt);
			byte[] passDigest =  digest.digest( stringToHash.getBytes("UTF-8") );
			
			for(int i=0; i<HASHING_ITERATION; i++){
				digest.reset();
				passDigest = digest.digest(passDigest);
			}
			
			return passDigest;
		

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	private String hexToString(byte[] inHex){
		
		try {
		
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < inHex.length; i++) {
				String hex = Integer.toHexString(0xff & inHex[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public byte[] getNewSalt(){
		
		try {
			
			byte[] salt = new byte[32];
			SecureRandom.getInstance("SHA1PRNG").nextBytes(salt);
			return salt;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
