package com.blstream.myhoard.db.dao;

import com.blstream.myhoard.db.model.UserDS;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// TODO RT implement
@Repository
@Transactional
public class UserDAO {

        @Autowired
        private SessionFactory sessionFactory;

        public List<UserDS> getList() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public UserDS get(int id) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        public List<UserDS> getByEmail(String email) {
                return sessionFactory.getCurrentSession().createCriteria(UserDS.class).add(Restrictions.eq("email", email)).list();
        }

        public void create(UserDS object) {
                sessionFactory.getCurrentSession().save(object);
        }

        public void update(UserDS object) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public void remove(int id) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

}
