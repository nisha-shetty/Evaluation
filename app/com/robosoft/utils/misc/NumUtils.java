package com.robosoft.utils.misc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class NumUtils {

	public String[] getStringEntityArray(String inStringOfValues) {
		String[] RetVals = null;
		
		if (inStringOfValues != null) {
			RetVals = inStringOfValues.split(",");
		}
		
		return RetVals;
	}
	
	public List<Long> getLongEntitiyList(String inStringOfValues) {
		List<Long> RetVals = null;
		
		try {
			RetVals = Arrays.asList(getLongEntitiyArray(inStringOfValues));
		}
		catch (Exception Ex) {
			Ex.printStackTrace();
		}
		
		return RetVals;
	}
	
	public Long[] getLongEntitiyArray(String inStringOfValues) {
		Long[] RetVals = null;
		
		try {
			long[] aTmp;
			int iCount;
			
			aTmp = Arrays.asList(inStringOfValues.split(",")).stream().map(String :: trim).mapToLong(Long :: parseLong).toArray();
			iCount = aTmp.length;
			RetVals = new Long[iCount];
			for (int i = 0; i < iCount; i++) {
				RetVals[i] = aTmp[i];
			}
		}
		catch (Exception Ex) {
		}
		
		return RetVals;
	}

	public List<Integer> getIntegerEntitiyList(String inStringOfValues) {
		List<Integer> RetVals = null;
		
		try {
			RetVals = Arrays.asList(getIntegerEntitiyArray(inStringOfValues));
		}
		catch (Exception Ex) {
			Ex.printStackTrace();
		}
		
		return RetVals;
	}
	
	public Integer[] getIntegerEntitiyArray(String inStringOfValues) {
		Integer[] RetVals = null;
		
		try {
			int[] aTmp;
			int iCount;
			
			aTmp = Arrays.asList(inStringOfValues.split(",")).stream().map(String :: trim).mapToInt(Integer :: parseInt).toArray();
			iCount = aTmp.length;
			RetVals = new Integer[iCount];
			for (int i = 0; i < iCount; i++) {
				RetVals[i] = aTmp[i];
			}
		}
		catch (Exception Ex) {
		}
		
		return RetVals;
	}

    private static String getDateAsString() {
        String retVal = null;
        try {
            DateFormat df = new SimpleDateFormat("ddMMyyyyhhmmss");
            Date dateobj = new Date();
            retVal = df.format(dateobj);
        }
        catch (Exception Exc) {
        }
        return retVal;
    }

    public String buildDocuentReferenceNumber() {
        String retVal = null;
        try {
            StringBuilder StrBld = new StringBuilder();
            PassCodeGen passCodeGenerator = new PassCodeGen(4, false);;
            String DtPart = getDateAsString();
            String RndPart = passCodeGenerator.get();
            
            StrBld.append(RndPart).append(DtPart);
            retVal = StrBld.toString();
        }
        catch (Exception Ex) {
        }
        return retVal;
    }
        
    
    static class PassCodeGen {
        private static final int charactersSize = 80;
        private static char[] charArr = new char[charactersSize];
        private static int charactersCount = 0;
        private int passwordSize;
        private static boolean isAphanumeric;

        public PassCodeGen(int passwordSize) {
            this.passwordSize = passwordSize;
            this.isAphanumeric = true;
            initCharacters();
        }

        public PassCodeGen(int passwordSize, boolean isAphanumeric) {
            this.passwordSize = passwordSize;
            this.isAphanumeric = isAphanumeric;
            initCharacters();
        }

        private static void initCharacters() {
            int i = 0;
            charactersCount = 0;
            for (int j = 48; j < 58; ++i, ++j, ++charactersCount) {
                charArr[i] = (char) j;
            }
            
            if (isAphanumeric) {
                //Use only if Alphanumeric code is required in OTP
                for (int j = 64; j < 91; ++i, ++j, ++charactersCount) {
                    charArr[i] = (char) j;
                }
                
                for (int j = 97; j < 123; ++i, ++j, ++charactersCount) {
                    charArr[i] = (char) j;
                }
            }
        }

        public String get() {
            Random rnd = new Random();
            char[] password = new char[passwordSize];
            for (int i = 0; i < passwordSize; ++i) {
                password[i] = charArr[rnd.nextInt(charactersCount)];
            }
            return new String(password);
        }
    }
    
        

}
