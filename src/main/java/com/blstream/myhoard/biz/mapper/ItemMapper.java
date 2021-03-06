package com.blstream.myhoard.biz.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.blstream.myhoard.biz.model.GeoPointDTO;
import com.blstream.myhoard.biz.model.ItemDTO;
import com.blstream.myhoard.db.model.CollectionDS;
import com.blstream.myhoard.db.model.ItemDS;
import com.blstream.myhoard.db.model.MediaDS;

public class ItemMapper {
    
    

    public static ItemDS map(ItemDTO itemDTO, CollectionDS collection, Set<MediaDS> media) {
        ItemDS itemDS = new ItemDS();
        itemDS.setName(itemDTO.getName());
        itemDS.setDescription(itemDTO.getDescription());
        if (itemDTO.getLocation() != null) {
            itemDS.setLat(itemDTO.getLocation().getLat());
            itemDS.setLng(itemDTO.getLocation().getLng());
        }
        if (itemDTO.getQuantity() != null) {
            itemDS.setQuantity(itemDTO.getQuantity());
        }
        itemDS.setCreatedDate(itemDTO.getCreatedDate());
        itemDS.setModifiedDate(itemDTO.getModifiedDate());
        itemDS.setCreatedDateClient(itemDTO.getCreatedDateClient());
        itemDS.setModifiedDateClient(itemDTO.getModifiedDateClient());
        itemDS.setCollection(collection);
        if (media != null) {
            itemDS.setMedia(media);
        }
        if (itemDTO.isForSale() != null) {
            itemDS.setForSale(itemDTO.isForSale());
        }

        return itemDS;
    }

    public static ItemDTO map(ItemDS itemDS) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(String.valueOf(itemDS.getId()));
        itemDTO.setName(itemDS.getName());
        itemDTO.setDescription(itemDS.getDescription());
        itemDTO.setLocation(new GeoPointDTO(itemDS.getLat(), itemDS.getLng()));
        itemDTO.setQuantity(itemDS.getQuantity());
        itemDTO.setCreatedDateClient(itemDS.getCreatedDateClient());
        itemDTO.setModifiedDateClient(itemDS.getModifiedDateClient());
        itemDTO.setCollection(String.valueOf(itemDS.getCollection().getId()));
        itemDTO.setOwner(UserMapper.map(itemDS.getOwner()));
        itemDTO.setMedia(MediaMapper.mapToSetDTO(itemDS.getMedia()));
        itemDTO.setForSale(itemDS.isForSale());
        
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
