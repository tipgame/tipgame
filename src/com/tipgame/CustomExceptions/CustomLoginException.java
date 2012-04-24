package com.tipgame.CustomExceptions;

public class CustomLoginException extends Exception{

	private static final long serialVersionUID = -4224799576582173382L;
	private String message;
	
	public CustomLoginException(String message)
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
