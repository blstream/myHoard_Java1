package com.blstream.myhoard.db.dao;

import com.blstream.myhoard.db.model.CommentDS;
import java.util.List;

public interface CommentDAO extends ResourceDAO<CommentDS> {

    public List<CommentDS> getListByCollection(int collectionId);

}
