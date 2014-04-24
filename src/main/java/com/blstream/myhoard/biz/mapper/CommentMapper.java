package com.blstream.myhoard.biz.mapper;

import com.blstream.myhoard.biz.model.CommentDTO;
import com.blstream.myhoard.db.model.CommentDS;

public class CommentMapper {

    public static CommentDS map(CommentDTO commentDTO) {
        CommentDS commentDS = new CommentDS();
        commentDS.setContent(commentDTO.getContent());
        commentDS.setCreatedDate(commentDTO.getCreatedDate());
        
        return commentDS;
    }

    public static CommentDTO map(CommentDS commentDS) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(String.valueOf(commentDS.getId()));
        commentDTO.setContent(commentDS.getContent());
        commentDTO.setCreatedDate(commentDS.getCreatedDate());
        commentDTO.setOwner(UserMapper.map(commentDS.getOwner()));
        commentDTO.setCollection(String.valueOf(commentDS.getCollection().getId()));

        return commentDTO;
    }
}
