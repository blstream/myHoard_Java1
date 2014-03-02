package com.blstream.myhoard.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.blstream.myhoard.biz.model.MediaDTO;
import com.blstream.myhoard.biz.service.ResourceService;
import com.blstream.myhoard.exception.CollectionRestException;
import com.blstream.myhoard.exception.MyHoardException;

@Controller
@RequestMapping("/media")
public class MediaController {

	@Autowired
	@Qualifier("mediaService")
	ResourceService<MediaDTO> mediaService;

	@RequestMapping(method = RequestMethod.POST)
	public String addMedia(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) throws IOException {

		System.out.println("media upload");

//		MediaDTO media = new MediaDTO();
//		media.setCreatedDate(new Date());
//		media.setId(500);
//
//		try {
//			media.setFile(file.getBytes());
//			media.setThumbnail(file.getBytes());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		try {
//			mediaService.create(media);
//		} catch (MyHoardException e1) {
//			e1.printStackTrace();
//		}

		throw new CollectionRestException(2222);
	}

	@RequestMapping(value = "/a")
	@ResponseBody
	public byte[] getFile() {
		MediaDTO m = null;
		try {
			m = mediaService.get(1);
		} catch (MyHoardException e) {
			e.printStackTrace();
		}

		return m.getFile();
	}

}