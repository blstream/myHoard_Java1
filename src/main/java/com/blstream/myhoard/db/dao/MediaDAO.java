package com.blstream.myhoard.db.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blstream.myhoard.db.model.MediaDS;

@Repository
@Transactional
public class MediaDAO implements ResourceDAO<MediaDS> {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MediaDS> getList() {
		return sessionFactory.getCurrentSession().createCriteria(MediaDS.class)
				.list();
	}

	@Override
	public MediaDS get(int id) {
		return (MediaDS) getSession().get(MediaDS.class, id);
	}

	@Override
	public void create(MediaDS object) {
		getSession().save(object);
	}

	@Override
	public void update(MediaDS object) {
		getSession().update(object);
	}

	@Override
	public void remove(int id) {
		MediaDS media = (MediaDS) sessionFactory.getCurrentSession().get(
				MediaDS.class, id);
		if (media != null) {
			sessionFactory.getCurrentSession().delete(media);
		}
	}

}