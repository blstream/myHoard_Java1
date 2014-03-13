package com.blstream.myhoard.biz.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class MediaUtils {

	private static final Logger logger = Logger.getLogger(MediaUtils.class
			.getCanonicalName());

	public byte[] getThumbnail(byte[] image, int size) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			long start = new Date().getTime();
			Thumbnails.of(new ByteArrayInputStream(image))
					.outputFormat("jpg").size(size, size).toOutputStream(baos);
			long elapsed = new Date().getTime() - start;

			logger.info("thumbnailator time: " + elapsed + "[ms], size: "
					+ size);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return baos.toByteArray();
	}
}
