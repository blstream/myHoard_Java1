package com.blstream.myhoard.controller;

import com.blstream.myhoard.biz.model.MediaDTO;
import com.blstream.myhoard.biz.service.ResourceService;
import com.blstream.myhoard.exception.CollectionRestException;
import com.blstream.myhoard.exception.ErrorCodeEnum;
import com.blstream.myhoard.exception.MyHoardException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/media")
public class MediaController {

        private static final Logger logger = Logger.getLogger(MediaController.class.getCanonicalName());

	@Autowired
	@Qualifier("mediaService")
	ResourceService<MediaDTO> mediaService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public String create(MultipartFile file, HttpServletRequest request)
			throws IOException {

                logger.info(getClass().toString() + ": create");

		MediaDTO media = new MediaDTO();
		media.setCreatedDate(new Date());

		if (file.isEmpty()) {
			throw new CollectionRestException(ErrorCodeEnum.CREATE.getValue());
		}

		try {
			media.setFile(file.getBytes());
			media.setThumbnail(file.getBytes());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new CollectionRestException(ErrorCodeEnum.CREATE.getValue());
		}

		try {
			mediaService.create(media);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new CollectionRestException(ErrorCodeEnum.CREATE.getValue());
		}

		return "upload.jsp";

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public byte[] read(@PathVariable("id") String idStr) {

		logger.info(getClass().toString() + ": read");

		int id = 0;

		try {
			id = Integer.parseInt(idStr);
			return mediaService.get(id).getFile();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new CollectionRestException(ErrorCodeEnum.READ.getValue());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public MediaDTO update(@PathVariable("id") String idStr,
			@RequestBody MultipartFile file) {

                logger.info(getClass().toString() + ": update");

		int id = 0;
		try {
			id = Integer.parseInt(idStr);
			MediaDTO mediaDTO = mediaService.get(id);
			if (mediaDTO == null) {
				throw new CollectionRestException(
						ErrorCodeEnum.UPDATE.getValue());
			}

			mediaDTO.setFile(file.getBytes());
			mediaService.update(mediaDTO);
			return mediaDTO;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new CollectionRestException(ErrorCodeEnum.UPDATE.getValue());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public void delete(@PathVariable("id") String idStr) {

                logger.info(getClass().toString() + ": delete");

		int id = 0;
		try {
			id = Integer.parseInt(idStr);
			mediaService.remove(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new CollectionRestException(ErrorCodeEnum.DELETE.getValue());
		}
	}

	@RequestMapping(value = "/{id}/thumbnail", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public byte[] readThumbnail(@PathVariable("id") String idStr) {

                logger.info(getClass().toString() + ": readThumbnail");

		int id = 0;

		try {
			id = Integer.parseInt(idStr);
			return mediaService.get(id).getThumbnail();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new CollectionRestException(ErrorCodeEnum.READ.getValue());
		}
	}

	@RequestMapping(value = "/a")
	@ResponseBody
	public byte[] getFile() {

                logger.info(getClass().toString() + ": getFile");

		List<MediaDTO> lista = null;
		try {
			lista = mediaService.getList();
		} catch (MyHoardException e) {
			logger.error(e.getMessage(), e);
		}

		return lista.get(lista.size() - 1).getFile();
	}

}