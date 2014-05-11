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
    private Date createdDateClient;
    private Date modifiedDateClient;    
    private UserDS owner;
    private CollectionDS collection;
    private Set<MediaDS> media;
    private boolean forSale;

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
    
    public boolean isForSale() {
		return forSale;
	}

	public void setForSale(boolean forSale) {
		this.forSale = forSale;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((collection == null) ? 0 : collection.hashCode());
        result = prime * result
                + ((createdDate == null) ? 0 : createdDate.hashCode());
        result = prime * result
                + ((createdDateClient == null) ? 0 : createdDateClient.hashCode());
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime * result + id;
        result = prime * result + ((lat == null) ? 0 : lat.hashCode());
        result = prime * result + ((lng == null) ? 0 : lng.hashCode());
        result = prime * result + ((media == null) ? 0 : media.hashCode());
        result = prime * result
                + ((modifiedDate == null) ? 0 : modifiedDate.hashCode());
        result = prime * result
                + ((modifiedDateClient == null) ? 0 : modifiedDateClient.hashCode());    
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((owner == null) ? 0 : owner.hashCode());
        result = prime * result + quantity;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ItemDS other = (ItemDS) obj;
        if (collection == null) {
            if (other.collection != null) {
                return false;
            }
        } else if (!collection.equals(other.collection)) {
            return false;
        }
        if (createdDate == null) {
            if (other.createdDate != null) {
                return false;
            }
        } else if (!createdDate.equals(other.createdDate)) {
            return false;
        }
        if (createdDateClient == null) {
            if (other.createdDateClient != null) {
                return false;
            }
        } else if (!createdDateClient.equals(other.createdDateClient)) {
            return false;
        }        
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        if (lat == null) {
            if (other.lat != null) {
                return false;
            }
        } else if (!lat.equals(other.lat)) {
            return false;
        }
        if (lng == null) {
            if (other.lng != null) {
                return false;
            }
        } else if (!lng.equals(other.lng)) {
            return false;
        }
        if (media == null) {
            if (other.media != null) {
                return false;
            }
        } else if (!media.equals(other.media)) {
            return false;
        }
        if (modifiedDate == null) {
            if (other.modifiedDate != null) {
                return false;
            }
        } else if (!modifiedDate.equals(other.modifiedDate)) {
            return false;
        }
        if (modifiedDateClient == null) {
            if (other.modifiedDateClient != null) {
                return false;
            }
        } else if (!modifiedDateClient.equals(other.modifiedDateClient)) {
            return false;
        }        
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (owner == null) {
            if (other.owner != null) {
                return false;
            }
        } else if (!owner.equals(other.owner)) {
            return false;
        }
        if (quantity != other.quantity) {
            return false;
        }
        return true;
    }

}
