package com.blstream.myhoard.exception;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

public class ErrorCode {

	@JsonProperty("error_code")
	private int errorCode;

	@JsonProperty("error_message")
	private String errorMessage;

	private Map<String, String> errors;

	public ErrorCode() {
	}

	public ErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public ErrorCode(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public ErrorCode(int errorCode, String errorMessage,
			Map<String, String> errors) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errors = errors;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

}
