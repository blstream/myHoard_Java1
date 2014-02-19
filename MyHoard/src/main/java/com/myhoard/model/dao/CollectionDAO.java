package com.myhoard.model.dao;

import java.util.List;

import org.hibernate.Session;
import com.myhoard.model.dao.Hibernate;


public class CollectionDAO implements ResourceDAO<CollectionDS> {
	
	@SuppressWarnings("unchecked")
	public List<CollectionDS> getList() {
		
		Session session = Hibernate.getSessionFactory().openSession();
		session.beginTransaction();
		List<CollectionDS> collections = session.createQuery("FROM CollectionDS").list();
		session.getTransaction().commit();
		session.close();
		return collections;
	}

	public CollectionDS get(int id) {
		
		Session session = Hibernate.getSessionFactory().openSession();
		session.beginTransaction();
		CollectionDS collection = (CollectionDS) session.get(CollectionDS.class,  id);
		session.getTransaction().commit();
		session.close();
		return collection;
	}

	public void create(CollectionDS obj) {
		
		Session session = Hibernate.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(obj);
		session.getTransaction().commit();
		session.close();
	}

	public void update(CollectionDS obj) {
		
		Session session = Hibernate.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(obj);
		session.getTransaction().commit();
		session.close();
	}

	public void remove(int id) {
		
		Session session = Hibernate.getSessionFactory().openSession();
		session.beginTransaction();
		CollectionDS collection = (CollectionDS) session.get(CollectionDS.class,  id);
		if(collection != null)
			session.delete(collection);
		session.getTransaction().commit();
		session.close();

	}
	
	
}
