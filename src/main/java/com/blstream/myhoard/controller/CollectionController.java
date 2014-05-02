package com.blstream.myhoard.controller;

import com.blstream.myhoard.authorization.service.SecurityService;
import com.blstream.myhoard.biz.enums.RequestMethodEnum;
import com.blstream.myhoard.biz.model.CollectionDTO;
import com.blstream.myhoard.biz.model.CommentDTO;
import com.blstream.myhoard.biz.model.ItemDTO;
import com.blstream.myhoard.biz.service.CollectionService;
import com.blstream.myhoard.biz.service.CommentService;
import com.blstream.myhoard.biz.service.ItemService;
import com.blstream.myhoard.biz.service.UserService;
import com.blstream.myhoard.biz.validator.CollectionValidator;
import com.blstream.myhoard.biz.validator.FavoriteCollectionValidator;
import com.blstream.myhoard.biz.validator.RequestValidator;
import com.blstream.myhoard.db.dao.UserDAO;
import com.blstream.myhoard.db.model.CollectionDS;
import com.blstream.myhoard.exception.MyHoardException;
import com.blstream.myhoard.exception.MyHoardRestException;
import com.blstream.myhoard.exception.NotFoundException;
import com.blstream.myhoard.exception.ResourceAlreadyExistException;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/collections")
public class CollectionController {

	private static final Logger logger = Logger
			.getLogger(CollectionController.class.getCanonicalName());

	@Autowired
	private CollectionService collectionService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private CollectionValidator collectionValidator;
	@Autowired
	private RequestValidator requestValidator;
	@Autowired
	private SecurityService securityService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private FavoriteCollectionValidator favoriteCollectionValidator;
    
    @Autowired
    private UserDAO userDao;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public CollectionDTO addCollection(@RequestBody CollectionDTO collection)
			throws MyHoardException {

		collection.setId("-1");
		collectionValidator.validate(collection, RequestMethodEnum.POST);

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
			@RequestParam(value = "sort_direction", required = false) String sortDirection,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "owner", required = false) String owner) throws MyHoardException {

		if (sortBy != null || sortDirection != null && (name == null && owner == null)) {
			if (sortBy == null || sortDirection == null) {
				throw new MyHoardRestException();
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
						throw new MyHoardRestException();
					}
					everythingOk = false;
				}

				if (!sortDirection.equals(availableDirection[0])
						&& !sortDirection.equals(availableDirection[1])) {
					throw new MyHoardRestException();
				}

				for (int i = 0; i < sortBy.size(); i++) {
					if (sortBy.get(i).equals("modified_date")) {
						sortBy.set(i, "modifiedDate");
					} else if (sortBy.get(i).equals("created_date")) {
						sortBy.set(i, "createdDate");
					}
				}

				try {
					return collectionService.getList(sortBy, sortDirection);
				} catch (MyHoardException e) {
					logger.error("getList error", e);
				}
			}
		}
		else if (name != null && owner != null && sortBy == null && sortDirection == null) {
			
			collectionValidator.validatePattern(name);
			
			if(owner.equals(securityService.getCurrentUser().getEmail())) {	
        			return collectionService.getList(name, owner);
        	}
        	else {
        		throw new NotFoundException(String.format("Access denied"));
        	}
		}
			
		try {
			return collectionService.getList();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new MyHoardRestException();	
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
		collection.setId(idStr);
		collectionValidator.validate(collection, RequestMethodEnum.PUT);

		try {
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

	@RequestMapping(value = "/{collectionId}/items", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ItemDTO> getCollectionsItems(
			@PathVariable("collectionId") String idStr,
			@RequestParam(value = "sort_by", required = false) List<String> sortBy,
			@RequestParam(value = "sort_direction", required = false) String sortDirection) {

		requestValidator.validId(idStr);

		if (sortBy != null || sortDirection != null) {
			if (sortBy == null || sortDirection == null) {
				throw new MyHoardRestException();
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
						throw new MyHoardRestException();
					}
					everythingOk = false;
				}

				if (!sortDirection.equals(availableDirection[0])
						&& !sortDirection.equals(availableDirection[1])) {
					throw new MyHoardRestException();
				}

				for (int i = 0; i < sortBy.size(); i++) {
					if (sortBy.get(i).equals("modified_date")) {
						sortBy.set(i, "modifiedDate");
					} else if (sortBy.get(i).equals("created_date")) {
						sortBy.set(i, "createdDate");
					}
				}

				return itemService.getList(Integer.parseInt(idStr), sortBy,
						sortDirection);
			}
		}

		try {
			return collectionService.get(Integer.parseInt(idStr)).getItems();
		} catch (MyHoardException e) {
			logger.error(e);
		}

		return new ArrayList<ItemDTO>();

	}
    
    
    @RequestMapping(value = "/{collectionId}/comments", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<CommentDTO> getCollectionComments(@PathVariable("collectionId") String id) throws MyHoardException {
        requestValidator.validId(id);

        return commentService.getListByCollection(id);
    }
    
    @RequestMapping(value = "/favorite", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
	public List<CollectionDTO> getFavoriteCollections() {
    	return collectionService.getFavoriteCollections();
	}
    
    @RequestMapping(value = "/favorite/{id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String addFavoriteCollection(@PathVariable("id") String idStr) {
		requestValidator.validId(idStr);
		favoriteCollectionValidator.validate(Integer.parseInt(idStr), Integer.parseInt(securityService.getCurrentUser().getId()), RequestMethodEnum.POST);
		collectionService.addToFavoriteCollections(Integer.parseInt(idStr));
		return null;
	}
    
    @RequestMapping(value = "/favorite/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String deleteFavoriteCollection(@PathVariable("id") String idStr) {
		requestValidator.validId(idStr);
		favoriteCollectionValidator.validate(Integer.parseInt(idStr), Integer.parseInt(securityService.getCurrentUser().getId()), RequestMethodEnum.DELETE);
		collectionService.deleteFromFavoriteCollections(Integer.parseInt(idStr));
		return null;
	}
}
