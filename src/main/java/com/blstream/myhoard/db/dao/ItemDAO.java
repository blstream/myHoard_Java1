package com.blstream.myhoard.db.dao;

import com.blstream.myhoard.db.model.ItemDS;
import java.util.List;

public interface ItemDAO {

    public List<ItemDS> getListByUser(int id);

    public List<ItemDS> getList();
    
    public List<ItemDS> getList(String name, int collection, String owner);

    public ItemDS get(int id);

    public void create(ItemDS object);

    public void update(ItemDS object);

    public void remove(int id);
}
