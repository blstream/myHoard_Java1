package com.blstream.myhoard.db.dao;

import java.util.List;

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

		List<CollectionDS> collections = sessionFactory.getCurrentSession()
				.createQuery("FROM CollectionDS").list();
		return collections;
	}

	public CollectionDS get(int id) {

		CollectionDS collection = (CollectionDS) sessionFactory
				.getCurrentSession().get(CollectionDS.class, id);
		return collection;
	}

	public void create(CollectionDS obj) {

		sessionFactory.getCurrentSession().save(obj);
	}

	public void update(CollectionDS obj) {

		sessionFactory.getCurrentSession().update(obj);
	}

	public void remove(int id) {

		CollectionDS collection = (CollectionDS) sessionFactory
				.getCurrentSession().get(CollectionDS.class, id);
		if (collection != null) {
			sessionFactory.getCurrentSession().delete(collection);
		}
	}

}
