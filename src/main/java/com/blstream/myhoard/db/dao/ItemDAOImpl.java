package com.blstream.myhoard.db.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blstream.myhoard.authorization.service.SecurityService;
import com.blstream.myhoard.db.model.ItemDS;

@Repository
@Transactional
public class ItemDAOImpl implements ItemDAO {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private SecurityService securityService;
    
    @SuppressWarnings("unchecked")
	@Override
    public List<ItemDS> getListByUser(int id) {
        return sessionFactory.getCurrentSession().createCriteria(ItemDS.class)
                .add(Restrictions.eq("owner.id", id)).list();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<ItemDS> getList() {
        return sessionFactory.getCurrentSession().createCriteria(ItemDS.class, "item")
				.createAlias("item.collection", "collection")
				.createAlias("collection.owner",  "owner")
				.add(Restrictions.disjunction()
					.add(Restrictions.eq("owner.id", securityService.getCurrentUser().getId()))
					.add(Restrictions.eq("collection.isPublic", true)))	
        		.list();
    }

    @Override
    public ItemDS get(int id) {
    	
    	return (ItemDS) sessionFactory.getCurrentSession().createCriteria(ItemDS.class, "item")
    			.createAlias("item.collection", "collection")
        		.createAlias("collection.owner", "owner")
        		.add(Restrictions.eq("item.id", id))
				.add(Restrictions.disjunction()
					.add(Restrictions.eq("owner.id", Integer.parseInt(securityService.getCurrentUser().getId())))
					.add(Restrictions.eq("collection.isPublic", true)))
    			.setMaxResults(1)
    			.uniqueResult();
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
        sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(ItemDS.class, id));
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<ItemDS> getList(String name, int collection, int owner) {
		
		return sessionFactory.getCurrentSession().createCriteria(ItemDS.class, "item")
				.createAlias("item.collection", "collection")
				.createAlias("collection.owner", "owner")
				.add(Restrictions.disjunction()
						.add(Restrictions.ilike("item.name", "%" + name + "%"))
						.add(Restrictions.ilike("item.description", "%" + name + "%"))
					)
				.add(Restrictions.eq("collection.id", collection))
				.add(Restrictions.disjunction()
					.add(Restrictions.eq("owner.id", owner))
					.add(Restrictions.eq("collection.isPublic", true)))
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ItemDS> getList(int id, List<String> sortBy,
			String sortDirection) {

		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				ItemDS.class, "item");

		if (sortDirection.equals("asc")) {
			for (String sortByElem : sortBy) {
				crit.addOrder(Order.asc(sortByElem));
			}
		} else {
			for (String sortByElem : sortBy) {
				crit.addOrder(Order.desc(sortByElem));
			}
		}

		crit.createAlias("item.collection", "collection");
		crit.createAlias("collection.owner", "owner");
		crit.add(Restrictions.eq("collection.id", id));
		crit.add(Restrictions.disjunction()
			.add(Restrictions.eq("owner.id", securityService.getCurrentUser().getId()))
			.add(Restrictions.eq("collection.isPublic", true)));
		
		return crit.list();
	}
    
    @Override
    public boolean isUniqueNameOfCollectionItem(String name, int collectionId) {
        @SuppressWarnings("unchecked")
		List<ItemDS> itemDS = sessionFactory.getCurrentSession()
                .createCriteria(ItemDS.class)
                .add(Restrictions.eq("name", name))
                .add(Restrictions.eq("collection.id", collectionId)).list();
    
        return itemDS.isEmpty();
    }

}
