package com.blstream.myhoard.biz.model;

import java.util.Arrays;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;

public class MediaDTO {

	private String id = "0";
	private String url;
	@JsonIgnore
	private byte[] file;
	@JsonIgnore
	private byte[] thumbnail;
	@JsonIgnore
	private Date createdDate;

	public MediaDTO() {
	}

	public MediaDTO(String id) {
		this.id = id;
	}

	public MediaDTO(String id, byte[] file, byte[] thumbnail, Date createdDate,
			String url) {
		this.id = id;
		this.file = file;
		this.thumbnail = thumbnail;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + Arrays.hashCode(file);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Arrays.hashCode(thumbnail);
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MediaDTO other = (MediaDTO) obj;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (!Arrays.equals(file, other.file))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (!Arrays.equals(thumbnail, other.thumbnail))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MediaDTO [id=" + id + ", url=" + url + ", file="
				+ Arrays.toString(file) + ", thumbnail="
				+ Arrays.toString(thumbnail) + ", createdDate=" + createdDate
				+ "]";
	}

}
