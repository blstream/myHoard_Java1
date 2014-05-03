package com.blstream.myhoard.db.dao;

import java.util.List;

import com.blstream.myhoard.exception.MyHoardException;

public interface ResourceDAO<T> {

    public List<T> getList() throws MyHoardException;

    public T get(int id) throws MyHoardException;

    public void create(T object) throws MyHoardException;

    public void update(T object) throws MyHoardException;

    public void remove(int id) throws MyHoardException;
}
