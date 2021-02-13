package com.robosoft.dto.returnValues;


public class SuccessConfirmReturnValue extends BaseReturnValue {

    private String object = "confirmation";
    private String message = "Success";

	public SuccessConfirmReturnValue(String referenceNo, String requestTime) {
		super(referenceNo, requestTime);
	}

    public String getObject() {
        return object;
    }

    public void setObject( String object ) {
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage( String message ) {
        this.message = message;
    }


}
