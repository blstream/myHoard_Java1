package com.blstream.myhoard.db.dao;

import com.blstream.myhoard.db.model.TokenDS;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TokenDAO {

        @Autowired
        private SessionFactory sessionFactory;

        public void create(TokenDS object) {
                sessionFactory.getCurrentSession().save(object);
        }

        public void update(TokenDS object) {
                sessionFactory.getCurrentSession().update(object);
        }

        public TokenDS getByAccessToken(String accessToken) {
                Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TokenDS.class);
                criteria.add(Restrictions.eq("accessToken", accessToken));
                criteria.setMaxResults(1);

                return (TokenDS) criteria.uniqueResult();
        }

        public TokenDS getByRefreshToken(String refreshToken) {
                Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TokenDS.class);
                criteria.add(Restrictions.eq("refreshToken", refreshToken));
                criteria.setMaxResults(1);

                return (TokenDS) criteria.uniqueResult();
        }

        public TokenDS getById(int id) {

                return (TokenDS) sessionFactory.getCurrentSession().get(TokenDS.class, id);
        }
}
