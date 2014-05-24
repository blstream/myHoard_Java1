package com.blstream.myhoard.db.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blstream.myhoard.authorization.service.SecurityService;
import com.blstream.myhoard.db.model.CollectionDS;
import com.blstream.myhoard.db.model.UserDS;
import com.blstream.myhoard.exception.MyHoardException;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private SecurityService securityService;

    @Override
    public UserDS getByEmail(String email) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserDS.class);
        criteria.add(Restrictions.eq("email", email).ignoreCase());
        criteria.setMaxResults(1);

        return (UserDS) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<UserDS> getList() throws MyHoardException {
        return sessionFactory.getCurrentSession().createCriteria(UserDS.class)
                .add(Restrictions.eq("publicAccount", true)).list();
    }

    @Override
    public UserDS get(int id) {
        return (UserDS) sessionFactory.getCurrentSession().get(UserDS.class, id);
    }

    @Override
    public void create(UserDS object) {
        sessionFactory.getCurrentSession().save(object);
    }

    @Override
    public void update(UserDS object) {
        sessionFactory.getCurrentSession().update(object);
    }

    @Override
    public void remove(int id) {
        sessionFactory.getCurrentSession().delete(get(id));
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<CollectionDS> getListOfUserCollections(int id) {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CollectionDS.class);
		if(Integer.parseInt(securityService.getCurrentUser().getId()) == id) {
			criteria.add(Restrictions.eq("owner.id", id));
		}
		else {
			criteria.add(Restrictions.eq("owner.id", id))
					.add(Restrictions.eq("isPublic", true));
		}
		return criteria.list();
	}
	
	@Override
	public List<CollectionDS> getListOfUserFavouriteCollections(int id) {
		UserDS user = (UserDS) sessionFactory.getCurrentSession().get(UserDS.class, id);
		Set<CollectionDS> favoriteCollections = user.getFavoriteCollections();
		Set<CollectionDS> favoriteCollectionsWithoutPrivate = new HashSet<CollectionDS>();
		
		if(securityService.getCurrentUser().getId().equals(String.valueOf(id))) {
			for (CollectionDS collection : favoriteCollections) {
				if(collection.getIsPublic() || collection.getOwner().getId() == Integer.parseInt(securityService.getCurrentUser().getId())) {
					favoriteCollectionsWithoutPrivate.add(collection);
				}
			}	
		} else {
			for (CollectionDS collection : favoriteCollections) {
				if(collection.getIsPublic() || collection.getOwner().getId() == id) {
					favoriteCollectionsWithoutPrivate.add(collection);
				}
			}
		}
		
		return new ArrayList<CollectionDS>(favoriteCollectionsWithoutPrivate);
	}

    @Override
	public void saveWithFavoriteCollection(int id, CollectionDS collectionToSave) {
		UserDS user = (UserDS) sessionFactory.getCurrentSession().get(UserDS.class, id);
		Set<CollectionDS> favoriteCollections = user.getFavoriteCollections();
		favoriteCollections.add(collectionToSave);
		user.setFavoriteCollections(favoriteCollections);
		update(user);
	}
	
    @Override
	public void saveWithoutFavoriteCollection(int id, CollectionDS collectionToDelete) {
		UserDS user = (UserDS) sessionFactory.getCurrentSession().get(UserDS.class, id);
		Set<CollectionDS> favoriteCollections = user.getFavoriteCollections();
		Iterator<CollectionDS> it = favoriteCollections.iterator();
		while(it.hasNext()) {
			CollectionDS collection = it.next();
			if(collection.getId() == collectionToDelete.getId()) {
				it.remove();
			}
		}
		user.setFavoriteCollections(favoriteCollections);
		update(user);
	}
}
