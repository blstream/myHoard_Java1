package com.blstream.myhoard.db.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blstream.myhoard.authorization.service.SecurityService;
import com.blstream.myhoard.db.model.CommentDS;
import com.blstream.myhoard.exception.MyHoardException;

@Repository
@Transactional
public class CommentDAOImpl implements CommentDAO {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private SecurityService securityService;

    @Override
    public CommentDS get(int id) throws MyHoardException {
        return (CommentDS) sessionFactory.getCurrentSession().createCriteria(CommentDS.class, "comment")
        		.createAlias("comment.collection", "collection")
        		.createAlias("collection.owner",  "owner")
        		.add(Restrictions.eq("comment.id", id))
        		.add(Restrictions.disjunction()
					.add(Restrictions.eq("owner.id", Integer.parseInt(securityService.getCurrentUser().getId())))
					.add(Restrictions.eq("collection.isPublic", true)))
        		.setMaxResults(1)
        		.uniqueResult();
    }

    @Override
    public void create(CommentDS object) throws MyHoardException {
        sessionFactory.getCurrentSession().save(object);
    }

    @Override
    public void update(CommentDS object) throws MyHoardException {
        sessionFactory.getCurrentSession().update(object);
    }

    @Override
    public void remove(int id) throws MyHoardException {
        sessionFactory.getCurrentSession().delete(get(id));
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<CommentDS> getListByCollection(int collectionId) {

        return sessionFactory.getCurrentSession()
                .createCriteria(CommentDS.class, "comment")
                .createAlias("comment.collection", "collection")
                .createAlias("collection.owner", "owner")
                .add(Restrictions.eq("collection.id", collectionId))
				.add(Restrictions.disjunction()
					.add(Restrictions.eq("owner.id", Integer.parseInt(securityService.getCurrentUser().getId())))
					.add(Restrictions.eq("collection.isPublic", true)))
                .list();
    }

    // RT - unused
    @Override
    public List<CommentDS> getList() throws MyHoardException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
