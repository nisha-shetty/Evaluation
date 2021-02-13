package com.robosoft.utils.misc;

import java.util.ArrayList;

import play.mvc.Http;

public class CustomContextPathUtil {

    /**
     * Prepares the context path to be used for reading service handler element appropriate to the given context path
     * Used only for those API requests which has custom path for those uri which passes arguments to the API through path 
     * parameters
     * 
     * @param ctxPath : Context path string to be verified and altered based on the required customization
     * @return - Context path of the API request
     */
    public String getServiceContextPath( String ctxPath, String httpMethod ) {
        String retVal = ctxPath;
        
        System.out.println("ctxPath........................................"+ctxPath);
        try {
            String toCheck = ctxPath;
            ArrayList<String> customPathModificationList = new ArrayList<String>();
            /*
             * If the path contains the matched text of an element in customPathModificationList, and it must be excluded 
             * from path modification then add it to customPathExclusionList
             * 
             * For example,
             * If the path detected is "/ckycstatuslist" then the part "/ckyc" matches the custom modification list, and 
             * obviously it is a candidate for customer path modification. To avail this rubtime modification of path, 
             * add "/ckycstatuslist" to  customPathExclusionList
             */
            ArrayList<String> customPathExclusionList = new ArrayList<String>();  
            
            customPathModificationList.add( "/role" );
            customPathModificationList.add( "/region" );
            customPathModificationList.add( "/branch" );
            customPathModificationList.add( "/listbranch" );
            customPathModificationList.add( "/reguser" );
            customPathModificationList.add( "/listreguser" );
            customPathModificationList.add( "/ckyc" );
            customPathModificationList.add( "/masterckyc" );
            customPathModificationList.add( "/ckycupdate" );
            customPathModificationList.add( "/getrejectionreason" );
            customPathModificationList.add( "/deletecersaibatch" );
            
            if ( !customPathModificationList.stream().anyMatch( Ob -> toCheck.contains( Ob )) ) {
                return retVal;
            }
            
            customPathExclusionList.add( "/ckycstatuslist" );
            customPathExclusionList.add( "/ckycauthorization" );
            customPathExclusionList.add( "/ckycsearchidtypelist" );
            if ( customPathExclusionList.stream().anyMatch( Ob -> toCheck.contains( Ob )) ) {
                return retVal;
            }
            
            System.out.println("ctx.request().method()........................................"+httpMethod);
            
            switch (httpMethod.toLowerCase()) {
                case "get" :
                    if (retVal.contains( "/role" )) {
                        retVal = retVal.substring( 0, retVal.indexOf( "/role" ) + 5 ).concat( "/get" );
                    } else if (retVal.contains( "/region" )) {
                        retVal = retVal.substring( 0, retVal.indexOf( "/region" ) + 7 ).concat( "/get" );
                    } else if (retVal.contains( "/branch" )) {
                        retVal = retVal.substring( 0, retVal.indexOf( "/branch" ) + 7 ).concat( "/get" );
                    } else if (retVal.contains( "/listbranch" )) {
                        retVal = retVal.substring( 0, retVal.indexOf( "/listbranch" ) + 11 );
                    } else if (retVal.contains( "/reguser" )) {
                        retVal = retVal.substring( 0, retVal.indexOf( "/reguser" ) + 8 ).concat( "/get" );
                    } else if (retVal.contains( "/listreguser" )) {
                        retVal = retVal.substring( 0, retVal.indexOf( "/listreguser" ) + 12 );
                    } else if (retVal.contains( "/ckyc" )) {
                        retVal = retVal.substring( 0, retVal.indexOf( "/ckyc" ) + 5 ).concat( "/get" );
                    } else if (retVal.contains( "/masterckyc" )) {
                        retVal = retVal.substring( 0, retVal.indexOf( "/masterckyc" ) + 11 );
                    } else if (retVal.contains( "/getrejectionreason" )) {
                        retVal = retVal.substring( 0, retVal.indexOf( "/getrejectionreason" ) + 19 );
                    } 
                    break;
                case "post" :
                    if (retVal.contains( "/role" )) {
                        retVal = retVal.substring( 0, retVal.indexOf( "/role" ) + 5 );
                    } else if (retVal.contains( "/region" )) {
                        retVal = retVal.substring( 0, retVal.indexOf( "/region" ) + 7 );
                    } else if (retVal.contains( "/branch" )) {
                        retVal = retVal.substring( 0, retVal.indexOf( "/branch" ) + 7 );
                    } else if (retVal.contains( "/reguser" )) {
                        retVal = retVal.substring( 0, retVal.indexOf( "/reguser" ) + 8 );
                    } 
                    break;
                case "put" :
                    if (retVal.contains( "/role" )) {
                        retVal = retVal.substring( 0, retVal.indexOf( "/role" ) + 5 ).concat( "/update" );
                    } else if (retVal.contains( "/region" )) {
                        retVal = retVal.substring( 0, retVal.indexOf( "/region" ) + 7 ).concat( "/update" );
                    } else if (retVal.contains( "/branch" )) {
                        retVal = retVal.substring( 0, retVal.indexOf( "/branch" ) + 7 ).concat( "/update" );
                    } else if (retVal.contains( "/reguser" )) {
                        retVal = retVal.substring( 0, retVal.indexOf( "/reguser" ) + 8 ).concat( "/update" );
                    } else if (!retVal.contains( "/ckycupdate" ) && retVal.contains( "/ckyc" )) {
                        retVal = retVal.substring( 0, retVal.indexOf( "/ckyc" ) + 5 ).concat( "/update" );
                    } else if (retVal.contains( "/ckycupdate" )) {
                        retVal = retVal.substring( 0, retVal.indexOf( "/ckycupdate" ) + 11 ).concat( "/update" );
                    } 
                    break;
                case "delete" :
                    if (retVal.contains( "/role" )) {
                        retVal = retVal.substring( 0, retVal.indexOf( "/role" ) + 5 ).concat( "/delete" );
                    } else if (retVal.contains( "/region" )) {
                        retVal = retVal.substring( 0, retVal.indexOf( "/region" ) + 7 ).concat( "/delete" );
                    } else if (retVal.contains( "/branch" )) {
                        retVal = retVal.substring( 0, retVal.indexOf( "/branch" ) + 7 ).concat( "/delete" );
                    } else if (retVal.contains( "/reguser" )) {
                        retVal = retVal.substring( 0, retVal.indexOf( "/reguser" ) + 8 ).concat( "/delete" );
                    } else if (retVal.contains( "/deletecersaibatch" )) {
                        retVal = retVal.substring( 0, retVal.indexOf( "/deletecersaibatch" ) + 18 );
                    } 
                    break;
            }
            
        } catch (Exception Exc) {
            
        }
        System.out.println("retVal........................................"+retVal);
        return retVal;
    }

    
    /**
     * Prepares the context path to be used for reading service handler element appropriate to the given context path
     * Used only for those API requests which has custom path for those uri which passes arguments to the API through path 
     * parameters
     * 
     * @param ctx : Http context
     * @return - Context path of the API request
     */
    public String getServiceContextPath( Http.Context ctx ) {
        return getServiceContextPath( ctx.request().path(), ctx.request().method() );
    }
    

}
