package com.blstream.myhoard.biz.service;

import com.blstream.myhoard.biz.mapper.ItemMapper;
import com.blstream.myhoard.biz.model.ItemDTO;
import com.blstream.myhoard.biz.model.MediaDTO;
import com.blstream.myhoard.db.dao.CollectionDAO;
import com.blstream.myhoard.db.dao.ResourceDAO;
import com.blstream.myhoard.db.dao.UserDAO;
import com.blstream.myhoard.db.model.CollectionDS;
import com.blstream.myhoard.db.model.ItemDS;
import com.blstream.myhoard.db.model.MediaDS;
import com.blstream.myhoard.db.model.UserDS;
import com.blstream.myhoard.exception.MyHoardException;
import com.blstream.myhoard.exception.NotFoundException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("itemService")
public class ItemService extends ResourceService<ItemDTO> {

    private static final Logger logger = Logger.getLogger(ItemService.class.getCanonicalName());

    @Autowired
    private ResourceDAO<ItemDS> itemDAO;
    @Autowired
    private CollectionDAO collectionDAO;
    @Autowired
    private ResourceDAO<MediaDS> mediaDAO;
    @Autowired
    UserDAO userDao;

    @Override
    public ItemDTO create(ItemDTO itemDTO) throws MyHoardException {
        Date date = new Date();
        itemDTO.setCreatedDate(new Timestamp(date.getTime()));
        itemDTO.setModifiedDate(new Timestamp(date.getTime()));
        CollectionDS collectionDS = getItemCollection(itemDTO);
        Set<MediaDS> mediaDSSet = getItemMedia(itemDTO);
        UserDS userDS = userDao.get(Integer.parseInt(itemDTO.getOwner().getId()));

        ItemDS itemDS = ItemMapper.map(itemDTO, collectionDS, mediaDSSet);
        itemDS.setOwner(userDS);
        itemDAO.create(itemDS);

        itemDTO = ItemMapper.map(itemDS);
        return itemDTO;
    }

    @Override
    public List<ItemDTO> getList() {
        List<ItemDS> itemDSList = itemDAO.getList();
        List<ItemDTO> itemList = ItemMapper.map(itemDSList);
        return itemList;
    }

    @Override
    public ItemDTO get(int id) {
        ItemDS itemDS = itemDAO.get(id);
        if (itemDS == null) {
            throw new NotFoundException(String.format("Item with id = %s not exist", id));
        }
        ItemDTO itemDTO = ItemMapper.map(itemDS);
        return itemDTO;
    }

    @Override
    public ItemDTO update(ItemDTO itemDTO) throws MyHoardException {
        CollectionDS collectionDS = getItemCollection(itemDTO);
        Set<MediaDS> mediaDSSet = getItemMedia(itemDTO);
        ItemDS updateItemDS = ItemMapper.map(itemDTO, collectionDS, mediaDSSet);

        ItemDS itemDS = itemDAO.get(Integer.parseInt(itemDTO.getId()));
        itemDS.setModifiedDate(new Timestamp(new Date().getTime()));
        itemDS.setName(updateItemDS.getName());
        itemDS.setDescription(updateItemDS.getDescription());
        itemDS.setLat(updateItemDS.getLat());
        itemDS.setLng(updateItemDS.getLng());
        itemDS.setQuantity(updateItemDS.getQuantity());
        itemDS.setCollection(updateItemDS.getCollection());
        itemDS.setMedia(updateItemDS.getMedia());
        itemDAO.update(itemDS);

        itemDTO = ItemMapper.map(itemDS);
        return itemDTO;
    }

    @Override
    public void remove(int id) {
        itemDAO.remove(id);
    }

    private CollectionDS getItemCollection(ItemDTO itemDTO) {
        CollectionDS collectionDS = null;
        try {
            int collectionId = Integer.parseInt(itemDTO.getCollection());
            collectionDS = collectionDAO.get(collectionId);
        } catch (NumberFormatException e) {
            logger.error("getItemCollection - Invalid Collection Id", e);
        }
        if (collectionDS == null) {
            throw new NotFoundException(String.format("Collection with id = %s not exist", itemDTO.getCollection()));
        }
        return collectionDS;
    }

    private Set<MediaDS> getItemMedia(ItemDTO itemDTO) {
        Set<MediaDS> mediaDSSet = new HashSet<MediaDS>();
        for (MediaDTO mediaDTO : itemDTO.getMedia()) {
            MediaDS mediaDS = null;
            try {
                mediaDS = mediaDAO.get(Integer.parseInt(mediaDTO.getId()));
            } catch (NumberFormatException e) {
                logger.error("getItemMedia - Invalid Media Id", e);
            }
            if (mediaDS == null) {
                throw new NotFoundException(String.format("Media with id = %s not exist", mediaDTO.getId()));
            }
            mediaDSSet.add(mediaDS);
        }
        return mediaDSSet;
    }

}
