package com.blstream.myhoard.biz.service;

import com.blstream.myhoard.biz.model.CommentDTO;
import com.blstream.myhoard.exception.MyHoardException;
import java.util.List;

public interface CommentService extends ResourceService<CommentDTO> {

    public CommentDTO get(String id) throws MyHoardException;

    public List<CommentDTO> getListByCollection(String collectionId) throws MyHoardException;

}
