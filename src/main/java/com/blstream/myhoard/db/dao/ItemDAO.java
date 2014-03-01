package com.blstream.myhoard.db.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blstream.myhoard.db.model.ItemDS;

// TODO RT implements all methods
@Repository
@Transactional
public class ItemDAO implements ResourceDAO<ItemDS> {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<ItemDS> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemDS get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(ItemDS object) {
		sessionFactory.getCurrentSession().save(object);
	}

	@Override
	public void update(ItemDS object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub
		
	}

}
