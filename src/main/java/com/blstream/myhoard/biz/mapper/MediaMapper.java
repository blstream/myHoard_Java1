package com.blstream.myhoard.biz.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.blstream.myhoard.biz.model.MediaDTO;
import com.blstream.myhoard.db.model.MediaDS;

public class MediaMapper {

	public static MediaDS map(MediaDTO mediaDTO) {

		MediaDS mediaDS = new MediaDS();

		mediaDS.setCreatedDate(mediaDTO.getCreatedDate());
		mediaDS.setFile(mediaDTO.getFile());
		mediaDS.setId(Integer.parseInt(mediaDTO.getId()));
		mediaDS.setItemDS(mediaDTO.getItemDS());
		mediaDS.setThumbnail(mediaDTO.getThumbnail());

		return mediaDS;
	}

	public static MediaDTO map(MediaDS mediaDS) {

		MediaDTO mediaDTO = new MediaDTO();

		mediaDTO.setCreatedDate(mediaDS.getCreatedDate());
		mediaDTO.setFile(mediaDS.getFile());
		mediaDTO.setId(String.valueOf(mediaDS.getId()));
		mediaDTO.setItemDS(mediaDS.getItemDS());
		mediaDTO.setThumbnail(mediaDS.getThumbnail());
		mediaDTO.setUrl(buildUrl(mediaDS.getId()));

		return mediaDTO;
	}

	public static List<MediaDTO> map(List<MediaDS> mediaDSs) {

		List<MediaDTO> mediaDTOs = new ArrayList<MediaDTO>();

		for (MediaDS mediaDS : mediaDSs) {
			mediaDTOs.add(MediaMapper.map(mediaDS));
		}

		return mediaDTOs;
	}
	
	public static Set<MediaDTO> map(Set<MediaDS> mediaDSSet) {
		// TODO MT generate hashCode and equals MediaDTO class
		Set<MediaDTO> mediaDTOs = new HashSet<MediaDTO>();
		for (MediaDS mediaDS : mediaDSSet) {
			mediaDTOs.add(MediaMapper.map(mediaDS));
		}
		return mediaDTOs;
	}

	private static String buildUrl(int id) {

		StringBuilder url = new StringBuilder();

		url.append(id);

		return url.toString();
	}

}
