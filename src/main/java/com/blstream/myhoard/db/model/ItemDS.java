package com.blstream.myhoard.db.model;

import java.util.Date;

public class ItemDS {

	private int id;
	private String name;
	private String description;
	private String location;
	private int quantity;
	private Date createdDate;
	private Date modifiedDate;
	// TODO owner will be set in the future, maybe in the next sprint.
	private String owner; 
	private CollectionDS collection;
	// TODO Media objects list

	public ItemDS() {
	}

	public ItemDS(String name, String description, String location,
			int quantity, Date createdDate, Date modifiedDate, String owner,
			CollectionDS collection) {
		this.name = name;
		this.description = description;
		this.location = location;
		this.quantity = quantity;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.owner = owner;
		this.collection = collection;
	}

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	@Override
	public String toString() {
		return "ItemDS [id=" + id + ", name=" + name + ", description="
				+ description + ", location=" + location + ", quantity="
				+ quantity + ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + ", owner=" + owner + ", collection="
				+ collection + "]";
	}

}
