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
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

    private static final Logger logger = Logger.getLogger(CommentServiceImpl.class.getCanonicalName());

    @Autowired
    private CommentDAO commentDAO;
    @Autowired
    private CollectionDAO collectionDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private SecurityService securityService;

    // TODO RT - implement
    @Override
    public CommentDTO get(int i) throws MyHoardException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // TODO RT - check it
    @Override
    public CommentDTO create(CommentDTO commentDTO) throws MyHoardException {
        UserDS userDS = userDAO.get(Integer.parseInt(securityService.getCurrentUser().getId()));
        CollectionDS collectionDS = collectionDAO.get(Integer.parseInt(commentDTO.getCollection()));
        commentDTO.setCreatedDate(new Timestamp(new Date().getTime()));

        CommentDS commentDS = CommentMapper.map(commentDTO);
        commentDS.setCollection(collectionDS);
        commentDS.setOwner(userDS);
        commentDAO.create(commentDS);

        return CommentMapper.map(commentDS);
    }

    // TODO RT - implement
    @Override
    public CommentDTO update(CommentDTO commentDTO) throws MyHoardException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // TODO RT - implement
    @Override
    public void remove(int i) throws MyHoardException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // RT - unused
    @Override
    public List<CommentDTO> getList() throws MyHoardException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
