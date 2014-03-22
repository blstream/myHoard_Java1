package com.blstream.myhoard.biz.service;

import java.util.List;

import com.blstream.myhoard.biz.model.CollectionDTO;
import com.blstream.myhoard.exception.MyHoardException;

public interface CollectionService {
	List<CollectionDTO> getList() throws MyHoardException;

	CollectionDTO get(int i) throws MyHoardException;

	CollectionDTO create(CollectionDTO collectionDTO) throws MyHoardException;

	CollectionDTO update(CollectionDTO collectionDTO) throws MyHoardException;

	void remove(int i) throws MyHoardException;

	List<CollectionDTO> getList(List<String> sortBy, String sortDirection)
			throws MyHoardException;
}
