package com.blstream.myhoard.biz.mapper;

import com.blstream.myhoard.biz.model.CollectionDTO;
import com.blstream.myhoard.db.model.CollectionDS;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.apache.log4j.Logger;
import java.util.Set;
import com.blstream.myhoard.db.model.TagDS;

public class CollectionMapper {
        
        private static final Logger logger = Logger.getLogger(CollectionMapper.class.getCanonicalName());

	/**
	 * Metoda przepisuje obiekt CollectionDTO na obiekt CollectionDS
	 * 
	 * @param collectionDTO
	 * @return collectionDS
	 */
	public static CollectionDS map(CollectionDTO collectionDTO) {
		CollectionDS collectionDS = new CollectionDS();

		collectionDS.setCreatedDate(collectionDTO.getCreatedDate());
		collectionDS.setDescription(collectionDTO.getDescription());
		try {
			collectionDS.setId(Integer.parseInt(collectionDTO.getId()));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		collectionDS.setItemsNumber(collectionDTO.getItemsNumber());
		collectionDS.setModifiedDate(collectionDTO.getModifiedDate());
		collectionDS.setName(collectionDTO.getName());
		collectionDS.setOwner(collectionDTO.getOwner());

		if (collectionDTO.getTags() != null) {

			List<String> tags = collectionDTO.getTags();
			Set<TagDS> tagSet = new HashSet<TagDS>();
			
			for(String tag : tags) {
				
				TagDS tmpTag = new TagDS(tag);
				tagSet.add(tmpTag);
				
			}
			collectionDS.setTags(tagSet);
		}
		
		return collectionDS;
	}

	/**
	 * Metoda przepisuje obiekt CollectionDS na obiekt CollectionDTO
	 * 
	 * @param collectionDS
	 * @return collectionDTO
	 */
	public static CollectionDTO map(CollectionDS collectionDS) {
		CollectionDTO collectionDTO = new CollectionDTO();

		collectionDTO.setCreatedDate(collectionDS.getCreatedDate());
		collectionDTO.setDescription(collectionDS.getDescription());
		collectionDTO.setId(String.valueOf(collectionDS.getId()));
		collectionDTO.setItemsNumber(collectionDS.getItems() != null ? collectionDS.getItems().size() : 0); // TODO
		collectionDTO.setModifiedDate(collectionDS.getModifiedDate());
		collectionDTO.setName(collectionDS.getName());
		collectionDTO.setOwner(collectionDS.getOwner());
		
		List<String> tagi = new ArrayList<String>();
		Set<TagDS> tagList = collectionDS.getTags();
		
		for(TagDS tmp : tagList)
			tagi.add(tmp.getName());
		
		collectionDTO.setTags(tagi);
		
		return collectionDTO;
	}

	/**
	 * @param List
	 *            <CollectionDS>
	 * @return List<CollectionDTO>
	 */
	public static List<CollectionDTO> map(List<CollectionDS> collectionDS) {
		List<CollectionDTO> collectionDTOs = new ArrayList<CollectionDTO>();

		for (CollectionDS collection : collectionDS) {
			collectionDTOs.add(map(collection));
		}
		
		return collectionDTOs;
	}

}
