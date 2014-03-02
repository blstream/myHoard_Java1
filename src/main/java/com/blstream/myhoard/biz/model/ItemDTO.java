package com.blstream.myhoard.biz.model;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.blstream.myhoard.biz.serializer.RestDateSerializer;

public class ItemDTO {

	private String id;
	private String name;
	private String description;
	private GeoPointDTO location;
	private int quantity;
	// TODO media
	private List<String> media;
	@JsonProperty("created_date")
	@JsonSerialize(using = RestDateSerializer.class)
	private Date createdDate;
	@JsonProperty("modified_date")
	@JsonSerialize(using = RestDateSerializer.class)
	private Date modifiedDate;
	// TODO owner will be set in the future
	private String owner;
	private String collection;

	public ItemDTO() {
	}

	public ItemDTO(String id, String name, String description, GeoPointDTO location, int quantity, List<String> media, Date createdDate,
			Date modifiedDate, String owner, String collection) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.location = location;
		this.quantity = quantity;
		this.media = media;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.owner = owner;
		this.collection = collection;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public GeoPointDTO getLocation() {
		return location;
	}

	public void setLocation(GeoPointDTO location) {
		this.location = location;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<String> getMedia() {
		return media;
	}

	public void setMedia(List<String> media) {
		this.media = media;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

}