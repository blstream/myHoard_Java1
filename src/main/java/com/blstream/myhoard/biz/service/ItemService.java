package com.blstream.myhoard.biz.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blstream.myhoard.biz.mapper.ItemMapper;
import com.blstream.myhoard.biz.model.ItemDTO;
import com.blstream.myhoard.db.dao.ResourceDAO;
import com.blstream.myhoard.db.model.CollectionDS;
import com.blstream.myhoard.db.model.ItemDS;

@Service("itemService")
public class ItemService extends ResourceService<ItemDTO> {

	@Autowired
	private ResourceDAO<ItemDS> itemDAO;
	@Autowired
	private ResourceDAO<CollectionDS> collectionDAO;

	@Override
	public ItemDTO create(ItemDTO itemDTO) {
		CollectionDS collectionDS = null;
		Date date = new Date();
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

	@Override
	public List<ItemDTO> getList() {

		return ItemMapper.map(itemDAO.getList());
	}

	@Override
	public ItemDTO get(int id) {

		return ItemMapper.map(itemDAO.get(id));
	}

	@Override
	public ItemDTO update(ItemDTO itemDTO) {
		CollectionDS collectionDS = null;
		try {
			int collectionId = Integer.parseInt(itemDTO.getCollection());
			collectionDS = collectionDAO.get(collectionId);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.toString());
		}

		ItemDS updateItemDS = ItemMapper.map(itemDTO, collectionDS);
		ItemDS itemDS = itemDAO.get(Integer.parseInt(itemDTO.getId()));
		itemDS.setModifiedDate(new Timestamp(new Date().getTime()));
		itemDS.setName(updateItemDS.getName());
		itemDS.setDescription(updateItemDS.getDescription());
		itemDS.setLat(updateItemDS.getLat());
		itemDS.setLng(updateItemDS.getLng());
		itemDS.setQuantity(updateItemDS.getQuantity());
		itemDS.setCollection(updateItemDS.getCollection());
		itemDAO.update(itemDS);

		return ItemMapper.map(itemDS);
	}

	@Override
	public void remove(int id) {
		itemDAO.remove(id);
	}

}
