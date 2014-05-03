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

import com.blstream.myhoard.authorization.service.SecurityService;
import com.blstream.myhoard.biz.enums.RequestMethodEnum;
import com.blstream.myhoard.biz.model.ItemDTO;
import com.blstream.myhoard.biz.service.ItemService;
import com.blstream.myhoard.biz.validator.ItemValidator;
import com.blstream.myhoard.biz.validator.RequestValidator;
import com.blstream.myhoard.exception.ForbiddenException;
import com.blstream.myhoard.exception.MyHoardException;
import com.blstream.myhoard.exception.NotFoundException;

@Controller
@RequestMapping("/items")
public class ItemController {

    private static final String ITEM_NOT_EXIST = "Item with id = %s not exist";
    private static final String INVALID_PARAMETERS = "Invalid parameters to searching";
    private static final String INVALID_COLLECTION_ID = "Invalid parameter: collection = %s";
    private static final String ACCESS_DENIED = "Access denied";
    private static final String GET_ITEM = "getItem";
    private static final String UPDATE_ITEM = "updateItem";
    private static final String DELETE_ITEM = "deleteItem";

    private static final Logger logger = Logger.getLogger(ItemController.class.getCanonicalName());

    @Autowired
    private ItemService itemService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private ItemValidator itemValidator;
	@Autowired
	private RequestValidator requestValidator;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<ItemDTO> getItems(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "collection", required = false) String collectionId,
            @RequestParam(value = "owner", required = false) String owner) throws MyHoardException {

        if (name == null && collectionId == null && owner == null) {
            return itemService.getListByUser();
        } else if (name != null && collectionId != null && owner != null) {
        	if(owner.equals(securityService.getCurrentUser().getEmail())) {
        		try {
        			itemValidator.validatePattern(name);
        			int collection = Integer.parseInt(collectionId);
        			return itemService.getList(name, collection, owner);
        		} catch (NumberFormatException e) {
        			logger.error(e.getMessage(), e);
        			throw new NotFoundException(String.format(INVALID_COLLECTION_ID, collectionId));
        		} catch (MyHoardException e) {
        			logger.error(e.getMessage(), e);
        			throw new NotFoundException(String.format(ITEM_NOT_EXIST, collectionId));
        		}
        	}
        	else {
	    		logger.error("owner: " + owner.toString() + " userDTO.getEmail(): " + securityService.getCurrentUser().getEmail());
	    		throw new NotFoundException(String.format(ACCESS_DENIED, collectionId));        		
        	}
        } else {
        	throw new NotFoundException(INVALID_PARAMETERS);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ItemDTO addItem(@RequestBody ItemDTO itemDTO) throws MyHoardException {
        requestValidator.validId(itemDTO.getCollection());
        itemValidator.validate(itemDTO, RequestMethodEnum.POST);
        
        return itemService.create(itemDTO);
    }

    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ItemDTO getItem(@PathVariable("itemId") String id) {
        requestValidator.validId(id);
        ItemDTO itemDTO;
        try {
            itemDTO = itemService.get(Integer.parseInt(id));
        } catch (MyHoardException mhe) {
            logger.error(GET_ITEM, mhe);
            throw new NotFoundException(String.format(ITEM_NOT_EXIST, id));
        }

        if (!securityService.getCurrentUser().getId().equals(itemDTO.getOwner().getId())) {
            throw new ForbiddenException();
        }

        return itemDTO;
    }

    @RequestMapping(value = "/{itemId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ItemDTO updateItem(@PathVariable("itemId") String id, @Valid @RequestBody ItemDTO itemDTO) throws MyHoardException {
        requestValidator.validId(itemDTO.getId());
        itemValidator.validate(itemDTO, RequestMethodEnum.PUT);
        ItemDTO srcItemDTO;
        try {
            srcItemDTO = itemService.get(Integer.parseInt(id));
            itemDTO.setId(id);
        } catch (MyHoardException mhe) {
            logger.info(UPDATE_ITEM, mhe);
            throw new NotFoundException(String.format(ITEM_NOT_EXIST, id));
        }

        if (!securityService.getCurrentUser().getId().equals(srcItemDTO.getOwner().getId())) {
            throw new ForbiddenException();
        }

        return itemService.update(itemDTO);
    }

    @RequestMapping(value = "/{itemId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void deleteItem(@PathVariable("itemId") String id) throws MyHoardException {
        requestValidator.validId(id);
        ItemDTO itemDTO;
        try {
            itemDTO = itemService.get(Integer.parseInt(id));
        } catch (MyHoardException mhe) {
            logger.error(DELETE_ITEM, mhe);
            throw new NotFoundException(String.format(ITEM_NOT_EXIST, id));
        }

        if (!securityService.getCurrentUser().getId().equals(itemDTO.getOwner().getId())) {
            throw new ForbiddenException();
        }

        itemService.remove(Integer.parseInt(id));
    }
}
