package com.blstream.myhoard.biz.service;

import com.blstream.myhoard.authorization.service.SecurityService;
import com.blstream.myhoard.biz.mapper.CommentMapper;
import com.blstream.myhoard.biz.model.CommentDTO;
import com.blstream.myhoard.db.dao.CollectionDAO;
import com.blstream.myhoard.db.dao.CommentDAO;
import com.blstream.myhoard.db.dao.UserDAO;
import com.blstream.myhoard.db.model.CollectionDS;
import com.blstream.myhoard.db.model.CommentDS;
import com.blstream.myhoard.db.model.UserDS;
import com.blstream.myhoard.exception.MyHoardException;
import com.blstream.myhoard.exception.NotFoundException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

    private static final String COMMENT_NOT_EXIST = "Comment with id = %s not exist";
    private static final String COLLECTION_WITH_ID_NOT_EXIST = "Collection with id = %s not exist";

    private static final Logger logger = Logger.getLogger(CommentServiceImpl.class.getCanonicalName());

    @Autowired
    private CommentDAO commentDAO;
    @Autowired
    private CollectionDAO collectionDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private SecurityService securityService;

    @Override
    public CommentDTO get(int id) throws MyHoardException {
        CommentDS commentDS = commentDAO.get(id);
        if (commentDS == null) {
            throw new NotFoundException(String.format(COMMENT_NOT_EXIST, id));
        }

        return CommentMapper.map(commentDS);
    }

    @Override
    public CommentDTO get(String id) throws MyHoardException {

        return get(Integer.parseInt(id));
    }

    @Override
    public CommentDTO create(CommentDTO commentDTO) throws MyHoardException {
        UserDS userDS = userDAO.get(Integer.parseInt(securityService.getCurrentUser().getId()));
        CollectionDS collectionDS = collectionDAO.get(Integer.parseInt(commentDTO.getCollection()));
        if (collectionDS == null) {
            throw new NotFoundException(String.format(COLLECTION_WITH_ID_NOT_EXIST, commentDTO.getCollection()));
        }
        commentDTO.setCreatedDate(new Timestamp(new Date().getTime()));
        CommentDS commentDS = CommentMapper.map(commentDTO);
        commentDS.setCollection(collectionDS);
        commentDS.setOwner(userDS);
        commentDAO.create(commentDS);

        return CommentMapper.map(commentDS);
    }

    @Override
    public CommentDTO update(CommentDTO commentDTO) throws MyHoardException {
        CommentDS commentDS = commentDAO.get(Integer.parseInt(commentDTO.getId()));
        commentDS.setModifiedDate(new Date());
        if (commentDTO.getContent() != null) {
            commentDS.setContent(commentDTO.getContent());
        }
        if (commentDTO.getCollection() != null) {
            CollectionDS collectionDS = collectionDAO.get(Integer.parseInt(commentDTO.getCollection()));
            if (collectionDS == null) {
                throw new NotFoundException(String.format(COLLECTION_WITH_ID_NOT_EXIST, commentDTO.getCollection()));
            }
            commentDS.setCollection(collectionDS);
        }
        commentDAO.update(commentDS);

        return CommentMapper.map(commentDS);
    }

    @Override
    public void remove(int id) throws MyHoardException {
        commentDAO.remove(id);
    }

    @Override
    public List<CommentDTO> getListByCollection(String collectionId) throws MyHoardException {
        List<CommentDS> commentDsList = commentDAO.getListByCollection(Integer.parseInt(collectionId));

        return CommentMapper.map(commentDsList);
    }

    // RT - unused
    @Override
    public List<CommentDTO> getList() throws MyHoardException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
