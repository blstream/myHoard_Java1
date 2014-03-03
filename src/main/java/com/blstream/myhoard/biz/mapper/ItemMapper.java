package com.blstream.myhoard.biz.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.blstream.myhoard.biz.model.GeoPointDTO;
import com.blstream.myhoard.biz.model.ItemDTO;
import com.blstream.myhoard.db.model.CollectionDS;
import com.blstream.myhoard.db.model.ItemDS;

public class ItemMapper {

	public static final ItemDS map(ItemDTO itemDTO, CollectionDS collection) {
		ItemDS itemDS = new ItemDS();
		itemDS.setName(itemDTO.getName());
		itemDS.setDescription(itemDTO.getDescription());
		itemDS.setLat(itemDTO.getLocation().getLat());
		itemDS.setLng(itemDTO.getLocation().getLng());
		itemDS.setQuantity(itemDTO.getQuantity());
		itemDS.setCreatedDate(itemDTO.getCreatedDate());
		itemDS.setModifiedDate(itemDTO.getModifiedDate());
		itemDS.setOwner(collection.getOwner());
		itemDS.setCollection(collection);

		return itemDS;
	}

	public static ItemDTO map(ItemDS itemDS) {
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setId(String.valueOf(itemDS.getId()));
		itemDTO.setName(itemDS.getName());
		itemDTO.setDescription(itemDS.getDescription());
		itemDTO.setLocation(new GeoPointDTO(itemDS.getLat(), itemDS.getLng()));
		itemDTO.setQuantity(itemDS.getQuantity());
		itemDTO.setCreatedDate(itemDS.getCreatedDate());
		itemDTO.setModifiedDate(itemDS.getModifiedDate());
		itemDTO.setCollection(String.valueOf(itemDS.getCollection().getId()));
		itemDTO.setOwner(itemDS.getOwner());
		itemDTO.setMedia(MediaMapper.map(itemDS.getMedia()));

		return itemDTO;
	}

	public static List<ItemDTO> map(List<ItemDS> itemDSList) {
		List<ItemDTO> itemDTOList = new ArrayList<ItemDTO>();
		for (ItemDS ids : itemDSList) {
			itemDTOList.add(map(ids));
		}
		return itemDTOList;
	}
}
