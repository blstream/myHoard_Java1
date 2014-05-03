package com.blstream.myhoard.biz.model;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.NotEmpty;

import com.blstream.myhoard.biz.serializer.RestDateSerializer;
import com.blstream.myhoard.biz.serializer.UserSerializer;

public class CollectionDTO {

    private String id;
    @NotEmpty
    private String name;
    private String description;
    List<String> tags;
    @JsonProperty("public")
    private boolean isPublic;
    @JsonProperty("items_number")
    private int itemsNumber;
    @JsonProperty("created_date")
    @JsonSerialize(using = RestDateSerializer.class)
    private Date createdDateClient;
    @JsonProperty("modified_date")
    @JsonSerialize(using = RestDateSerializer.class)
    private Date modifiedDateClient;
    @JsonIgnore
    private Date createdDate;
    @JsonIgnore
    private Date modifiedDate;    
    @JsonSerialize(using = UserSerializer.class)
    private UserDTO owner;

    @JsonIgnore
    private List<ItemDTO> items;

    public CollectionDTO() {
        super();
    }

    public CollectionDTO(String id, String name, String description,
            List<String> tags, boolean isPublic,  int itemsNumber, Date createdDate,
            Date modifiedDate, Date createDateClient,
            Date modifiedDateClient, UserDTO owner) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.isPublic = isPublic;
        this.itemsNumber = itemsNumber;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.createdDateClient = createDateClient;
        this.modifiedDateClient= modifiedDateClient;
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

    public boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(boolean isPublic) {
		this.isPublic = isPublic;
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
    
    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }

	public List<ItemDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemDTO> items) {
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + itemsNumber;
		result = prime * result + (isPublic ? 1 : 0);
		result = prime * result
				+ ((modifiedDate == null) ? 0 : modifiedDate.hashCode());
		result = prime * result
				+ ((modifiedDateClient == null) ? 0 : modifiedDateClient.hashCode());		
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (itemsNumber != other.itemsNumber)
			return false;
		if (isPublic != other.getIsPublic())
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
				+ description + ", tags=" + tags + ", isPublic" + isPublic + ", itemsNumber="
				+ itemsNumber + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", createDateClient=" + createdDateClient
				+ ", modifiedDateClient=" + modifiedDateClient + ", owner=" + owner
				+ ", items=" + items + "]";
	}
}
