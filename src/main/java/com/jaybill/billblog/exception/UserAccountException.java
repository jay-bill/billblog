package com.jaybill.billblog.exception;

public class UserAccountException extends RuntimeException{

	private static final long serialVersionUID = -3633127985206474897L;
	
	public UserAccountException(String message){
		super(message);
	}
}
