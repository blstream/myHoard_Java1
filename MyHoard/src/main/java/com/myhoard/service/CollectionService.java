package com.myhoard.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhoard.model.dao.CollectionDAO;
import com.myhoard.model.dao.CollectionDS;

@Service("collectionService")
public class CollectionService {

	private CollectionDAO collectionDAO;

	@Autowired
	public void setCollectionDAO(CollectionDAO collectionDAO) {
		this.collectionDAO = collectionDAO;
	}

	public List<CollectionDS> getList() {
		return collectionDAO.getList();
	}

	public CollectionDS get(int i) {
		return collectionDAO.get(i);
	}

	public void create(CollectionDS collection) {
		Date date = new java.util.Date();
		collection.setCreatedDate(new Timestamp(date.getTime()));
		collection.setModifiedDate(new Timestamp(date.getTime()));
		collection.setOwner("Moja babcia");
		collectionDAO.create(collection);
	}

	public void update(CollectionDS collection) {
		collectionDAO.update(collection);
	}

	public void remove(int i) {
		collectionDAO.remove(i);
	}

}
