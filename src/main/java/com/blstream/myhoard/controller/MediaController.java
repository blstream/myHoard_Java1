package com.blstream.myhoard.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

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

		MediaDTO media = new MediaDTO();
		media.setCreatedDate(new Date());
		media.setId(500);

		if (file.isEmpty()) {
			System.out.println("EMPTY");
			return "upload.jsp";
		}

		try {
			media.setFile(file.getBytes());
			media.setThumbnail(file.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			mediaService.create(media);
		} catch (MyHoardException e1) {
			e1.printStackTrace();
		}

		return "upload.jsp";

		// throw new CollectionRestException(2222);
	}

	@RequestMapping(value = "/a")
	@ResponseBody
	public byte[] getFile() {
		List<MediaDTO> lista = null;
		try {
			lista = mediaService.getList();
		} catch (MyHoardException e) {
			e.printStackTrace();
		}

		System.out.println(lista.size());

		return lista.get(lista.size() - 1).getFile();
	}

}