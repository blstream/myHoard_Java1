package com.blstream.myhoard.db.dao;

import com.blstream.myhoard.db.model.UserDS;

public interface UserDAO {

    public UserDS getByEmail(String email);

    public UserDS get(int id);

    public void create(UserDS object);

    public void update(UserDS object);

    public void remove(int id);

}
