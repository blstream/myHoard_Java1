package com.blstream.myhoard.biz.mapper;

import java.util.ArrayList;
import java.util.List;

import com.blstream.myhoard.biz.model.CommentDTO;
import com.blstream.myhoard.db.model.CommentDS;

public class CommentMapper {

    public static CommentDS map(CommentDTO commentDTO) {
        CommentDS commentDS = new CommentDS();
        commentDS.setContent(commentDTO.getContent());
        commentDS.setCreatedDate(commentDTO.getCreatedDate());
        commentDS.setModifiedDate(commentDTO.getCreatedDate());

        return commentDS;
    }

    public static CommentDTO map(CommentDS commentDS) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(String.valueOf(commentDS.getId()));
        commentDTO.setContent(commentDS.getContent());
        commentDTO.setCreatedDate(commentDS.getCreatedDate());
        commentDTO.setOwner(UserMapper.map(commentDS.getOwner()));
        commentDTO.setCollection(String.valueOf(commentDS.getCollection().getId()));
        commentDTO.setCollectionDTO(CollectionMapper.map(commentDS.getCollection()));

        return commentDTO;
    }

    public static List<CommentDTO> map(List<CommentDS> itemDSList) {
        List<CommentDTO> commentDTOList = new ArrayList<CommentDTO>();
        for (CommentDS ids : itemDSList) {
            commentDTOList.add(map(ids));
        }
        return commentDTOList;
    }
}
