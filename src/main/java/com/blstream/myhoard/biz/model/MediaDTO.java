package com.blstream.myhoard.biz.model;

import java.util.Arrays;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.blstream.myhoard.biz.serializer.RestDateSerializer;
import com.blstream.myhoard.db.model.ItemDS;

public class MediaDTO {

	private String id;

	@JsonIgnore
	private byte[] file;

	@JsonIgnore
	private byte[] thumbnail;

	@JsonIgnore
	private ItemDS itemDS;

	@JsonIgnore
	@JsonProperty("created_date")
	@JsonSerialize(using = RestDateSerializer.class)
	private Date createdDate;

	private String url;

	public MediaDTO() {
		super();
	}

	public MediaDTO(String id, byte[] file, byte[] thumbnail, ItemDS itemDS,
			Date createdDate, String url) {
		super();
		this.id = id;
		this.file = file;
		this.thumbnail = thumbnail;
		this.itemDS = itemDS;
		this.createdDate = createdDate;
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public byte[] getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(byte[] thumbnail) {
		this.thumbnail = thumbnail;
	}

	public ItemDS getItemDS() {
		return itemDS;
	}

	public void setItemDS(ItemDS itemDS) {
		this.itemDS = itemDS;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "MediaDTO [id=" + id + ", file=" + Arrays.toString(file)
				+ ", thumbnail=" + Arrays.toString(thumbnail) + ", itemDS="
				+ itemDS + ", createdDate=" + createdDate + ", url=" + url
				+ "]";
	}

}
