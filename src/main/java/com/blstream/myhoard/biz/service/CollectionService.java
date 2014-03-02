package com.blstream.myhoard.biz.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blstream.myhoard.biz.mapper.CollectionMapper;
import com.blstream.myhoard.biz.model.CollectionDTO;
import com.blstream.myhoard.db.dao.ResourceDAO;
import com.blstream.myhoard.db.model.CollectionDS;
import com.blstream.myhoard.exception.MyHoardException;

@Service("collectionService")
public class CollectionService extends ResourceService<CollectionDTO> {

	@Autowired
	private ResourceDAO<CollectionDS> collectionDAO;

	@Override
	public List<CollectionDTO> getList() {

		List<CollectionDS> collectionDSs = collectionDAO.getList();
		List<CollectionDTO> collectionDTOs = CollectionMapper
				.map(collectionDSs);

		return collectionDTOs;
	}
	
	@Override
	public CollectionDTO get(int i) throws MyHoardException {

		CollectionDS collectionDS = collectionDAO.get(i);
		if (collectionDS == null) {
			throw new MyHoardException();
		}
		CollectionDTO collectionDTO = CollectionMapper.map(collectionDS);

		return collectionDTO;
	}

	@Override
	public CollectionDTO create(CollectionDTO collection) {
		Date date = new java.util.Date();
		collection.setCreatedDate(new Timestamp(date.getTime()));
		collection.setModifiedDate(new Timestamp(date.getTime()));
		collection.setOwner("Moj dziadek");

		CollectionDS collectionDS = CollectionMapper.map(collection);
		collectionDAO.create(collectionDS);
		CollectionDTO collectionDTO = CollectionMapper.map(collectionDS);

		return collectionDTO;
	}

	@Override
	public CollectionDTO update(CollectionDTO collection)
			throws MyHoardException {

		CollectionDS collectionDS = CollectionMapper.map(collection);
		CollectionDS obiektZBazy = collectionDAO.get(collectionDS.getId());

		if (obiektZBazy == null) {
			throw new MyHoardException();
		}

		obiektZBazy.setDescription(collectionDS.getDescription());
		obiektZBazy.setItemsNumber(collectionDS.getItemsNumber());
		obiektZBazy.setModifiedDate(new Date());
		obiektZBazy.setName(collectionDS.getName());

		if (collectionDS.getOwner() != null) {
			// czy mozemy zmienic?
			obiektZBazy.setOwner(collectionDS.getOwner());
		}
		obiektZBazy.setTags(collectionDS.getTags());
		System.out.println(obiektZBazy.toString());
		collectionDAO.update(obiektZBazy);

		return CollectionMapper.map(obiektZBazy);
	}

	@Override
	public void remove(int i) throws MyHoardException {
		CollectionDS collectionDS = collectionDAO.get(i);
		if (collectionDS != null) {
			collectionDAO.remove(i);
		} else {
			throw new MyHoardException();
		}
	}

}
