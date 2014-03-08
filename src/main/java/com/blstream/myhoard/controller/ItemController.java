package com.blstream.myhoard.controller;

import com.blstream.myhoard.biz.model.ItemDTO;
import com.blstream.myhoard.biz.service.ItemService;
import com.blstream.myhoard.exception.NotFoundException;
import java.util.List;
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

//TODO RT Validation
@Controller
@RequestMapping("/items")
public class ItemController {

        private static final Logger logger = Logger.getLogger(ItemController.class.getCanonicalName());

        @Autowired
        ItemService itemService;

        @RequestMapping(method = RequestMethod.GET)
        @ResponseStatus(HttpStatus.OK)
        @ResponseBody
        public List<ItemDTO> getItems() {
                logger.info("Getting all items");

                return itemService.getList();
        }

        @RequestMapping(method = RequestMethod.POST)
        @ResponseStatus(HttpStatus.CREATED)
        @ResponseBody
        public ItemDTO addItem(@RequestBody ItemDTO item) {

                return itemService.create(item);
        }

        @RequestMapping(value = "/{id}", method = RequestMethod.GET)
        @ResponseStatus(HttpStatus.OK)
        @ResponseBody
        public ItemDTO getItem(@PathVariable("id") String id) {
                try {
                        ItemDTO itemDTO = itemService.get(Integer.parseInt(id));
                        return itemDTO;
                } catch (Exception e) {
                        logger.info("Item not found", e);
                        throw new NotFoundException();
                }
        }

        @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
        @ResponseStatus(HttpStatus.OK)
        @ResponseBody
        public ItemDTO updateItem(@PathVariable("id") String id, @RequestBody ItemDTO item) {
                try {
                        itemService.get(Integer.parseInt(id));
                } catch (Exception e) {
                        logger.info("Item not found", e);
                        throw new NotFoundException();
                }
                item.setId(id);
                ItemDTO itemDTO = itemService.update(item);
                return itemDTO;
        }

        @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
        @ResponseStatus(HttpStatus.NO_CONTENT)
        @ResponseBody
        public void deleteItem(@PathVariable("id") String id) {
                try {
                        itemService.get(Integer.parseInt(id));
                } catch (Exception e) {
                        logger.info("Item not found", e);
                        throw new NotFoundException();
                }
                itemService.remove(Integer.parseInt(id));
        }

}
