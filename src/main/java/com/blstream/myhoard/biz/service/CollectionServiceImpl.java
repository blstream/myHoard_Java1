package com.blstream.myhoard.biz.service;

import com.blstream.myhoard.authorization.service.SecurityService;
import com.blstream.myhoard.biz.mapper.CollectionMapper;
import com.blstream.myhoard.biz.mapper.ItemMapper;
import com.blstream.myhoard.biz.model.CollectionDTO;
import com.blstream.myhoard.biz.model.ItemDTO;
import com.blstream.myhoard.biz.model.UserDTO;
import com.blstream.myhoard.db.dao.CollectionDAO;
import com.blstream.myhoard.db.dao.UserDAO;
import com.blstream.myhoard.db.model.CollectionDS;
import com.blstream.myhoard.db.model.UserDS;
import com.blstream.myhoard.exception.MyHoardException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("collectionService")
public class CollectionServiceImpl implements CollectionService {

    private static final Logger logger = Logger.getLogger(CollectionServiceImpl.class.getCanonicalName());

    @Autowired
    private CollectionDAO collectionDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    SecurityService securityService;

    @Override
    public List<CollectionDTO> getList() {
        List<CollectionDS> collectionDSs = collectionDAO.getList();
        List<CollectionDTO> collectionDTOs = CollectionMapper.map(collectionDSs);

        return collectionDTOs;
    }

    @Override
    public CollectionDTO get(int i) throws MyHoardException {
        CollectionDS collectionDS = collectionDAO.get(i);
        if (collectionDS == null) {
            logger.error("CollectionDS object is null");
            throw new MyHoardException();
        }
        CollectionDTO collectionDTO = CollectionMapper.map(collectionDS);

        return collectionDTO;
    }

    @Override
    public CollectionDTO create(CollectionDTO collection) throws MyHoardException {
    	
    	logger.error(collection);
    	Date date = new java.util.Date();
    	collection.setCreatedDate(new Timestamp(date.getTime()));
    	collection.setModifiedDate(new Timestamp(date.getTime()));
    	
        if(collection.getCreatedDateClient() == null && collection.getModifiedDateClient() == null)
        {
        	collection.setCreatedDateClient(collection.getCreatedDate());
        	collection.setModifiedDateClient(collection.getModifiedDate());
        }
        CollectionDS collectionDS = CollectionMapper.map(collection);
        collectionDS.setOwner(userDAO.get(Integer.parseInt(securityService.getCurrentUser().getId())));

        logger.error(collectionDS);
        collectionDAO.create(collectionDS);
        CollectionDTO collectionDTO = CollectionMapper.map(collectionDS);

        return collectionDTO;
    }

    @Override
    public CollectionDTO update(CollectionDTO collection) throws MyHoardException {
        CollectionDS collectionDS = CollectionMapper.map(collection);
        CollectionDS srcCollectionDS = collectionDAO.get(collectionDS.getId());

        if (srcCollectionDS == null) {
            throw new MyHoardException();
        }

        if(collectionDS.getDescription() != null) {
        	srcCollectionDS.setDescription(collectionDS.getDescription());
        }
        
        srcCollectionDS.setItemsNumber(collectionDS.getItemsNumber());
        
        srcCollectionDS.setModifiedDate(new Date());
        
        if(collectionDS.getModifiedDateClient() != null) {
        	srcCollectionDS.setModifiedDateClient(collectionDS.getModifiedDateClient());
        }
        else
        	srcCollectionDS.setModifiedDateClient(srcCollectionDS.getModifiedDate());
        
		if (collectionDS.getName() != null) {
			srcCollectionDS.setName(collectionDS.getName());
		}

        if (collectionDS.getOwner() != null) {
            srcCollectionDS.setOwner(userDAO.get(Integer.parseInt(collection.getOwner().getId())));
        }
        
        if(collectionDS.getTags() != null) {
        	srcCollectionDS.setTags(collectionDS.getTags());
        }
        
        srcCollectionDS.setIsPublic(collectionDS.getIsPublic());
        
        collectionDAO.update(srcCollectionDS);

        return CollectionMapper.map(srcCollectionDS);
    }

    @Override
    public void remove(int i) throws MyHoardException {
        CollectionDS collectionDS = collectionDAO.get(i);
        if (collectionDS != null) {
            collectionDAO.remove(i);
        } else {
            logger.error("CollectionDS object is null");
            throw new MyHoardException();
        }
    }

    @Override
    public List<CollectionDTO> getList(List<String> sortBy, String sortDirection) throws MyHoardException {
        List<CollectionDS> collectionDSs = collectionDAO.getList(sortBy, sortDirection);
        List<CollectionDTO> collectionDTOs = CollectionMapper.map(collectionDSs);

        return collectionDTOs;
    }

	@Override
	public boolean isNameUnique(CollectionDTO collectionDTO) {
		return collectionDAO.isNameUniqeu(collectionDTO);
	}

	@Override
	public List<CollectionDTO> getList(String name, String owner)
			throws MyHoardException {
		
		return CollectionMapper.map(collectionDAO.getList(name, owner));
	}

	@Override
	public List<CollectionDTO> getFavoriteCollections() {
		UserDTO user = securityService.getCurrentUser();
		List<CollectionDS> collectionsDS = userDAO.getListOfUserFavoriteCollections(Integer.parseInt(user.getId()));
		List<CollectionDTO> collectionsDTO = CollectionMapper.map(collectionsDS);
		return collectionsDTO;
	}

	@Override
	public void addToFavoriteCollections(int id) {
		CollectionDS collectionDS = collectionDAO.get(id);
		userDAO.saveWithFavoriteCollection(Integer.parseInt(securityService.getCurrentUser().getId()), collectionDS);
	}

	@Override
	public void deleteFromFavoriteCollections(int id) {
		CollectionDS collectionDS = collectionDAO.get(id);
		userDAO.saveWithoutFavoriteCollection(Integer.parseInt(securityService.getCurrentUser().getId()), collectionDS);
	}
	
	

}
