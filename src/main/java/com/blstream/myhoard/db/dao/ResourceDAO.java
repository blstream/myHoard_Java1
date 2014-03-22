package com.blstream.myhoard.db.dao;

import com.blstream.myhoard.exception.MyHoardException;
import java.util.List;

public interface ResourceDAO<T> {

    public List<T> getList();

    public T get(int id);

    public void create(T object) throws MyHoardException;

    public void update(T object) throws MyHoardException;

    public void remove(int id);
}
