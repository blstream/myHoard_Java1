package com.blstream.myhoard.biz.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blstream.myhoard.biz.mapper.CollectionMapper;
import com.blstream.myhoard.biz.model.CollectionDTO;
import com.blstream.myhoard.db.dao.CollectionDAO;
import com.blstream.myhoard.db.model.CollectionDS;
import com.blstream.myhoard.exception.CollectionException;
import com.blstream.myhoard.exception.ErrorCodeEnum;

@Service("collectionService")
public class CollectionService implements ResourceService<CollectionDTO> {

	/**********************************************************************************
	 * Czy DAO nie powinno zwracać DTO? I wlasnie tam wszelkie czary
	 * mary(mapowania)? * Czy w DTO powinna byc jakakolwiek logika? *
	 **********************************************************************************/

	@Autowired
	private CollectionDAO collectionDAO;

	public List<CollectionDTO> getList() {

		List<CollectionDS> collectionDSs = collectionDAO.getList();
		List<CollectionDTO> collectionDTOs = CollectionMapper
				.map(collectionDSs);

		return collectionDTOs;
	}

	public CollectionDTO get(int i) {

		CollectionDS collectionDS = collectionDAO.get(i);
		if(collectionDS==null) {
			throw new CollectionException(ErrorCodeEnum.READ.getValue());
		}
		CollectionDTO collectionDTO = CollectionMapper.map(collectionDS);

		return collectionDTO;
	}

	public CollectionDTO create(CollectionDTO collection) {
		Date date = new java.util.Date();
		collection.setCreatedDate(new Timestamp(date.getTime()));
		collection.setModifiedDate(new Timestamp(date.getTime()));
		collection.setOwner("Moja babcia");

		CollectionDS collectionDS = CollectionMapper.map(collection);
		collectionDAO.create(collectionDS);
		CollectionDTO collectionDTO = CollectionMapper.map(collectionDS);

		return collectionDTO;
	}

	public void update(CollectionDTO collection) {
		// update nie dziala, trzeba poprawic dao
		CollectionDS collectionDS = CollectionMapper.map(collection);

		collectionDAO.update(collectionDS);
	}

	public void remove(int i) {
		collectionDAO.remove(i);
	}

}
