package com.lowCarbPower.lowCarbPowerportal.exception;

public class ThirdPartyException extends Exception{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 704989975917096936L;
	
	private Integer code;
    private String userMessage;

    public ThirdPartyException(Integer code, String userMessage, String developerMessage){
        super(developerMessage);
        this.code = code;
        this.userMessage = userMessage;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
}
