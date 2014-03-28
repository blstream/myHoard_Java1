package com.blstream.myhoard.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.blstream.myhoard.biz.model.MediaDTO;
import com.blstream.myhoard.biz.service.MediaService;
import com.blstream.myhoard.biz.util.MediaUtils;
import com.blstream.myhoard.biz.validator.MediaValidator;
import com.blstream.myhoard.exception.ErrorCodeEnum;
import com.blstream.myhoard.exception.MyHoardException;
import com.blstream.myhoard.exception.MyHoardRestException;
import com.blstream.myhoard.exception.NotFoundException;

@Controller
@RequestMapping("/media")
public class MediaController {

	private static final Logger logger = Logger.getLogger(MediaController.class
			.getCanonicalName());

	@Autowired
	private MediaService mediaService;

	@Autowired
	private MediaUtils mediaUtils;

	@Autowired
	private MediaValidator mediaValidator;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public MediaDTO create(MultipartFile file, HttpServletRequest request)
			throws IOException {

		logger.info("create");

		byte[] photo = mediaUtils.getFileFromMultipartFile(file); // file.getBytes();//mediaUtils.getFileFromRequest(request);

		MediaDTO mediaDTO = new MediaDTO();
		mediaDTO.setFile(photo);

		mediaValidator.validate(mediaDTO, "post");

		try {
			mediaDTO = mediaService.create(mediaDTO);
		} catch (MyHoardException e) {
			e.printStackTrace();
		}

		return mediaDTO;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public byte[] read(@PathVariable("id") String idStr,
			@RequestParam(value = "size", defaultValue = "0") int size) {

		logger.info("read");

		if (size != 0) {

			if (size != 500 && size != 340 && size != 300 && size != 160) {
				logger.error("Bad size: " + size);
				throw new NotFoundException(String.format(
						"Media with size = %s not exist", size));
			}

			logger.info("size [request param]: " + size);

			int id = 0;
			try {
				id = Integer.parseInt(idStr);
				byte[] image = mediaService.get(id).getFile();
				return mediaUtils.getThumbnail(image, size);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new NotFoundException(String.format(
						"Media with id = %s not exist", id));
			}

		}

		int id = 0;
		try {
			id = Integer.parseInt(idStr);
			return mediaService.get(id).getFile();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new NotFoundException(e.getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public MediaDTO update(@PathVariable("id") String idStr,
			HttpServletRequest request) {

		logger.info("update");

		byte[] file = mediaUtils.getFileFromRequest(request);

		int id = 0;
		try {
			id = Integer.parseInt(idStr);
			MediaDTO mediaDTO = mediaService.get(id);
			if (mediaDTO == null) {
				throw new MyHoardRestException(ErrorCodeEnum.UPDATE.getValue());
			}

			mediaDTO.setFile(file);
			mediaValidator.validate(mediaDTO, "put");
			mediaService.update(mediaDTO);
			return mediaDTO;
		} catch (MyHoardException ex) {
			logger.error(ex.getMessage(), ex);
			throw new MyHoardRestException(ErrorCodeEnum.UPDATE.getValue());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public void delete(@PathVariable("id") String idStr) {

		logger.info("delete");

		int id = 0;
		try {
			id = Integer.parseInt(idStr);
			mediaService.remove(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new MyHoardRestException(ErrorCodeEnum.DELETE.getValue());
		}
	}

	@RequestMapping(value = "/{id}/thumbnail", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public byte[] readThumbnail(@PathVariable("id") String idStr,
			HttpServletRequest request) {

		logger.info("readThumbnail");

		int size = 0;
		String parameter = request.getParameter("size");

		if (parameter != null) {

			try {
				size = Integer.parseInt(parameter);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}

			if (size != 500 && size != 340 && size != 300 && size != 140) {
				logger.error("Bad size: " + size);
				throw new NotFoundException(String.format(
						"Media with size = %s not exist", size));
			}

		}

		logger.info("size [request param]: " + size);

		int id = 0;
		try {
			id = Integer.parseInt(idStr);
			byte[] image = mediaService.get(id).getFile();
			return mediaUtils.getThumbnail(image, size);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new NotFoundException(String.format(
					"Media with id = %s not exist", id));
		}
	}

	@RequestMapping(value = "/a")
	@ResponseBody
	public byte[] getFile() {

		return null;

	}

}