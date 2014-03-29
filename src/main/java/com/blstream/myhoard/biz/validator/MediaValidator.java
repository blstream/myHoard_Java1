package com.blstream.myhoard.biz.validator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.blstream.myhoard.biz.model.MediaDTO;
import com.blstream.myhoard.exception.MySuperExtraException;

@Component
public class MediaValidator {

	private static final String MEDIA_NOT_FOUND = "Media not found!";
	private static final String MEDIA_SIZE_NOT_ACCEPTABLE_MAX_SIZE_10MB = "Media size not acceptable. Max size 10MB!";

	private Map<String, String> errorMessages;
	private final String KEY_FILE = "media";

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
			errorMessages.put(KEY_FILE, MEDIA_NOT_FOUND);
			return;
		}

		if (file.length > maxSize) {
			errorMessages
					.put(KEY_FILE, MEDIA_SIZE_NOT_ACCEPTABLE_MAX_SIZE_10MB);
		}

	}

	private void checkError() {
		if (errorMessages.size() > 0) {
			throw new MySuperExtraException(errorMessages);
		}

	}

}
