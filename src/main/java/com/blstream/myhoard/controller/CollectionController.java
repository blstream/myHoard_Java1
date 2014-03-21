package com.blstream.myhoard.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.blstream.myhoard.exception.ErrorCodeEnum;
import com.blstream.myhoard.exception.MyHoardException;
import com.blstream.myhoard.exception.MyHoardRestException;

@Controller
@RequestMapping("/collections")
public class CollectionController {

	private static final Logger logger = Logger
			.getLogger(CollectionController.class.getCanonicalName());

	@Autowired
	CollectionService<CollectionDTO> collectionService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public CollectionDTO addCollection(
			@Valid @RequestBody CollectionDTO collection) {
		try {
			return collectionService.create(collection);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new MyHoardRestException(ErrorCodeEnum.CREATE.getValue());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public CollectionDTO getCollection(@PathVariable("id") String idStr) {
		try {
			int id = Integer.parseInt(idStr);
			return collectionService.get(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new MyHoardRestException(ErrorCodeEnum.READ.getValue());
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
					return collectionService.getList(sortBy, sortDirection);
				} catch (MyHoardException e) {
					e.printStackTrace();
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

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public void deleteCollection(@PathVariable("id") String idStr) {
		try {
			int id = Integer.parseInt(idStr);
			collectionService.remove(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new MyHoardRestException(ErrorCodeEnum.DELETE.getValue());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public CollectionDTO updateCollection(@PathVariable("id") String idStr,
			@Valid @RequestBody CollectionDTO collection) {
		try {
			Integer.parseInt(idStr);
			collection.setId(idStr);
			return collectionService.update(collection);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new MyHoardRestException(ErrorCodeEnum.UPDATE.getValue());
		}
	}
}
