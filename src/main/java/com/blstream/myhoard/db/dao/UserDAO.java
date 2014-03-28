package com.blstream.myhoard.db.dao;

import com.blstream.myhoard.db.model.UserDS;

public interface UserDAO extends ResourceDAO<UserDS> {

    public UserDS getByEmail(String email);

    @Override
    public UserDS get(int id);

    @Override
    public void create(UserDS object);

    @Override
    public void update(UserDS object);

    @Override
    public void remove(int id);

}
