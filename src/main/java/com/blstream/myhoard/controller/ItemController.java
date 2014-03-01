package com.blstream.myhoard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.blstream.myhoard.biz.model.ItemDTO;
import com.blstream.myhoard.biz.service.ItemService;

// TODO RT Item CRUD
@Controller
@RequestMapping("/items")
public class ItemController {

	@Autowired
	ItemService itemService;

	// TODO RT Validation, Exeptions
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public ItemDTO addItem(@RequestBody ItemDTO item) {
		return itemService.create(item);
	}

}
