package com.blstream.myhoard.biz.validator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.blstream.myhoard.biz.model.MediaDTO;
import com.blstream.myhoard.exception.MySuperExtraException;

@Component
public class MediaValidator {

	private Map<String, String> errorMessages;
	private final String KEY = "media";

	public void validate(MediaDTO mediaDTO, String requestMethod) {

		errorMessages = new HashMap<>();

		switch (requestMethod) {
		case "post":
			validateCreate(mediaDTO);
			break;
		case "put":
			validateUpdate(mediaDTO);
			break;
		default:
			break;
		}

	}

	private void validateCreate(MediaDTO mediaDTO) {

		validateFile(mediaDTO.getFile());
		checkError();

	}

	private void validateUpdate(MediaDTO mediaDTO) {

		validateFile(mediaDTO.getFile());
		checkError();

	}

	private void validateFile(byte[] file) {

		long maxSize = 10485760;

		if (file == null) {
			errorMessages.put(KEY, "Media not found!");
			return;
		}

		if (file.length > maxSize) {
			errorMessages.put(KEY, "Media size not acceptable. Max size 10MB!");
		}

	}

	private void checkError() {
		if (errorMessages.size() > 0) {
			throw new MySuperExtraException(errorMessages);
		}

	}

}
