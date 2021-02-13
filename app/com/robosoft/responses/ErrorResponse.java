package com.robosoft.responses;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.robosoft.dto.returnValues.ServiceReturnValue;

@JsonInclude (JsonInclude.Include.NON_NULL)
public class ErrorResponse implements ServiceReturnValue {
	
	private String object = "error";
	private String domain;
	private String code;
	private String description;
	private String user_friendly_message;
	private String culprit;
	private List<Error> errors;
	
	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorResponse(String domain) {
		this.domain = domain;
	}

    public ErrorResponse(String code, String errorMessage) {
        super();
        this.code = code;
        this.description = errorMessage;
    }
    
	public ErrorResponse(String domain, String code, String errorMessage, String userFriendlyMessage) {
		super();
		this.domain = domain;
		this.code = code;
		this.description = errorMessage;
		this.user_friendly_message = userFriendlyMessage;
	}
	
	public String getObject() {
        return object;
    }

    public void setObject( String object ) {
        this.object = object;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain( String domain ) {
        this.domain = domain;
    }

    public String getCode() {
        return code;
    }

    public void setCode( String code ) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public String getUser_friendly_message() {
        return user_friendly_message;
    }

    public void setUser_friendly_message( String user_friendly_message ) {
        this.user_friendly_message = user_friendly_message;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors( List<Error> errors ) {
        this.errors = errors;
    }

    public String getCulprit() {
        return culprit;
    }

    public void setCulprit( String culprit ) {
        this.culprit = culprit;
    }

    public void addErrors(String domain, String code, String errorMessage, String userFriendlyMessage, String culprit) {
		Error errorObj;
		if (errors == null) {
			errors = new ArrayList(8);
		}
		errorObj = new Error();
		errorObj.setCode( code );
		errorObj.setDescription( errorMessage );
		errorObj.setDomain( domain );
		errorObj.setUser_friendly_message( userFriendlyMessage );
		errorObj.setCulprit( culprit );
		errors.add( errorObj );
	}
	
	
	public class Error{
		private String object = "error";
		private String domain;
		private String code;
		private String description;
		private String user_friendly_message;
        private String culprit;
		
        public String getObject() {
            return object;
        }
        public void setObject( String object ) {
            this.object = object;
        }
        public String getDomain() {
            return domain;
        }
        public void setDomain( String domain ) {
            this.domain = domain;
        }
        public String getCode() {
            return code;
        }
        public void setCode( String code ) {
            this.code = code;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription( String description ) {
            this.description = description;
        }
        public String getUser_friendly_message() {
            return user_friendly_message;
        }
        public void setUser_friendly_message( String user_friendly_message ) {
            this.user_friendly_message = user_friendly_message;
        }
        public String getCulprit() {
            return culprit;
        }
        public void setCulprit( String culprit ) {
            this.culprit = culprit;
        }
		
	}

}

