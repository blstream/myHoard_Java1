package com.blstream.myhoard.biz.service;

import com.blstream.myhoard.authorization.service.SecurityService;
import com.blstream.myhoard.biz.mapper.ItemMapper;
import com.blstream.myhoard.biz.model.ItemDTO;
import com.blstream.myhoard.biz.model.MediaDTO;
import com.blstream.myhoard.biz.model.UserDTO;
import com.blstream.myhoard.db.dao.CollectionDAO;
import com.blstream.myhoard.db.dao.ItemDAO;
import com.blstream.myhoard.db.dao.MediaDAO;
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
public class ItemServiceImpl implements ItemService {

    private static final Logger logger = Logger.getLogger(ItemServiceImpl.class.getCanonicalName());

    @Autowired
    private ItemDAO itemDAO;
    @Autowired
    private CollectionDAO collectionDAO;
    @Autowired
    private MediaDAO mediaDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private SecurityService securityService;

    @Override
    public ItemDTO create(ItemDTO itemDTO) throws MyHoardException {
        Date date = new Date();
        itemDTO.setCreatedDate(new Timestamp(date.getTime()));
        itemDTO.setModifiedDate(new Timestamp(date.getTime()));
        
        if (itemDTO.getCreatedDateClient() == null && itemDTO.getModifiedDateClient() == null) {
            itemDTO.setCreatedDateClient(itemDTO.getCreatedDate());
            itemDTO.setModifiedDateClient(itemDTO.getModifiedDate());
        }
        
        CollectionDS collectionDS = getItemCollection(itemDTO);
        Set<MediaDS> mediaDSSet = getItemMedia(itemDTO);
        UserDS userDS = userDAO.get(Integer.parseInt(securityService.getCurrentUser().getId()));

        ItemDS itemDS = ItemMapper.map(itemDTO, collectionDS, mediaDSSet);
        itemDS.setOwner(userDS);
        
        itemDAO.create(itemDS);

        itemDTO = ItemMapper.map(itemDS);
        return itemDTO;
    }

    @Override
    public List<ItemDTO> getListByUser() {
        List<ItemDS> itemDSList = itemDAO.getListByUser(Integer.parseInt(securityService.getCurrentUser().getId()));

        return ItemMapper.map(itemDSList);
    }

    @Override
    public List<ItemDTO> getListByUser(UserDTO userDTO) throws MyHoardException {
        List<ItemDS> itemDSList = itemDAO.getListByUser(Integer.parseInt(userDTO.getId()));

        return ItemMapper.map(itemDSList);
    }

    @Override
    public List<ItemDTO> getList() {
        List<ItemDS> itemDSList = itemDAO.getList();

        return ItemMapper.map(itemDSList);
    }

    @Override
    public List<ItemDTO> getList(String name, int collection, String owner) {

        return ItemMapper.map(itemDAO.getList(name, collection, owner));
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
        Set<MediaDS> mediaDSSet = null;
        CollectionDS collectionDS = null;
        if (itemDTO.getCollection() != null) {
            collectionDS = getItemCollection(itemDTO);
        }
        if (itemDTO.getMedia() != null) {
            mediaDSSet = getItemMedia(itemDTO);
        }
        ItemDS updateItemDS = ItemMapper.map(itemDTO, collectionDS, mediaDSSet);

        ItemDS itemDS = itemDAO.get(Integer.parseInt(itemDTO.getId()));
        
        itemDS.setModifiedDate(new Date());
        
        if(updateItemDS.getModifiedDateClient() != null) {
        	itemDS.setModifiedDateClient(updateItemDS.getModifiedDateClient());
        } else {
        	itemDS.setModifiedDateClient(itemDS.getModifiedDate());
        }
        
        if (updateItemDS.getName() != null) {
            itemDS.setName(updateItemDS.getName());
        }
        if (updateItemDS.getDescription() != null) {
            itemDS.setDescription(updateItemDS.getDescription());
        }
        if (updateItemDS.getLat() != null) {
            itemDS.setLat(updateItemDS.getLat());
        }
        if (updateItemDS.getLng() != null) {
            itemDS.setLng(updateItemDS.getLng());
        }
        if (itemDTO.getQuantity() != null) {
            itemDS.setQuantity(updateItemDS.getQuantity());
        }
        if (updateItemDS.getCollection() != null) {
            itemDS.setCollection(updateItemDS.getCollection());
        }
        if (updateItemDS.getMedia() != null) {
            itemDS.setMedia(updateItemDS.getMedia());
        }
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
        if (itemDTO.getMedia() != null) {
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
        }
        return mediaDSSet;
    }

	@Override
	public List<ItemDTO> getList(int id, List<String> sortBy, String sortDirection) {
		
		List<ItemDS> itemDSList = itemDAO.getList(id, sortBy, sortDirection);
		List<ItemDTO> itemDTOList = ItemMapper.map(itemDSList);

		return itemDTOList;
	}

}
