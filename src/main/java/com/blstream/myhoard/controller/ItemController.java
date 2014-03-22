package com.blstream.myhoard.controller;

import com.blstream.myhoard.biz.model.ItemDTO;
import com.blstream.myhoard.biz.model.UserDTO;
import com.blstream.myhoard.biz.service.IResourceService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/items")
public class ItemController {

    private static final String ITEM_NOT_EXIST = "Item with id = %s not exist";
    private static final String ITEM_NOT_EXIST_INVALID_ID = "Item with id = %s not exist; Invalid Id";
    private static final String GET_ITEM = "getItem";
    private static final String UPDATE_ITEM = "updateItem";
    private static final String DELETE_ITEM = "deleteItem";

    private static final Logger logger = Logger.getLogger(ItemController.class.getCanonicalName());

    @Autowired
    IResourceService<ItemDTO> itemService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<ItemDTO> getItems() throws MyHoardException {

        return itemService.getList();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ItemDTO addItem(HttpServletRequest request, @Valid @RequestBody ItemDTO item) throws MyHoardException {
        UserDTO userDTO = (UserDTO) request.getAttribute(USER);
        item.setOwner(userDTO);

        return itemService.create(item);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ItemDTO getItem(@PathVariable("id") String id) {
        try {
            ItemDTO itemDTO = itemService.get(Integer.parseInt(id));
            return itemDTO;
        } catch (NumberFormatException e) {
            logger.error(GET_ITEM, e);
            throw new NotFoundException(String.format(ITEM_NOT_EXIST_INVALID_ID, id));
        } catch (MyHoardException mhe) {
            logger.error(GET_ITEM, mhe);
            throw new NotFoundException(String.format(ITEM_NOT_EXIST, id));
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ItemDTO updateItem(@PathVariable("id") String id, @Valid @RequestBody ItemDTO item) throws MyHoardException {
        try {
            itemService.get(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            logger.error(UPDATE_ITEM, e);
            throw new NotFoundException(String.format(ITEM_NOT_EXIST_INVALID_ID, id));
        } catch (MyHoardException mhe) {
            logger.info(UPDATE_ITEM, mhe);
            throw new NotFoundException(String.format(ITEM_NOT_EXIST, id));
        }
        item.setId(id);
        ItemDTO itemDTO = itemService.update(item);

        return itemDTO;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void deleteItem(HttpServletRequest request, @PathVariable("id") String id) throws MyHoardException {
        UserDTO userDTO = (UserDTO) request.getAttribute(USER);
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
