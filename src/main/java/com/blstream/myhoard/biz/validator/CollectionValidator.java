package com.blstream.myhoard.biz.validator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blstream.myhoard.biz.enums.RequestMethodEnum;
import com.blstream.myhoard.biz.model.CollectionDTO;
import com.blstream.myhoard.biz.service.CollectionService;
import com.blstream.myhoard.exception.ValidatorException;

@Component
public class CollectionValidator {

	private static final String DESCRIPTION_MAX_SIZE_900 = "Description max size = 900";
	private static final String NAME_MAX_SIZE_64 = "Name max size = 64";
	private static final String NAME_MINIMUM_SIZE_2 = "Name minimum size = 2";

	private final String KEY_NAME = "name";
	private final String KEY_DESCRIPTION = "description";

	@Autowired
	private CollectionService collectionService;

	private Map<String, String> errorMessages;

	public void validate(CollectionDTO collectionDTO, RequestMethodEnum requestMethod) {

		errorMessages = new HashMap<>();

		switch (requestMethod) {
		case POST:
			validateCreate(collectionDTO);
			break;
		case PUT:
			validateUpdate(collectionDTO);
			break;
		default:
			break;
		}

	}

	private void validateCreate(CollectionDTO collectionDTO) {

		validNameCreate(collectionDTO);
		validDescription(collectionDTO);

		checkError();

	}

	private void validateUpdate(CollectionDTO collectionDTO) {

		validNameUpdate(collectionDTO);
		validDescription(collectionDTO);

		checkError();

	}

	private void checkError() {
		if (errorMessages.size() > 0) {
			throw new ValidatorException(errorMessages);
		}
	}

	private void validNameCreate(CollectionDTO collectionDTO) {

		if (collectionDTO.getName() == null) {
			errorMessages.put(KEY_NAME, NAME_MINIMUM_SIZE_2);
			return;
		}

		collectionDTO.setName(collectionDTO.getName().trim());

		if (collectionDTO.getName().length() < 2) {
			errorMessages.put(KEY_NAME, NAME_MINIMUM_SIZE_2);
		}

		if (collectionDTO.getName().length() > 64) {
			errorMessages.put(KEY_NAME, NAME_MAX_SIZE_64);
		}

		isNameUnique(collectionDTO.getName());

	}

	private void validNameUpdate(CollectionDTO collectionDTO) {

		if (collectionDTO.getName() == null) {
			return;
		}

		collectionDTO.setName(collectionDTO.getName().trim());

		if (collectionDTO.getName().length() < 2) {
			errorMessages.put(KEY_NAME, NAME_MINIMUM_SIZE_2);
		}
		
		if (collectionDTO.getName().length() > 64) {
			errorMessages.put(KEY_NAME, NAME_MAX_SIZE_64);
		}

		isNameUnique(collectionDTO.getName());

	}

	private void validDescription(CollectionDTO collectionDTO) {

		if (collectionDTO.getDescription() == null) {
			return;
		}

		collectionDTO.setDescription(collectionDTO.getDescription().trim());

		if (collectionDTO.getDescription().length() > 900) {
			errorMessages.put(KEY_DESCRIPTION, DESCRIPTION_MAX_SIZE_900);
		}

	}

	private void isNameUnique(String name) {
		if (!collectionService.isNameUnique(name.trim())) {
			errorMessages.put(KEY_NAME, String.format(
					"Collection with name: %s already exist!", name.trim()));
		}
	}

}
