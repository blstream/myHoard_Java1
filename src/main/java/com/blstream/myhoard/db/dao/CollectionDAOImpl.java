package com.blstream.myhoard.db.dao;

import com.blstream.myhoard.db.model.CollectionDS;
import com.blstream.myhoard.db.model.TagDS;
import com.blstream.myhoard.exception.MyHoardException;
import com.blstream.myhoard.exception.ResourceAlreadyExistException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CollectionDAOImpl implements CollectionDAO {

    private static final Logger logger = Logger.getLogger(CollectionDAOImpl.class.getCanonicalName());

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<CollectionDS> getList() {

        List<CollectionDS> collections = sessionFactory.getCurrentSession()
                .createCriteria(CollectionDS.class).list();
        return collections;
    }

    public CollectionDS get(int id) {

        CollectionDS collection = (CollectionDS) sessionFactory
                .getCurrentSession().get(CollectionDS.class, id);
        return collection;
    }

    public void create(CollectionDS obj) throws MyHoardException {

        if (!this.isUnique(obj)) {
            logger.error("Collection exist!");
            throw new ResourceAlreadyExistException(String.format("Collection with name: %s already exist!", obj.getName()));
        }

        List<TagDS> tagsFromDb = getTagsFromDB(obj);
        Set<TagDS> tagsSrc = obj.getTags();
        Set<TagDS> tagsToSave = new HashSet<TagDS>();

        for (TagDS tagSrc : tagsSrc) {
            boolean isExisting = false;
            for (TagDS tagDb : tagsFromDb) {
                if (tagDb.getName().equals(tagSrc.getName())) {
                    isExisting = true;
                    tagsToSave.add(tagDb);
                    break;
                }
            }
            if (!isExisting) {
                tagsToSave.add(tagSrc);
            }
        }

        obj.setTags(null);
        obj.setTags(tagsToSave);
        sessionFactory.getCurrentSession().save(obj);
    }

    public void update(CollectionDS obj) throws MyHoardException {

        if (!this.isUnique(obj)) {
            logger.error("Collection exist!");
            throw new ResourceAlreadyExistException(String.format("Collection with name: %s already exist!", obj.getName()));
        }

        CollectionDS objectFromDb = (CollectionDS) sessionFactory
                .getCurrentSession().get(CollectionDS.class, obj.getId());
        Set<TagDS> tagsToSave = new HashSet<TagDS>();

        for (TagDS tagSrc : obj.getTags()) {
            boolean isExisting = false;
            for (TagDS tagDb : this.getTagsFromDB(obj)) {
                if (tagSrc.getName().equals(tagDb.getName())) {
                    isExisting = true;
                    tagsToSave.add(tagDb);
                    break;
                }
            }
            if (!isExisting) {
                tagsToSave.add(tagSrc);
            }
        }

        objectFromDb.setName(obj.getName());
        objectFromDb.setDescription(obj.getDescription());
        objectFromDb.setTags(tagsToSave);
        objectFromDb.setModifiedDate(obj.getModifiedDate());

        sessionFactory.getCurrentSession().update(objectFromDb);
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
        List<TagDS> tags = new ArrayList<TagDS>();

        if (obj != null && !obj.getTags().isEmpty()) {
            List<String> tagsName = new ArrayList<String>();
            for (TagDS tag : obj.getTags()) {
                tagsName.add(tag.getName());
            }
            tags = sessionFactory.getCurrentSession()
                    .createCriteria(TagDS.class)
                    .add(Restrictions.in("name", tagsName.toArray())).list();
        }
        return tags;

    }

    private boolean isUnique(CollectionDS obj) {

        int numberOfCollections = sessionFactory.getCurrentSession()
                .createCriteria(CollectionDS.class)
                .add(Restrictions.eq("name", obj.getName()))
                .add(Restrictions.eq("owner", obj.getOwner())).list().size();
        if (numberOfCollections > 0) {
            return false;
        } else {
            return true;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CollectionDS> getList(List<String> sortBy, String sortDirection)
            throws MyHoardException {

        Criteria crit = sessionFactory.getCurrentSession().createCriteria(
                CollectionDS.class);

        if (sortDirection.equals("asc")) {
            for (String sortByElem : sortBy) {
                crit.addOrder(Order.asc(sortByElem));
            }
        } else {
            for (String sortByElem : sortBy) {
                crit.addOrder(Order.desc(sortByElem));
            }

        }

        return crit.list();
    }

}
