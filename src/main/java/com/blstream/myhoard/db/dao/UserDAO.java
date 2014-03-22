package com.blstream.myhoard.db.dao;

import com.blstream.myhoard.db.model.UserDS;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDAO {

        @Autowired
        private SessionFactory sessionFactory;

        // TODO RT implement
        public List<UserDS> getList() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public UserDS get(int id) {
                return (UserDS) sessionFactory.getCurrentSession().get(UserDS.class, id);
        }

        public UserDS getByEmail(String email) {
                Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserDS.class);
                criteria.add(Restrictions.eq("email", email));
                criteria.setMaxResults(1);

                return (UserDS) criteria.uniqueResult();
        }

        public void create(UserDS object) {
                sessionFactory.getCurrentSession().save(object);
        }

        // TODO RT implement
        public void update(UserDS object) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        // TODO RT implement
        public void remove(int id) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

}
