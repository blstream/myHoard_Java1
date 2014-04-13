package com.blstream.myhoard.db.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CollectionDS {

	private int id;
	private String name;
	private String description;
	private Set<TagDS> tags = new HashSet<TagDS>(0);
	private int itemsNumber;
	private Date createdDate;
	private Date modifiedDate;
	private Date createdDateClient;
	private Date modifiedDateClient;
	private UserDS owner;
	private Set<ItemDS> items;

	public CollectionDS() {
	}

	public CollectionDS(String name, String description, Set<TagDS> tags,
			int itemsNumber, Date createdDate, Date modifiedDate, Date createdDateClient,
			Date modifiedDateClient, UserDS owner) {

		this.id = 0;
		this.name = name;
		this.description = description;
		this.tags = tags;
		this.itemsNumber = itemsNumber;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.setCreatedDateClient(createdDateClient);
		this.setModifiedDateClient(modifiedDateClient);		
		this.owner = owner;
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

	public Set<TagDS> getTags() {
		return tags;
	}

	public void setTags(Set<TagDS> tags) {
		this.tags = tags;
	}

	public int getItemsNumber() {
		return itemsNumber;
	}

	public void setItemsNumber(int itemsNumber) {
		this.itemsNumber = itemsNumber;
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

	public Date getCreatedDateClient() {
		return createdDateClient;
	}

	public void setCreatedDateClient(Date createdDateClient) {
		this.createdDateClient = createdDateClient;
	}

	public Date getModifiedDateClient() {
		return modifiedDateClient;
	}

	public void setModifiedDateClient(Date modifiedDateClient) {
		this.modifiedDateClient = modifiedDateClient;
	}

	public UserDS getOwner() {
		return owner;
	}

	public void setOwner(UserDS owner) {
		this.owner = owner;
	}

	public Set<ItemDS> getItems() {
		return items;
	}

	public void setItems(Set<ItemDS> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result
				+ ((createdDateClient == null) ? 0 : createdDateClient.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + itemsNumber;
		result = prime * result + ((description == null) ? 0 : tags.hashCode());
		result = prime * result
				+ ((modifiedDate == null) ? 0 : modifiedDate.hashCode());
		result = prime * result
				+ ((modifiedDateClient == null) ? 0 : modifiedDateClient.hashCode());		
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
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
		CollectionDS other = (CollectionDS) obj;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (createdDateClient == null) {
			if (other.createdDateClient != null)
				return false;
		} else if (!createdDateClient.equals(other.createdDateClient))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (itemsNumber != other.itemsNumber)
			return false;
		if (modifiedDate == null) {
			if (other.modifiedDate != null)
				return false;
		} else if (!modifiedDate.equals(other.modifiedDate))
			return false;
		if (modifiedDateClient == null) {
			if (other.modifiedDateClient != null)
				return false;
		} else if (!modifiedDateClient.equals(other.modifiedDateClient))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CollectionDS [id=" + id + ", name=" + name + ", description="
				+ description + ", itemsNumber=" + itemsNumber
				+ ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + ", createdDateClient=" + createdDateClient 
				+ ", modifiedDateClient=" + modifiedDateClient + ", owner=" + owner + "]";
	}

}
