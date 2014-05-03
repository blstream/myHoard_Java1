package com.blstream.myhoard.db.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blstream.myhoard.db.model.TokenDS;

@Repository
@Transactional
public class TokenDAOImpl implements TokenDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public TokenDS getByAccessToken(String accessToken) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TokenDS.class);
        criteria.add(Restrictions.eq("accessToken", accessToken));
        criteria.setMaxResults(1);

        return (TokenDS) criteria.uniqueResult();
    }

    @Override
    public TokenDS getByRefreshToken(String refreshToken) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TokenDS.class);
        criteria.add(Restrictions.eq("refreshToken", refreshToken));
        criteria.setMaxResults(1);

        return (TokenDS) criteria.uniqueResult();
    }

    @Override
    public TokenDS getById(int id) {

        return (TokenDS) sessionFactory.getCurrentSession().get(TokenDS.class, id);
    }

    @Override
    public void create(TokenDS object) {
        sessionFactory.getCurrentSession().save(object);
    }

    @Override
    public void update(TokenDS object) {
        sessionFactory.getCurrentSession().update(object);
    }
}
