package com.blstream.myhoard.db.dao;

import java.util.List;

import com.blstream.myhoard.db.model.CommentDS;

public interface CommentDAO extends ResourceDAO<CommentDS> {

    public List<CommentDS> getListByCollection(int collectionId);

}
