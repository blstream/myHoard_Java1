package com.myhoard.controllers;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.myhoard.model.dao.CollectionDS;
import com.myhoard.service.ResourceService;

@Controller
@RequestMapping("/collections")
public class CollectionController {

	@Autowired
	ResourceService<CollectionDS> collectionService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public CollectionDS addCollection(@RequestBody CollectionDS collection) {
		collectionService.create(collection);
		return collection;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public CollectionDS getCollection(@PathVariable("id") int id) {
		StringUtils.isNumeric("");
		return collectionService.get(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<CollectionDS> getCollections() {
		return collectionService.getList();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public void deleteCollection(@PathVariable("id") int id) {
		collectionService.remove(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public CollectionDS updateCollection(@PathVariable int id,
			@RequestBody CollectionDS collection) {
		collectionService.update(collection);
		return collection;
	}

}
