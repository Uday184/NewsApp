package com.stackroute.newsapp.exception;

@SuppressWarnings("serial")
public class NewsAlreadyExistsException extends Exception{
	
	String message;

	public NewsAlreadyExistsException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "NewsAlreadyExistsException [message = "+message+"]";
	}

}
