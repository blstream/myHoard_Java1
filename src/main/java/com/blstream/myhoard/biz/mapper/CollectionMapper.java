package com.blstream.myhoard.biz.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.blstream.myhoard.biz.model.CollectionDTO;
import com.blstream.myhoard.db.model.CollectionDS;

public class CollectionMapper {

	/**
	 * Metoda przepisuje obiekt CollectionDTO na obiekt CollectionDS
	 * 
	 * @param collectionDTO
	 * @return collectionDS
	 */
	public static CollectionDS map(CollectionDTO collectionDTO) {
		CollectionDS collectionDS = new CollectionDS();

		collectionDS.setCreatedDate(collectionDTO.getCreated_date());
		collectionDS.setDescription(collectionDTO.getDescription());
		try {
			collectionDS.setId(Integer.parseInt(collectionDTO.getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		collectionDS.setItemsNumber(collectionDTO.getItems_number());
		collectionDS.setModifiedDate(collectionDTO.getModified_date());
		collectionDS.setName(collectionDTO.getName());
		collectionDS.setOwner(collectionDTO.getOwner());
		// collectionDS.setTags(collectionDTO.getTags());

		if (collectionDTO.getTags() != null) {

			List<String> tags = collectionDTO.getTags();
			StringBuilder tagsStr = new StringBuilder();
			String regex = "@NEXT@";

			for (String tag : tags) {
				tagsStr.append(regex);
				tagsStr.append(tag);
			}

			collectionDS.setTags(tagsStr.toString());
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

		collectionDTO.setCreated_date(collectionDS.getCreatedDate());
		collectionDTO.setDescription(collectionDS.getDescription());
		collectionDTO.setId(String.valueOf(collectionDS.getId()));
		collectionDTO.setItems_number(collectionDS.getItemsNumber());
		collectionDTO.setModified_date(collectionDS.getModifiedDate());
		collectionDTO.setName(collectionDS.getName());
		collectionDTO.setOwner(collectionDS.getOwner());

		if (collectionDS.getTags() != null) {
			String regex = "@NEXT@";
			String[] split = collectionDS.getTags().split(regex);
			List<String> wordList = Arrays.asList(split);

			collectionDTO.setTags(wordList);
		}
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
