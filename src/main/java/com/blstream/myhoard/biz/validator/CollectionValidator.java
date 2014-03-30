package com.blstream.myhoard.biz.validator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.blstream.myhoard.biz.model.CollectionDTO;
import com.blstream.myhoard.exception.MySuperExtraException;

@Component
public class CollectionValidator {

	private static final String DESCRIPTION_MAX_SIZE_128 = "Description max size = 128";
	private static final String NAME_MAX_SIZE_96 = "Name max size = 96";
	private static final String NAME_MINIMUM_SIZE_2 = "Name minimum size = 2";

	private Map<String, String> errorMessages;

	private final String KEY_NAME = "name";
	private final String KEY_DESCRIPTION = "description";

	public void validate(CollectionDTO collectionDTO, String requestMethod) {

		errorMessages = new HashMap<>();

		switch (requestMethod) {
		case "post":
			validateCreate(collectionDTO);
			break;
		case "put":
			validateUpdate(collectionDTO);
			break;
		default:
			break;
		}

	}

	private void validateCreate(CollectionDTO collectionDTO) {

		validNameCreate(collectionDTO.getName());
		validDescription(collectionDTO.getDescription());

		checkError();

	}

	private void validateUpdate(CollectionDTO collectionDTO) {

		validNameUpdate(collectionDTO.getName());
		validDescription(collectionDTO.getDescription());

		checkError();

	}

	private void checkError() {
		if (errorMessages.size() > 0) {
			throw new MySuperExtraException(errorMessages);
		}
	}

	private void validNameCreate(String name) {

		if (name == null) {
			errorMessages.put(KEY_NAME, NAME_MINIMUM_SIZE_2);
			return;
		}

		name = name.trim();

		if (name.length() < 2) {
			errorMessages.put(KEY_NAME, NAME_MINIMUM_SIZE_2);
		}

		if (name.length() > 96) {
			errorMessages.put(KEY_NAME, NAME_MAX_SIZE_96);
		}

	}

	private void validNameUpdate(String name) {

		if (name == null) {
			return;
		}

		name = name.trim();

		if (name.length() < 2) {
			errorMessages.put(KEY_NAME, NAME_MINIMUM_SIZE_2);
		}

	}

	private void validDescription(String description) {
		
		if (description == null) {
			return;
		}

		description = description.trim();

		if (description.length() > 128) {
			errorMessages.put(KEY_DESCRIPTION, DESCRIPTION_MAX_SIZE_128);
		}

	}

}
