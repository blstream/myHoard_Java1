package com.blstream.myhoard.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.blstream.myhoard.biz.model.CollectionDTO;
import com.blstream.myhoard.biz.service.CollectionService;
import com.blstream.myhoard.biz.validator.CollectionValidator;
import com.blstream.myhoard.biz.validator.RequestValidator;
import com.blstream.myhoard.exception.ErrorCodeEnum;
import com.blstream.myhoard.exception.MyHoardException;
import com.blstream.myhoard.exception.MyHoardRestException;
import com.blstream.myhoard.exception.NotFoundException;
import com.blstream.myhoard.exception.ResourceAlreadyExistException;

@Controller
@RequestMapping("/collections")
public class CollectionController {

	private static final Logger logger = Logger
			.getLogger(CollectionController.class.getCanonicalName());

	@Autowired
	private CollectionService collectionService;

	@Autowired
	private CollectionValidator collectionValidator;

	@Autowired
	private RequestValidator requestValidator;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public CollectionDTO addCollection(@RequestBody CollectionDTO collection)
			throws MyHoardException {

		collectionValidator.validate(collection, "post");

		return collectionService.create(collection);
	}

	@RequestMapping(value = "/{collectionId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public CollectionDTO getCollection(
			@PathVariable("collectionId") String idStr) {

		requestValidator.validId(idStr);
		try {
			return collectionService.get(Integer.parseInt(idStr));
		} catch (MyHoardException e) {
			logger.error(e.getMessage(), e);
			throw new NotFoundException(String.format(
					"Collection with id = %s not exist", idStr));
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<CollectionDTO> getCollections(
			@RequestParam(value = "sort_by", required = false) List<String> sortBy,
			@RequestParam(value = "sort_direction", required = false) String sortDirection) {

		if (sortBy != null || sortDirection != null) {
			if (sortBy == null || sortDirection == null) {
				throw new MyHoardRestException(400);
			} else {

				String[] availableFields = { "name", "description",
						"created_date", "modified_date", "owner" };
				String[] availableDirection = { "asc", "desc" };

				for (String sortByElem : sortBy) {
					boolean everythingOk = false;
					for (String available : availableFields) {
						if (sortByElem.equals(available)) {
							everythingOk = true;
							break;
						}
					}
					if (!everythingOk) {
						throw new MyHoardRestException(1000);
					}
					everythingOk = false;
				}

				if (!sortDirection.equals(availableDirection[0])
						&& !sortDirection.equals(availableDirection[1])) {
					throw new MyHoardRestException(1000);
				}

				for (int i = 0; i < sortBy.size(); i++) {
					if (sortBy.get(i).equals("modified_date")) {
						sortBy.set(i, "modifiedDate");
					} else if (sortBy.get(i).equals("created_date")) {
						sortBy.set(i, "createdDate");
					}
				}

				try {
					return collectionService.getList(sortBy, sortDirection); // TODO
																				// Matuesz
																				// -
																				// owner
				} catch (MyHoardException e) {
					logger.error("getList error", e);
				}
			}
		}

		try {
			return collectionService.getList();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new MyHoardRestException(ErrorCodeEnum.READ.getValue());
		}
	}

	@RequestMapping(value = "/{collectionId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public void deleteCollection(@PathVariable("collectionId") String idStr) {

		requestValidator.validId(idStr);

		try {
			collectionService.remove(Integer.parseInt(idStr));
		} catch (MyHoardException e) {
			logger.error(e.getMessage(), e);
			throw new NotFoundException(String.format(
					"Collection with id = %s not exist", idStr));
		}

	}

	@RequestMapping(value = "/{collectionId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public CollectionDTO updateCollection(
			@PathVariable("collectionId") String idStr,
			@RequestBody CollectionDTO collection) {

		requestValidator.validId(idStr);
		try {
			collection.setId(idStr);
			return collectionService.update(collection);
		} catch (ResourceAlreadyExistException ex) {
			logger.error(ex.getMessage(), ex);
			throw new ResourceAlreadyExistException(String.format(
					"Collection with name: %s already exist!",
					collection.getName()));
		} catch (Exception e) {
			throw new NotFoundException(String.format(
					"Collection with id = %s not exist", idStr));
		}
	}
}
