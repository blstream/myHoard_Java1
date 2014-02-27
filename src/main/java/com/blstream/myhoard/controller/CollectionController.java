package com.blstream.myhoard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.blstream.myhoard.biz.model.CollectionDTO;
import com.blstream.myhoard.biz.service.ResourceService;
import com.blstream.myhoard.exception.CollectionException;
import com.blstream.myhoard.exception.CollectionRestException;
import com.blstream.myhoard.exception.ErrorCodeEnum;

@Controller
@RequestMapping("/collections")
public class CollectionController {

	@Autowired
	ResourceService<CollectionDTO> collectionService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public CollectionDTO addCollection(@RequestBody CollectionDTO collection) {

		try {
			return collectionService.create(collection);
		} catch (Exception e) {
			throw new CollectionRestException(ErrorCodeEnum.CREATE.getValue());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public CollectionDTO getCollection(@PathVariable("id") String idStr) {

		int id = 0;

		try {
			id = Integer.parseInt(idStr);
			return collectionService.get(id);
		} catch (Exception e) {
			throw new CollectionRestException(ErrorCodeEnum.READ.getValue());
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<CollectionDTO> getCollections() {
		try {
			return collectionService.getList();
		} catch (CollectionException e) {
			throw new CollectionRestException(ErrorCodeEnum.READ.getValue());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public void deleteCollection(@PathVariable("id") String idStr) {
		int id = 0;
		try {
			id = Integer.parseInt(idStr);
			collectionService.remove(id);
		} catch (Exception ex) {
			throw new CollectionRestException(ErrorCodeEnum.DELETE.getValue());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public CollectionDTO updateCollection(@PathVariable("id") String idStr,
			@RequestBody CollectionDTO collection) {

		try {
			Integer.parseInt(idStr);
			collection.setId(idStr);
			return collectionService.update(collection);
		} catch (Exception ex) {
			throw new CollectionRestException(ErrorCodeEnum.UPDATE.getValue());
		}

	}

}
