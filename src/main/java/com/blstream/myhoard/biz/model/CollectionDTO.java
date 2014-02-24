package com.blstream.myhoard.biz.model;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.blstream.myhoard.biz.serializer.RestDateSerializer;

public class CollectionDTO {

	private String id = "0";
	private String name;
	private String description;
	List<String> tags;
	private int items_number;
	@JsonSerialize(using = RestDateSerializer.class)
	private Date created_date;
	@JsonSerialize(using = RestDateSerializer.class)
	private Date modified_date;
	private String owner;

	public CollectionDTO() {
		super();
	}

	public CollectionDTO(String id, String name, String description,
			List<String> tags, int items_number, Date created_date,
			Date modified_date, String owner) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.tags = tags;
		this.items_number = items_number;
		this.created_date = created_date;
		this.modified_date = modified_date;
		this.owner = owner;
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

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public int getItems_number() {
		return items_number;
	}

	public void setItems_number(int items_number) {
		this.items_number = items_number;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getModified_date() {
		return modified_date;
	}

	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((created_date == null) ? 0 : created_date.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + items_number;
		result = prime * result
				+ ((modified_date == null) ? 0 : modified_date.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
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
		CollectionDTO other = (CollectionDTO) obj;
		if (created_date == null) {
			if (other.created_date != null)
				return false;
		} else if (!created_date.equals(other.created_date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (items_number != other.items_number)
			return false;
		if (modified_date == null) {
			if (other.modified_date != null)
				return false;
		} else if (!modified_date.equals(other.modified_date))
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
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CollectionDTO [id=" + id + ", name=" + name + ", description="
				+ description + ", tags=" + tags + ", items_number="
				+ items_number + ", created_date=" + created_date
				+ ", modified_date=" + modified_date + ", owner=" + owner + "]";
	}

}
