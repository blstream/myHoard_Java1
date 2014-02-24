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
import com.blstream.myhoard.exception.ErrorCodeEnum;

@Controller
@RequestMapping("/collections")
public class CollectionController {

	@Autowired
	ResourceService<CollectionDTO> collectionService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public CollectionDTO addCollection(@RequestBody CollectionDTO collection) {

		return collectionService.create(collection);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public CollectionDTO getCollection(@PathVariable("id") String idStr) {

		// na razie jest jak jest
		int id = 0;
		try {
			id = Integer.parseInt(idStr);
		} catch (Exception ex) {
			throw new CollectionException(ErrorCodeEnum.READ.getValue());
		}

		return collectionService.get(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<CollectionDTO> getCollections() {
		return collectionService.getList();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public void deleteCollection(@PathVariable("id") String idStr) {
		int id = 0;
		try {
			id = Integer.parseInt(idStr);
		} catch (Exception ex) {
			throw new CollectionException(ErrorCodeEnum.DELETE.getValue());
		}
		collectionService.remove(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public CollectionDTO updateCollection(@PathVariable("id") String idStr,
			@RequestBody CollectionDTO collection) {

		try {
			Integer.parseInt(idStr);
		} catch (Exception ex) {
			throw new CollectionException(ErrorCodeEnum.UPDATE.getValue());
		}
		collection.setId(idStr);
		return collectionService.update(collection);
	}

}
