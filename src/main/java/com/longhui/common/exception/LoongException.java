package com.longhui.common.exception;

public class LoongException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoongException() {
		super();
	}

	public LoongException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LoongException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoongException(String message) {
		super(message);
	}

	public LoongException(Throwable cause) {
		super(cause);
	}
	
	

}
