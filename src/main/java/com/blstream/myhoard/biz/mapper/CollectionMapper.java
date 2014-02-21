package com.blstream.myhoard.biz.mapper;

import java.util.ArrayList;
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

		collectionDS.setCreatedDate(collectionDTO.getCreatedDate());
		collectionDS.setDescription(collectionDTO.getDescription());
		try {
		collectionDS.setId(Integer.parseInt(collectionDTO.getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		collectionDS.setItemsNumber(collectionDTO.getItemsNumber());
		collectionDS.setModifiedDate(collectionDTO.getModifiedDate());
		collectionDS.setName(collectionDTO.getName());
		collectionDS.setOwner(collectionDTO.getOwner());
		collectionDS.setTags(collectionDTO.getTags());

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
		collectionDTO.setItemsNumber(collectionDS.getItemsNumber());
		collectionDTO.setModifiedDate(collectionDS.getModifiedDate());
		collectionDTO.setName(collectionDS.getName());
		collectionDTO.setOwner(collectionDS.getOwner());
		collectionDTO.setTags(collectionDS.getTags());

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
