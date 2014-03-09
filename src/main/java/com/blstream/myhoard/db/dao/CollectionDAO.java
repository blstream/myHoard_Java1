package com.blstream.myhoard.db.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blstream.myhoard.db.model.CollectionDS;
import com.blstream.myhoard.db.model.TagDS;

@Repository
@Transactional
public class CollectionDAO implements ResourceDAO<CollectionDS> {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<CollectionDS> getList() {

		List<CollectionDS> collections = sessionFactory.getCurrentSession()
				.createCriteria(CollectionDS.class)
				.list();
		return collections;
	}

	public CollectionDS get(int id) {

		CollectionDS collection = (CollectionDS) sessionFactory
				.getCurrentSession().get(CollectionDS.class, id);
		return collection;
	}
	
	public void create(CollectionDS obj) {
		
		List<TagDS> tags = this.getTagsFromDB(obj);
		
		for(TagDS tag : obj.getTags()) {
			boolean toSave = false;
			for(TagDS dbTag : tags) {
				if(tag.getName().equals(dbTag.getName())) {
					toSave = true;
					//org.hibernate.NonUniqueObjectException
					//tag.setId(dbTag.getId());
					break;
				}
			}
			if(toSave == false) {
				sessionFactory.getCurrentSession().save(tag);
			}
		}
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
	
	@SuppressWarnings("unchecked")
	private List<TagDS> getTagsFromDB(CollectionDS obj) {
		
		List<String> tagsName = new ArrayList<String>();
		
		for(TagDS tag : obj.getTags()) {
			tagsName.add(tag.getName());
		}
		
		List<TagDS> tags = sessionFactory.getCurrentSession()
			.createCriteria(TagDS.class)
			.add(Restrictions.in("name", tagsName.toArray()))
			.list();
		
		return tags;
	}

}
