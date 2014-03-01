package com.blstream.myhoard.biz.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blstream.myhoard.biz.mapper.ItemMapper;
import com.blstream.myhoard.biz.model.ItemDTO;
import com.blstream.myhoard.db.dao.ResourceDAO;
import com.blstream.myhoard.db.model.CollectionDS;
import com.blstream.myhoard.db.model.ItemDS;

// TODO RT create abstract Service
// TODO RT implements ResourceService
@Service("itemService")
public class ItemService {

	private final static Logger logger = Logger.getLogger(ItemMapper.class
			.getName());

	@Autowired
	private ResourceDAO<ItemDS> itemDAO;

	@Autowired
	private ResourceDAO<CollectionDS> collectionDAO;

	public ItemDTO create(ItemDTO itemDTO) {
		CollectionDS collectionDS = null;
		Date date = new java.util.Date();
		itemDTO.setCreatedDate(new Timestamp(date.getTime()));
		itemDTO.setModifiedDate(new Timestamp(date.getTime()));

		try {
			int collectionId = Integer.parseInt(itemDTO.getCollection());
			collectionDS = collectionDAO.get(collectionId);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.toString());
		}

		ItemDS itemDS = ItemMapper.map(itemDTO, collectionDS);
		itemDAO.create(itemDS);
		itemDTO = ItemMapper.map(itemDS);

		return itemDTO;
	}

}
