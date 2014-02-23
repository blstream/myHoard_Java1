package com.blstream.myhoard.db.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blstream.myhoard.db.model.CollectionDS;

@Repository
@Transactional
public class CollectionDAO implements ResourceDAO<CollectionDS> {

	@Autowired
    private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<CollectionDS> getList() {

		Session session = sessionFactory.openSession();
		List<CollectionDS> collections = session.createQuery(
				"FROM CollectionDS").list();
		session.close();
		return collections;
	}

	
	public CollectionDS get(int id) {

		Session session = sessionFactory.openSession();
		CollectionDS collection = (CollectionDS) session.get(
				CollectionDS.class, id);
		session.close();
		return collection;
	}

	public void create(CollectionDS obj) {

		Session session = sessionFactory.openSession();
		session.save(obj);
		session.close();
	}

	public void update(CollectionDS obj) {

		Session session = sessionFactory.openSession();
		session.update(obj);
		session.close();
	}

	public void remove(int id) {

		Session session = sessionFactory.openSession();
		CollectionDS collection = (CollectionDS) session.get(
				CollectionDS.class, id);
		if (collection != null)
			session.delete(collection);
		session.close();
	}

}
