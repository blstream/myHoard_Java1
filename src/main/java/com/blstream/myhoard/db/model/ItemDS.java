package com.blstream.myhoard.db.model;

import java.util.Date;
import java.util.Set;

public class ItemDS {

	private int id;
	private String name;
	private String description;
	private Float lat;
	private Float lng;
	private int quantity;
	private Date createdDate;
	private Date modifiedDate;
	// TODO owner will be set in the future, maybe in the next sprint.
	private String owner;
	private CollectionDS collection;
	private Set<MediaDS> media;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public Float getLng() {
		return lng;
	}

	public void setLng(Float lng) {
		this.lng = lng;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public CollectionDS getCollection() {
		return collection;
	}

	public void setCollection(CollectionDS collection) {
		this.collection = collection;
	}

	public Set<MediaDS> getMedia() {
		return media;
	}

	public void setMedia(Set<MediaDS> media) {
		this.media = media;
	}

}
