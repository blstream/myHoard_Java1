package com.blstream.myhoard.db.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blstream.myhoard.db.model.ItemDS;

@Repository
@Transactional
public class ItemDAO implements ResourceDAO<ItemDS> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<ItemDS> getList() {
		return sessionFactory.getCurrentSession().createCriteria(ItemDS.class).list();
	}

	@Override
	public ItemDS get(int id) {
		return (ItemDS) sessionFactory.getCurrentSession().get(ItemDS.class, id);
	}

	@Override
	public void create(ItemDS object) {
		sessionFactory.getCurrentSession().save(object);
	}

	@Override
	public void update(ItemDS object) {
		sessionFactory.getCurrentSession().update(object);
	}

	@Override
	public void remove(int id) {
		sessionFactory.getCurrentSession().delete(get(id));
	}

}
