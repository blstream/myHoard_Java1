package com.blstream.myhoard.exception;

public class CollectionException extends RuntimeException {

	private static final long serialVersionUID = -2241431205864065754L;

	private int code;

	public CollectionException() {
	}

	public CollectionException(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
