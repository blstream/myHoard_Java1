package com.blstream.myhoard.biz.service;

import com.blstream.myhoard.authorization.service.SecurityService;
import com.blstream.myhoard.biz.mapper.MediaMapper;
import com.blstream.myhoard.biz.model.MediaDTO;
import com.blstream.myhoard.db.dao.MediaDAO;
import com.blstream.myhoard.db.dao.UserDAO;
import com.blstream.myhoard.db.model.MediaDS;
import com.blstream.myhoard.db.model.UserDS;
import com.blstream.myhoard.exception.MyHoardException;
import com.blstream.myhoard.exception.NotFoundException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mediaService")
public class MediaServiceImpl implements MediaService {

	private static final Logger logger = Logger
			.getLogger(MediaServiceImpl.class.getCanonicalName());

	@Autowired
	SecurityService securityService;
	
	@Autowired
	private MediaDAO mediaDAO;
	
    @Autowired
    private UserDAO userDAO;

	@Override
	public List<MediaDTO> getList() throws MyHoardException {
		List<MediaDS> mediaDSs = mediaDAO.getList();
		if (mediaDSs == null) {
			logger.info("Media Service list is null");
		}
		List<MediaDTO> mediaDTOs = MediaMapper.map(mediaDSs);

		return mediaDTOs;
	}

	@Override
	public MediaDTO get(int id) throws MyHoardException {
		MediaDS mediaDS = mediaDAO.get(id);

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
		UserDS user = userDAO.get(Integer.parseInt(securityService.getCurrentUser().getId()));
		
		mediaDS.setOwner(user);
		mediaDAO.create(mediaDS);

		MediaDTO media = MediaMapper.map(mediaDS);

		return media;
	}

	@Override
	public MediaDTO update(MediaDTO mediaDTO) throws MyHoardException {

		MediaDS mediaDS = MediaMapper.map(mediaDTO);
		MediaDS sourceMediaDS = mediaDAO.get(mediaDS.getId());

		if (sourceMediaDS == null) {
			logger.error("mediaDSBaza == null");
			throw new MyHoardException();
		}
		
		if(sourceMediaDS.getOwner().getId() != Integer.parseInt(securityService.getCurrentUser().getId())) {
			throw new NotFoundException(String.format("Access denied for media id = ", sourceMediaDS.getId()));
		}

		if(mediaDS.getFile() != null) {
			sourceMediaDS.setFile(mediaDS.getFile());
		}
		
		if(mediaDS.getItemDS() != null ) {
			sourceMediaDS.setItemDS(mediaDS.getItemDS());
		}
		
		if(mediaDS.getThumbnail() != null) {
			sourceMediaDS.setThumbnail(mediaDTO.getThumbnail());
		}

		mediaDAO.update(sourceMediaDS);

		MediaDTO mediaDTOBaza = MediaMapper.map(sourceMediaDS);

		return mediaDTOBaza;
	}

	@Override
	public void remove(int id) throws MyHoardException {
		MediaDS mediaDS = mediaDAO.get(id);

		if (mediaDS == null) {
			logger.error("mediaDS null");
			throw new MyHoardException();
		}

		mediaDAO.remove(mediaDS.getId());

	}
}
