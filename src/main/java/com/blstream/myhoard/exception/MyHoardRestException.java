package com.blstream.myhoard.exception;

public class MyHoardRestException extends RuntimeException {

	private static final long serialVersionUID = -2241431205864065754L;

	private int code;
	private String errorReason;

	public MyHoardRestException() {
		super();
	}

	public MyHoardRestException(int code) {
		super();
		this.code = code;
	}

	public MyHoardRestException(int code, String errorReason) {
		super();
		this.code = code;
		this.errorReason = errorReason;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getErrorReason() {
		return errorReason;
	}

	public void setErrorReason(String errorReason) {
		this.errorReason = errorReason;
	}

}
