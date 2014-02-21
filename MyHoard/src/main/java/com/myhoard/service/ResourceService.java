package com.myhoard.service;

import java.util.List;

public interface ResourceService<T> {

	List<T> getList();

	T get(int i);

	T create(T t);

	void update(T t);

	void remove(int i);

}
