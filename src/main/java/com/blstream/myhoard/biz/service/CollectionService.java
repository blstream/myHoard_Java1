package com.blstream.myhoard.biz.service;

import com.blstream.myhoard.biz.model.CollectionDTO;
import com.blstream.myhoard.exception.MyHoardException;

import java.util.List;

public interface CollectionService extends ResourceService<CollectionDTO> {

    @Override
    List<CollectionDTO> getList() throws MyHoardException;

    @Override
    CollectionDTO get(int i) throws MyHoardException;

    @Override
    CollectionDTO create(CollectionDTO collectionDTO) throws MyHoardException;

    @Override
    CollectionDTO update(CollectionDTO collectionDTO) throws MyHoardException;

    @Override
    void remove(int i) throws MyHoardException;

	public List<CollectionDTO> getList(String name, String owner)
			throws MyHoardException;
	
	List<CollectionDTO> getList(List<String> sortBy, String sortDirection)
            throws MyHoardException;
    
    boolean isNameUnique(CollectionDTO collectionDTO);

	List<CollectionDTO> getFavoriteCollections();

	void addToFavoriteCollections(int id);

	void deleteFromFavoriteCollections(int parseInt);
}
