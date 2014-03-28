package com.blstream.myhoard.db.dao;

import com.blstream.myhoard.db.model.CollectionDS;
import com.blstream.myhoard.exception.MyHoardException;
import java.util.List;

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
}
