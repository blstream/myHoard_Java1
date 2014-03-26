package com.blstream.myhoard.controller;

import com.blstream.myhoard.biz.model.ItemDTO;
import com.blstream.myhoard.biz.model.UserDTO;
import com.blstream.myhoard.biz.service.ItemService;
import static com.blstream.myhoard.constants.Constants.USER;
import com.blstream.myhoard.exception.ForbiddenException;
import com.blstream.myhoard.exception.MyHoardException;
import com.blstream.myhoard.exception.NotFoundException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/items")
public class ItemController {

    private static final String ITEM_NOT_EXIST = "Item with id = %s not exist";
    private static final String ITEM_NOT_EXIST_INVALID_ID = "Item with id = %s not exist; Invalid Id";
    private static final String INVALID_PARAMETERS = "Invalid parameters to searching";
    private static final String INVALID_COLLECTION_ID = "Invalid parameter: collection = %s";
    private static final String ACCESS_DENIED = "Access denied";
    private static final String GET_ITEM = "getItem";
    private static final String UPDATE_ITEM = "updateItem";
    private static final String DELETE_ITEM = "deleteItem";

    private static final Logger logger = Logger.getLogger(ItemController.class.getCanonicalName());

    @Autowired
    private ItemService itemService;

    @ModelAttribute(USER)
    public UserDTO getUser(HttpServletRequest request) {
        return (UserDTO) request.getAttribute(USER);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<ItemDTO> getItems(
    		@ModelAttribute(USER) UserDTO userDTO,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "collection", required = false) String collectionId,
            @RequestParam(value = "owner", required = false) String owner) throws MyHoardException {
    	
    	
    	if(name == null && collectionId == null && owner == null) {
    		return itemService.getListByUser(userDTO);
    	}
    	else if(name != null && collectionId != null && owner != null) {
    			try {
    	        	int collection = Integer.parseInt(collectionId);
    	            return itemService.getList(name,collection, owner);    		
    	    	}
    	    	catch(Exception e) {
    	    		logger.error(e.getMessage(), e);
    	    		throw new NotFoundException(String.format(INVALID_COLLECTION_ID, collectionId));
    	    	}
    	}
    	else {
    		throw new NotFoundException(INVALID_PARAMETERS);
    	}
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ItemDTO addItem(@ModelAttribute(USER) UserDTO userDTO, @Valid @RequestBody ItemDTO item) throws MyHoardException {
        item.setOwner(userDTO);

        return itemService.create(item);
    }

    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ItemDTO getItem(@ModelAttribute(USER) UserDTO userDTO, @PathVariable("itemId") String id) {
        ItemDTO itemDTO;
        try {
            itemDTO = itemService.get(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            logger.error(GET_ITEM, e);
            throw new NotFoundException(String.format(ITEM_NOT_EXIST_INVALID_ID, id));
        } catch (MyHoardException mhe) {
            logger.error(GET_ITEM, mhe);
            throw new NotFoundException(String.format(ITEM_NOT_EXIST, id));
        }

        if (!userDTO.getId().equals(itemDTO.getOwner().getId())) {
            throw new ForbiddenException();
        }

        return itemDTO;
    }

    @RequestMapping(value = "/{itemId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ItemDTO updateItem(@ModelAttribute(USER) UserDTO userDTO, @PathVariable("itemId") String id,
            @Valid @RequestBody ItemDTO itemDTO) throws MyHoardException {
        ItemDTO srcItemDTO;
        try {
            srcItemDTO = itemService.get(Integer.parseInt(id));
            itemDTO.setId(id);
        } catch (NumberFormatException e) {
            logger.error(UPDATE_ITEM, e);
            throw new NotFoundException(String.format(ITEM_NOT_EXIST_INVALID_ID, id));
        } catch (MyHoardException mhe) {
            logger.info(UPDATE_ITEM, mhe);
            throw new NotFoundException(String.format(ITEM_NOT_EXIST, id));
        }

        if (!userDTO.getId().equals(srcItemDTO.getOwner().getId())) {
            throw new ForbiddenException();
        }

        return itemService.update(itemDTO);
    }

    @RequestMapping(value = "/{itemId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void deleteItem(@ModelAttribute(USER) UserDTO userDTO, @PathVariable("itemId") String id) throws MyHoardException {
        ItemDTO itemDTO;
        try {
            itemDTO = itemService.get(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            logger.error(DELETE_ITEM, e);
            throw new NotFoundException(String.format(ITEM_NOT_EXIST_INVALID_ID, id));
        } catch (MyHoardException mhe) {
            logger.error(DELETE_ITEM, mhe);
            throw new NotFoundException(String.format(ITEM_NOT_EXIST, id));
        }

        if (!userDTO.getId().equals(itemDTO.getOwner().getId())) {
            throw new ForbiddenException();
        }

        itemService.remove(Integer.parseInt(id));
    }
}
