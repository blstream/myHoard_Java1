package com.blstream.myhoard.db.dao;

import com.blstream.myhoard.db.model.ItemDS;

import java.util.List;

public interface ItemDAO extends ResourceDAO<ItemDS> {

    public List<ItemDS> getListByUser(int id);

    public List<ItemDS> getList(String name, int collection, String owner);

    @Override
    public List<ItemDS> getList();

    @Override
    public ItemDS get(int id);

    @Override
    public void create(ItemDS object);

    @Override
    public void update(ItemDS object);

    @Override
    public void remove(int id);

	public List<ItemDS> getList(int id, List<String> sortBy, String sortDirection);
}
