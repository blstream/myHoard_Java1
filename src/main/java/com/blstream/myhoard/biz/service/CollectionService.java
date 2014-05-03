package com.blstream.myhoard.biz.service;

import java.util.List;

import com.blstream.myhoard.biz.model.CollectionDTO;
import com.blstream.myhoard.exception.MyHoardException;

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

	List<CollectionDTO> getFavouriteCollections();

	void addToFavouriteCollections(int id);

	void deleteFromFavouriteCollections(int parseInt);
	
	CollectionDTO getById(int id) throws MyHoardException;
	
	List<CollectionDTO> getFavouriteCollectionsByUserId(int id);
}
