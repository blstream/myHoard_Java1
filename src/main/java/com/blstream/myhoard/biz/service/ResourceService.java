package com.blstream.myhoard.biz.service;

import java.util.List;

import com.blstream.myhoard.exception.CollectionException;

public interface ResourceService<T> {

	List<T> getList() throws CollectionException;

	T get(int i) throws CollectionException;

	T create(T t) throws CollectionException;

	T update(T t) throws CollectionException;

	void remove(int i) throws CollectionException;

}
