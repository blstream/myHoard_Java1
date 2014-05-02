package com.blstream.myhoard.biz.validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blstream.myhoard.biz.enums.RequestMethodEnum;
import com.blstream.myhoard.biz.model.CollectionDTO;
import com.blstream.myhoard.biz.service.CollectionService;
import com.blstream.myhoard.biz.service.UserService;
import com.blstream.myhoard.db.dao.UserDAO;
import com.blstream.myhoard.db.model.CollectionDS;
import com.blstream.myhoard.exception.MyHoardException;
import com.blstream.myhoard.exception.ValidatorException;

@Component
public class FavoriteCollectionValidator {

	private static final String KEY_COLLECTION = "collection";

	@Autowired
	private CollectionService collectionService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserDAO userDAO;

	private Map<String, String> errorMessages;

	public void validate(int idCollection, int idUser,
			RequestMethodEnum requestMethod) {

		errorMessages = new HashMap<>();

		switch (requestMethod) {
		case POST:
			validateCreate(idCollection, idUser);
			break;
		case DELETE:
			validateDelete(idCollection, idUser);
			break;
		default:
			break;
		}
		checkError();
	}

	private void validateDelete(int idCollection, int idUser) {
		isPossibleToRemoveFromFavorite(idCollection, idUser);
	}

	private void isPossibleToRemoveFromFavorite(int idCollection, int idUser) {
		List<CollectionDS> listOfUserFavoriteCollections = userDAO
				.getListOfUserFavoriteCollections(idUser);

		boolean alreadyExist = false;
		for (CollectionDS collection : listOfUserFavoriteCollections) {
			if (collection.getId() == idCollection) {
				alreadyExist = true;
				break;
			}
		}

		if (!alreadyExist) {
			errorMessages.put(KEY_COLLECTION,
					"You don't favorite this collection!");
			return;
		}
	}

	private void validateCreate(int idCollection, int idUser) {
		isPossibleAddToFavorite(idCollection, idUser);

	}

	private void checkError() {
		if (errorMessages.size() > 0) {
			throw new ValidatorException(errorMessages);
		}
	}

	private void isPossibleAddToFavorite(int idCollection, int idUser) {

		List<CollectionDS> listOfUserFavoriteCollections = userDAO
				.getListOfUserFavoriteCollections(idUser);

		boolean alreadyExist = false;
		for (CollectionDS collection : listOfUserFavoriteCollections) {
			if (collection.getId() == idCollection) {
				alreadyExist = true;
				break;
			}
		}

		if (alreadyExist) {
			errorMessages.put(KEY_COLLECTION,
					"You already favorite this collection!");
			return;
		}

		CollectionDTO collection = null;
		try {
			collection = collectionService.get(idCollection);
		} catch (MyHoardException e) {
			e.printStackTrace();
		}

		if (collection == null) {
			errorMessages.put(KEY_COLLECTION, "Collection not exist!");
		} else {
			if (!collection.getIsPublic()) {
				if (!collection.getOwner().getId()
						.equals(String.valueOf(idUser))) {
					errorMessages.put(KEY_COLLECTION,
							"Sorry! Collection is private!");
				}
			}
		}

	}

}
