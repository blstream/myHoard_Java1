package com.blstream.myhoard.exception;

public enum ErrorCodeEnum {
	CREATE(400), READ(300), UPDATE(111), DELETE(400);

	private final int value;

	private ErrorCodeEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
