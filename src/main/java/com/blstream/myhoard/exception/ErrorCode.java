package com.blstream.myhoard.exception;

import org.codehaus.jackson.annotate.JsonProperty;

public class ErrorCode {

	@JsonProperty("error_code")
	private int errorCode;

	@JsonProperty("error_reason")
	private String errorReason;

	public ErrorCode() {
		super();
	}

	public ErrorCode(int errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public ErrorCode(int errorCode, String errorReason) {
		super();
		this.errorCode = errorCode;
		this.errorReason = errorReason;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorReason() {
		return errorReason;
	}

	public void setErrorReason(String errorReason) {
		this.errorReason = errorReason;
	}

}
