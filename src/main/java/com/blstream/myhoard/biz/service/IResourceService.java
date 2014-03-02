package com.blstream.myhoard.biz.service;

import java.util.List;

import com.blstream.myhoard.exception.MyHoardException;

public interface IResourceService<T> {

	List<T> getList() throws MyHoardException;

	T get(int i) throws MyHoardException;

	T create(T t) throws MyHoardException;

	T update(T t) throws MyHoardException;

	void remove(int i) throws MyHoardException;

}
