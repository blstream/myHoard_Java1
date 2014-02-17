package com.myhoard.model.dao;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "Collections")
public class CollectionDS {
	
    @Id
    @Column(name="id")
    @GeneratedValue
	private int id;
    
    @Column(name="name")
	private String name;
	
    @Column(name="description")
	private String description;
	
    @Column(name="tags")
	private String tags;
	
    @Column(name="items_number")
	private int itemsNumber;
	
    @Column(name="created_date")
	private Timestamp createdDate;
	
    @Column(name="modified_date")
	private Timestamp modifiedDate;
	
    @Column(name="owner")
	private String owner;
	
	
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

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public int getItemsNumber() {
		return itemsNumber;
	}

	public void setItemsNumber(int itemsNumber) {
		this.itemsNumber = itemsNumber;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	
	
}
