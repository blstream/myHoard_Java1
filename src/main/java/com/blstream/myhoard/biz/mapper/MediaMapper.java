package com.blstream.myhoard.biz.mapper;

import java.util.ArrayList;
import java.util.List;

import com.blstream.myhoard.biz.model.MediaDTO;
import com.blstream.myhoard.db.model.MediaDS;

public class MediaMapper {

	public static MediaDS map(MediaDTO mediaDTO) {

		MediaDS mediaDS = new MediaDS();

		mediaDS.setCreatedDate(mediaDTO.getCreatedDate());
		mediaDS.setFile(mediaDTO.getFile());
		mediaDS.setId(mediaDTO.getId());
		mediaDS.setItemDS(mediaDTO.getItemDS());
		mediaDS.setThumbnail(mediaDTO.getThumbnail());

		return mediaDS;
	}

	public static MediaDTO map(MediaDS mediaDS) {

		MediaDTO mediaDTO = new MediaDTO();

		mediaDTO.setCreatedDate(mediaDS.getCreatedDate());
		mediaDTO.setFile(mediaDS.getFile());
		mediaDTO.setId(mediaDS.getId());
		mediaDTO.setItemDS(mediaDS.getItemDS());
		mediaDTO.setThumbnail(mediaDS.getThumbnail());

		return mediaDTO;
	}

	public static List<MediaDTO> map(List<MediaDS> mediaDSs) {

		List<MediaDTO> mediaDTOs = new ArrayList<MediaDTO>();

		for (MediaDS mediaDS : mediaDSs) {
			mediaDTOs.add(MediaMapper.map(mediaDS));
		}

		return mediaDTOs;
	}

}
