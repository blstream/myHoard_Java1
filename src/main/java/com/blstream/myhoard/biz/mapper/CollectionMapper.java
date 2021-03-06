package com.blstream.myhoard.biz.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.blstream.myhoard.biz.model.CollectionDTO;
import com.blstream.myhoard.db.model.CollectionDS;
import com.blstream.myhoard.db.model.ItemDS;
import com.blstream.myhoard.db.model.TagDS;

public class CollectionMapper {

    private static final Logger logger = Logger.getLogger(CollectionMapper.class.getCanonicalName());

    public static CollectionDS map(CollectionDTO collectionDTO) {
        CollectionDS collectionDS = new CollectionDS();

        collectionDS.setCreatedDate(collectionDTO.getCreatedDate());
        collectionDS.setCreatedDateClient(collectionDTO.getCreatedDateClient());
        collectionDS.setDescription(collectionDTO.getDescription());
        try {
            collectionDS.setId(Integer.parseInt(collectionDTO.getId()));
        } catch (Exception e) {
            logger.error("map error", e);
        }
        collectionDS.setIsPublic(collectionDTO.getIsPublic());
        collectionDS.setItemsNumber(collectionDTO.getItemsNumber());
        collectionDS.setModifiedDate(collectionDTO.getModifiedDate());
        collectionDS.setModifiedDateClient(collectionDTO.getModifiedDateClient());
        collectionDS.setName(collectionDTO.getName());
        //collectionDS.setOwner(UserMapper.map(collectionDTO.getOwner()));

        if (collectionDTO.getTags() != null) {

            List<String> tags = collectionDTO.getTags();
            Set<TagDS> tagSet = new HashSet<TagDS>();

            for (String tag : tags) {
                TagDS tmpTag = new TagDS(tag);
                tagSet.add(tmpTag);
            }
            collectionDS.setTags(tagSet);
        }

        return collectionDS;
    }

    public static CollectionDTO map(CollectionDS collectionDS) {
        CollectionDTO collectionDTO = new CollectionDTO();

        collectionDTO.setCreatedDateClient(collectionDS.getCreatedDateClient());
        collectionDTO.setDescription(collectionDS.getDescription());
        collectionDTO.setIsPublic(collectionDS.getIsPublic());
        collectionDTO.setId(String.valueOf(collectionDS.getId()));
        collectionDTO.setItemsNumber(collectionDS.getItems() != null ? collectionDS.getItems().size() : 0);
        collectionDTO.setModifiedDateClient(collectionDS.getModifiedDateClient());
        collectionDTO.setName(collectionDS.getName());
        if(collectionDS.getOwner() != null) {
        	collectionDTO.setOwner(UserMapper.map(collectionDS.getOwner()));
        }

        List<String> tagi = new ArrayList<String>();
        Set<TagDS> tagList = collectionDS.getTags();

        for (TagDS tmp : tagList) {
            tagi.add(tmp.getName());
        }

        collectionDTO.setTags(tagi);
        
        Set<ItemDS> itemSet = collectionDS.getItems();
        List<ItemDS> itemList = new ArrayList<ItemDS>();
		if (itemSet != null) {
			itemList.addAll(itemSet);
		}
        collectionDTO.setItems(ItemMapper.map(itemList));

        return collectionDTO;
    }

    public static List<CollectionDTO> map(List<CollectionDS> collectionDS) {
        List<CollectionDTO> collectionDTOs = new ArrayList<CollectionDTO>();

        for (CollectionDS collection : collectionDS) {
            collectionDTOs.add(map(collection));
        }

        return collectionDTOs;
    }

}
