package com.blstream.myhoard.db.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blstream.myhoard.authorization.service.SecurityService;
import com.blstream.myhoard.db.model.MediaDS;

@Repository
@Transactional
public class MediaDAOImpl implements MediaDAO {

	@Autowired
	private SessionFactory sessionFactory;

    @Autowired
    private SecurityService securityService;
    
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MediaDS> getList() {
		return sessionFactory.getCurrentSession()
				.createCriteria(MediaDS.class, "media")
				.createAlias("media.item", "item")
				.createAlias("item.collection", "collection")
				.add(Restrictions.disjunction()
					.add(Restrictions.eq("media.owner", securityService.getCurrentUser().getId()))
					.add(Restrictions.eq("collection.isPublic", true)))
				.list();
	}

	@Override
	public MediaDS get(int id) {
		return (MediaDS) sessionFactory.getCurrentSession()
			.createCriteria(MediaDS.class, "media")
			.createAlias("media.itemDS", "item")
			.createAlias("item.collection", "collection")
			.createAlias("media.owner",  "owner")
			.add(Restrictions.eq("media.id", id))
			.add(Restrictions.disjunction()
				.add(Restrictions.eq("owner.id", Integer.parseInt(securityService.getCurrentUser().getId())))
				.add(Restrictions.eq("collection.isPublic", true)))
			.setMaxResults(1)
			.uniqueResult();
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