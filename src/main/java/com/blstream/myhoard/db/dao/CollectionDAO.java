package com.blstream.myhoard.db.dao;

import java.util.List;

import com.blstream.myhoard.biz.model.CollectionDTO;
import com.blstream.myhoard.db.model.CollectionDS;
import com.blstream.myhoard.exception.MyHoardException;

public interface CollectionDAO extends ResourceDAO<CollectionDS> {

    @Override
    public List<CollectionDS> getList();

    @Override
    public CollectionDS get(int id);

    @Override
    public void create(CollectionDS object) throws MyHoardException;

    @Override
    public void update(CollectionDS object) throws MyHoardException;

    @Override
    public void remove(int id);

    public List<CollectionDS> getList(List<String> sortBy, String sortDirection)
            throws MyHoardException;
    
    public boolean isNameUnique(CollectionDTO collectionDTO);
    
    public CollectionDS getById(int id);
    
    public List<CollectionDS> getList(String name, int owner);
    
    public List<String> getObservers(int collection);
}
