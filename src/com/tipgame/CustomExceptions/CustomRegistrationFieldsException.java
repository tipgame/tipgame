package com.tipgame.CustomExceptions;

public class CustomRegistrationFieldsException extends Exception{

	private static final long serialVersionUID = 2632243964688486954L;
	private String message;
	
	public CustomRegistrationFieldsException(String message)
	{
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
