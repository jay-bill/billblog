package com.jaybill.billblog.exception;

public class CantFindThisIdException extends RuntimeException {
	private static final long serialVersionUID = -8365338039348799277L;
	public CantFindThisIdException(String msg){
		super(msg);
	}
}
