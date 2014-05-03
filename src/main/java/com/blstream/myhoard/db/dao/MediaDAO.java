package com.blstream.myhoard.db.dao;

import java.util.List;

import com.blstream.myhoard.db.model.MediaDS;

public interface MediaDAO {

    public List<MediaDS> getList();

    public MediaDS get(int id);

    public void create(MediaDS object);

    public void update(MediaDS object);

    public void remove(int id);

}
