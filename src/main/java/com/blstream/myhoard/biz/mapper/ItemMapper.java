package com.blstream.myhoard.biz.mapper;

import com.blstream.myhoard.biz.model.GeoPointDTO;
import com.blstream.myhoard.biz.model.ItemDTO;
import com.blstream.myhoard.db.model.CollectionDS;
import com.blstream.myhoard.db.model.ItemDS;
import com.blstream.myhoard.db.model.MediaDS;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ItemMapper {

        public static ItemDS map(ItemDTO itemDTO, CollectionDS collection, Set<MediaDS> media) {
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
                itemDS.setMedia(media);

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
                itemDTO.setMedia(MediaMapper.mapToSetDTO(itemDS.getMedia()));

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
