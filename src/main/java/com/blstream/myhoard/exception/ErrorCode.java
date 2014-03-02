package com.blstream.myhoard.exception;

public class ErrorCode {

	private int error_code;
	private String error_reason;

	public ErrorCode() {
	}

	public ErrorCode(int error_code) {
		this.error_code = error_code;
	}

	public ErrorCode(int error_code, String error_reason) {
		this.error_code = error_code;
		this.error_reason = error_reason;
	}

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

	public String getError_reason() {
		return error_reason;
	}

	public void setError_reason(String error_reason) {
		this.error_reason = error_reason;
	}

}
