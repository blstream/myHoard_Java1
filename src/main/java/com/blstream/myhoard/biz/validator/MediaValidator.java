package com.blstream.myhoard.biz.validator;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blstream.myhoard.biz.enums.RequestMethodEnum;
import com.blstream.myhoard.biz.model.MediaDTO;
import com.blstream.myhoard.biz.util.MediaUtils;
import com.blstream.myhoard.exception.ValidatorException;

@Component
public class MediaValidator {

	private static final Logger logger = Logger.getLogger(MediaValidator.class
			.getCanonicalName());

	private static final String MEDIA_NOT_FOUND = "Media not found!";
	@SuppressWarnings("unused")
	private static final String MEDIA_SIZE_NOT_ACCEPTABLE_MAX_SIZE_10MB = "Media size not acceptable. Max size 10MB!";

	private Map<String, String> errorMessages;
	private final String KEY_FILE = "media";

	@Autowired
	MediaUtils mediaUtils;

	public void validate(MediaDTO mediaDTO, RequestMethodEnum requestMethod) {

		errorMessages = new HashMap<>();

		switch (requestMethod) {
		case POST:
			validateCreate(mediaDTO);
			break;
		case PUT:
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

		// long maxSize = 10485760;

		if (file == null) {
			errorMessages.put(KEY_FILE, MEDIA_NOT_FOUND);
			return;
		}

		if (!mediaUtils.getMetadane(file).equals("image/jpeg")
				&& !mediaUtils.getMetadane(file).equals("image/png")) {
			errorMessages.put(KEY_FILE, MEDIA_NOT_FOUND);
			return;
		}

		long start = new Date().getTime();
		try {
			ImageIO.read(new ByteArrayInputStream(file));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			errorMessages.put(KEY_FILE, MEDIA_NOT_FOUND);
			return;
		}
		long elapsed = new Date().getTime() - start;

		logger.info("ImageIO.read: [ms]: " + elapsed);

		// if (file.length > maxSize) {
		// errorMessages
		// .put(KEY_FILE, MEDIA_SIZE_NOT_ACCEPTABLE_MAX_SIZE_10MB);
		// }

	}

	private void checkError() {
		if (errorMessages.size() > 0) {
			throw new ValidatorException(errorMessages);
		}

	}

}
