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
public class FavouriteCollectionValidator {

	private static final String KEY_COLLECTION = "collection";

	private static final String KEY_USER = "user";

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
		case GET:
			validateGet(idUser);
			break;
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
		isPossibleToRemoveFromFavourite(idCollection, idUser);
	}

	private void isPossibleToRemoveFromFavourite(int idCollection, int idUser) {
		List<CollectionDS> listOfUserFavouriteCollections = userDAO
				.getListOfUserFavouriteCollections(idUser);

		boolean alreadyExist = false;
		for (CollectionDS collection : listOfUserFavouriteCollections) {
			if (collection.getId() == idCollection) {
				alreadyExist = true;
				break;
			}
		}

		if (!alreadyExist) {
			errorMessages.put(KEY_COLLECTION,
					"You don't favourite this collection!");
			return;
		}
	}

	private void validateCreate(int idCollection, int idUser) {
		isPossibleAddToFavourite(idCollection, idUser);

	}

	private void checkError() {
		if (errorMessages.size() > 0) {
			throw new ValidatorException(errorMessages);
		}
	}

	private void isPossibleAddToFavourite(int idCollection, int idUser) {

		List<CollectionDS> listOfUserFavouriteCollections = userDAO
				.getListOfUserFavouriteCollections(idUser);

		boolean alreadyExist = false;
		for (CollectionDS collection : listOfUserFavouriteCollections) {
			if (collection.getId() == idCollection) {
				alreadyExist = true;
				break;
			}
		}

		if (alreadyExist) {
			errorMessages.put(KEY_COLLECTION,
					"You already favourite this collection!");
			return;
		}

		CollectionDTO collection = null;
		try {
			collection = collectionService.getById(idCollection);
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
	
	private void validateGet(int idUser) {
		isUserExist(idUser);
	}

	private void isUserExist(int idUser) {
		if(userDAO.get(idUser) == null ) {
			errorMessages.put(KEY_USER, "User with id: "+ idUser + " not exist!");
		}
	}

}
