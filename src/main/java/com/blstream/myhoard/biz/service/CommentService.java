package com.blstream.myhoard.biz.service;

import java.util.List;

import com.blstream.myhoard.biz.model.CommentDTO;
import com.blstream.myhoard.exception.MyHoardException;

public interface CommentService extends ResourceService<CommentDTO> {

    public CommentDTO get(String id) throws MyHoardException;

    public List<CommentDTO> getListByCollection(String collectionId) throws MyHoardException;

}
