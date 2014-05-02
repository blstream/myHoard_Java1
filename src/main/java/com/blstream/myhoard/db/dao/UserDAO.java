package com.blstream.myhoard.db.dao;

import java.util.List;

import com.blstream.myhoard.db.model.CollectionDS;
import com.blstream.myhoard.db.model.UserDS;

public interface UserDAO extends ResourceDAO<UserDS> {

    public UserDS getByEmail(String email);
    
    public List<CollectionDS> getListOfUserCollections(int id);

    @Override
    public UserDS get(int id);

    @Override
    public void create(UserDS object);

    @Override
    public void update(UserDS object);

    @Override
    public void remove(int id);

}
