package com.blstream.myhoard.biz.service;

import com.blstream.myhoard.biz.mapper.MediaMapper;
import com.blstream.myhoard.biz.model.MediaDTO;
import com.blstream.myhoard.db.dao.ResourceDAO;
import com.blstream.myhoard.db.model.ItemDS;
import com.blstream.myhoard.db.model.MediaDS;
import com.blstream.myhoard.exception.MyHoardException;
import com.blstream.myhoard.exception.NotFoundException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("mediaService")
public class MediaService extends ResourceService<MediaDTO> {

    private static final Logger logger = Logger.getLogger(MediaService.class
            .getCanonicalName());

    @Autowired
    @Qualifier("mediaDAO")
    ResourceDAO<MediaDS> mediaDao;

    @Autowired
    @Qualifier("itemDAO")
    ResourceDAO<ItemDS> itemDao;

    @Override
    public List<MediaDTO> getList() throws MyHoardException {

        List<MediaDS> mediaDSs = mediaDao.getList();
        if (mediaDSs == null) {
            logger.info("Media Service list is null");
        }
        List<MediaDTO> mediaDTOs = MediaMapper.map(mediaDSs);

        return mediaDTOs;
    }

    @Override
    public MediaDTO get(int id) throws MyHoardException {

        MediaDS mediaDS = mediaDao.get(id);

        if (mediaDS == null) {
            logger.info("Media not exist");
            throw new NotFoundException(String.format(
                    "Media with id = %s not exist", id));
        }

        MediaDTO mediaDTO = MediaMapper.map(mediaDS);

        return mediaDTO;
    }

    @Override
    public MediaDTO create(MediaDTO mediaDTO) throws MyHoardException {

        Date date = new java.util.Date();

        mediaDTO.setCreatedDate(new Timestamp(date.getTime()));

        MediaDS mediaDS = MediaMapper.map(mediaDTO);

        // pozniej poprawie
        mediaDS.setItemDS(itemDao.get(1));

        mediaDao.create(mediaDS);

        MediaDTO media = MediaMapper.map(mediaDS);

        return media;
    }

    @Override
    public MediaDTO update(MediaDTO mediaDTO) throws MyHoardException {

        MediaDS mediaDS = MediaMapper.map(mediaDTO);
        MediaDS mediaDSBaza = mediaDao.get(mediaDS.getId());

        if (mediaDSBaza == null) {
            logger.error("mediaDSBaza == null");
            throw new MyHoardException();
        }

        mediaDSBaza.setFile(mediaDS.getFile());
        mediaDSBaza.setItemDS(mediaDS.getItemDS());
        mediaDSBaza.setThumbnail(mediaDTO.getThumbnail());

        mediaDao.update(mediaDSBaza);

        MediaDTO mediaDTOBaza = MediaMapper.map(mediaDSBaza);

        return mediaDTOBaza;
    }

    @Override
    public void remove(int id) throws MyHoardException {

        MediaDS mediaDS = mediaDao.get(id);

        if (mediaDS == null) {
            logger.error("mediaDS null");
            throw new MyHoardException();
        }

        mediaDao.remove(mediaDS.getId());

    }
}
