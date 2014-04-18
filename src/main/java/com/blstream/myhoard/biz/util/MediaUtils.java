package com.blstream.myhoard.biz.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

@Component
public class MediaUtils {

	private static final Logger logger = Logger.getLogger(MediaUtils.class
			.getCanonicalName());

	public byte[] getThumbnail(byte[] image, int size) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			long start = new Date().getTime();
			Thumbnails.of(new ByteArrayInputStream(image)).outputFormat("jpg")
					.size(size, size).toOutputStream(baos);
			long elapsed = new Date().getTime() - start;

			logger.info("thumbnailator time: " + elapsed + "[ms], size: "
					+ size);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return baos.toByteArray();
	}

	public byte[] getFileFromRequest(HttpServletRequest request) {

		ServletFileUpload fileUpload = new ServletFileUpload(
				new DiskFileItemFactory());
		List<FileItem> fileItems = null;
		byte[] fileUploadBytes = null;
		try {
			fileItems = fileUpload.parseRequest(request);
			InputStream in = fileItems.get(0).getInputStream();
			fileUploadBytes = IOUtils.toByteArray(in);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return fileUploadBytes;
	}

	public byte[] getFileFromMultipartFile(MultipartFile file)
			throws IOException {

		if (file == null) {
			return null;
		}

		if (!file.getContentType().contains("image")) {
			return null;
		}
		return file.getBytes();

	}

	public String getMetadane(byte[] bytes) {
		ByteArrayInputStream bai = new ByteArrayInputStream(bytes);
		org.xml.sax.ContentHandler contenthandler = new BodyContentHandler();
		org.apache.tika.metadata.Metadata metadata = new Metadata();
		org.apache.tika.parser.Parser parser = new AutoDetectParser();
		try {
			parser.parse(bai, contenthandler, metadata, new ParseContext());

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		}
		logger.info("Mime: " + metadata.get(Metadata.CONTENT_TYPE));
		return metadata.get(Metadata.CONTENT_TYPE);
	}

	public byte[] getImageCrop(byte[] src, int size) {

		logger.info("getImageCrop");

		long start = System.currentTimeMillis();

		InputStream in = new ByteArrayInputStream(src);
		BufferedImage image = null;
		try {
			image = ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
		}

		int w = image.getWidth();
		int h = image.getHeight();

		if (w < size || h < size || size == 0) {
			return src;
		}

		int startX = (w - size) / 2;
		int startY = (h - size) / 2;

		BufferedImage out = image.getSubimage(startX, startY, size, size);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] imageInByte = null;
		try {
			ImageIO.write(out, "png", baos);
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		long elapsed = System.currentTimeMillis() - start;

		logger.info("method: getImageCrop - execution time " + elapsed + " [ms]");

		return imageInByte;
	}
}
