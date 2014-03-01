package com.blstream.myhoard.biz.mapper;

import java.util.Arrays;

import com.blstream.myhoard.biz.model.ItemDTO;
import com.blstream.myhoard.db.model.CollectionDS;
import com.blstream.myhoard.db.model.ItemDS;

// TODO RT implements method mapping list of items
public class ItemMapper {

	public static final ItemDS map(ItemDTO itemDTO, CollectionDS collection) {
		ItemDS itemDS = new ItemDS();
		itemDS.setName(itemDTO.getName());
		itemDS.setDescription(itemDTO.getDescription());
		itemDS.setLocation(itemDTO.getLocation());
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
		itemDTO.setLocation(itemDS.getLocation());
		itemDTO.setQuantity(itemDS.getQuantity());
		itemDTO.setCreatedDate(itemDS.getCreatedDate());
		itemDTO.setModifiedDate(itemDS.getModifiedDate());
		itemDTO.setCollection(String.valueOf(itemDS.getCollection().getId()));
		itemDTO.setOwner(itemDS.getOwner());
		itemDTO.setMedia(Arrays.asList(new String[] { "TODO MEDIA" }));

		return itemDTO;
	}

}
