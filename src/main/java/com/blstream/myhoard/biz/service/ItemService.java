package com.blstream.myhoard.biz.service;

import com.blstream.myhoard.biz.mapper.ItemMapper;
import com.blstream.myhoard.biz.model.ItemDTO;
import com.blstream.myhoard.biz.model.MediaDTO;
import com.blstream.myhoard.db.dao.ResourceDAO;
import com.blstream.myhoard.db.model.CollectionDS;
import com.blstream.myhoard.db.model.ItemDS;
import com.blstream.myhoard.db.model.MediaDS;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("itemService")
public class ItemService extends ResourceService<ItemDTO> {

        private static final Logger logger = Logger.getLogger(ItemService.class.getCanonicalName());

        @Autowired
        private ResourceDAO<ItemDS> itemDAO;
        @Autowired
        private ResourceDAO<CollectionDS> collectionDAO;
        @Autowired
        private ResourceDAO<MediaDS> mediaDAO;

        @Override
        public ItemDTO create(ItemDTO itemDTO) {
                CollectionDS collectionDS = null;
                Date date = new Date();
                itemDTO.setCreatedDate(new Timestamp(date.getTime()));
                itemDTO.setModifiedDate(new Timestamp(date.getTime()));
                try {
                        int collectionId = Integer.parseInt(itemDTO.getCollection());
                        collectionDS = collectionDAO.get(collectionId);
                } catch (Exception e) {
                        logger.error(e.toString(), e);
                }

                Set<MediaDS> mediaDS = new HashSet<MediaDS>();
                for (MediaDTO mediaDTO : itemDTO.getMedia()) {
                        mediaDS.add(mediaDAO.get(Integer.parseInt(mediaDTO.getId())));
                }

                ItemDS itemDS = ItemMapper.map(itemDTO, collectionDS, mediaDS);
                itemDAO.create(itemDS);
                itemDTO = ItemMapper.map(itemDS);

                return itemDTO;
        }

        @Override
        public List<ItemDTO> getList() {
                List<ItemDS> itemDSList = itemDAO.getList();
                List<ItemDTO> itemList = ItemMapper.map(itemDSList);
                
                return itemList;
        }

        @Override
        public ItemDTO get(int id) {
                ItemDS itemDS = itemDAO.get(id);
                ItemDTO itemDTO = ItemMapper.map(itemDS);
                
                return itemDTO;
        }

        @Override
        public ItemDTO update(ItemDTO itemDTO) {
                CollectionDS collectionDS = null;
                try {
                        int collectionId = Integer.parseInt(itemDTO.getCollection());
                        collectionDS = collectionDAO.get(collectionId);
                } catch (Exception e) {
                        logger.error(e.toString(), e);
                }

                Set<MediaDS> mediaDS = new HashSet<MediaDS>();
                for (MediaDTO mediaDTO : itemDTO.getMedia()) {
                        mediaDS.add(mediaDAO.get(Integer.parseInt(mediaDTO.getId())));
                }
                ItemDS updateItemDS = ItemMapper.map(itemDTO, collectionDS, mediaDS);

                ItemDS itemDS = itemDAO.get(Integer.parseInt(itemDTO.getId()));
                itemDS.setModifiedDate(new Timestamp(new Date().getTime()));
                itemDS.setName(updateItemDS.getName());
                itemDS.setDescription(updateItemDS.getDescription());
                itemDS.setLat(updateItemDS.getLat());
                itemDS.setLng(updateItemDS.getLng());
                itemDS.setQuantity(updateItemDS.getQuantity());
                itemDS.setCollection(updateItemDS.getCollection());
                itemDS.setMedia(updateItemDS.getMedia());
                itemDAO.update(itemDS);
                
                itemDTO = ItemMapper.map(itemDS);
                
                return itemDTO;
        }

        @Override
        public void remove(int id) {

                itemDAO.remove(id);
        }

}
