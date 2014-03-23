package com.blstream.myhoard.db.dao;

import com.blstream.myhoard.db.model.MediaDS;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class MediaDAOImpl implements MediaDAO {

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