package com.prizy.exception;

public class ObjectAlreadyExistException extends Exception {
	private static final long serialVersionUID = -9003549790478925072L;

	public ObjectAlreadyExistException(String msg){
		super(msg);
	}
}
