package com.blstream.myhoard.controller;

import com.blstream.myhoard.biz.model.CollectionDTO;
import com.blstream.myhoard.biz.service.IResourceService;
import com.blstream.myhoard.exception.MyHoardRestException;
import com.blstream.myhoard.exception.ErrorCodeEnum;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/collections")
public class CollectionController {

        private static final Logger logger = Logger.getLogger(CollectionController.class.getCanonicalName());

        @Autowired
        IResourceService<CollectionDTO> collectionService;

        @RequestMapping(method = RequestMethod.POST)
        @ResponseBody
        @ResponseStatus(HttpStatus.CREATED)
        public CollectionDTO addCollection(@Valid @RequestBody CollectionDTO collection) {
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
        public List<CollectionDTO> getCollections() {
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
        public CollectionDTO updateCollection(@PathVariable("id") String idStr, @Valid @RequestBody CollectionDTO collection) {
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
