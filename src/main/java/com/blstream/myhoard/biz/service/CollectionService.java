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
import com.blstream.myhoard.exception.CollectionException;
import com.blstream.myhoard.exception.ErrorCodeEnum;

@Service("collectionService")
public class CollectionService implements ResourceService<CollectionDTO> {

	/**********************************************************************************
	 * Czy DAO nie powinno zwracać DTO? I wlasnie tam wszelkie czary
	 * mary(mapowania)? * Czy w DTO powinna byc jakakolwiek logika? *
	 **********************************************************************************/

	@Autowired
	private ResourceDAO<CollectionDS> collectionDAO;

	public List<CollectionDTO> getList() {

		List<CollectionDS> collectionDSs = collectionDAO.getList();
		List<CollectionDTO> collectionDTOs = CollectionMapper
				.map(collectionDSs);

		return collectionDTOs;
	}

	public CollectionDTO get(int i) {

		CollectionDS collectionDS = collectionDAO.get(i);
		if (collectionDS == null) {
			throw new CollectionException(ErrorCodeEnum.READ.getValue());
		}
		CollectionDTO collectionDTO = CollectionMapper.map(collectionDS);

		return collectionDTO;
	}

	public CollectionDTO create(CollectionDTO collection) {
		Date date = new java.util.Date();
		collection.setCreated_date(new Timestamp(date.getTime()));
		collection.setModified_date(new Timestamp(date.getTime()));
		collection.setOwner("Moj dziadek");

		CollectionDS collectionDS = CollectionMapper.map(collection);
		collectionDAO.create(collectionDS);
		CollectionDTO collectionDTO = CollectionMapper.map(collectionDS);

		return collectionDTO;
	}

	public CollectionDTO update(CollectionDTO collection) {

		CollectionDS collectionDS = CollectionMapper.map(collection);
		CollectionDS obiektZBazy = collectionDAO.get(collectionDS.getId());

		if (obiektZBazy == null) {
			throw new CollectionException(ErrorCodeEnum.UPDATE.getValue());
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

	public void remove(int i) {
		CollectionDS collectionDS = collectionDAO.get(i);
		if (collectionDS != null) {
			collectionDAO.remove(i);
		} else {
			throw new CollectionException(ErrorCodeEnum.DELETE.getValue());
		}
	}

}
