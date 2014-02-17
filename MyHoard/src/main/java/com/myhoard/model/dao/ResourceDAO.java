package com.myhoard.model.dao;

import java.util.List;

public interface ResourceDAO<T> {

	public List<T> getList();
	public T get(int id);
	public void create(T object);
	public void update (T object);
	public void remove(T object);
}
