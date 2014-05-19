package com.blstream.myhoard.biz.model;

import java.util.Date;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.blstream.myhoard.biz.serializer.RestDateSerializer;
import com.blstream.myhoard.biz.serializer.UserSerializer;

public class ItemDTO {

    private String id;
    private String name;
    private String description;
    private GeoPointDTO location;
    private Integer quantity;
    private Set<MediaDTO> media;
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
    private String collection;
    @JsonProperty("for_sale")
    private Boolean forSale;

    public ItemDTO() {
    }

    public ItemDTO(String id, String name, String description,
            GeoPointDTO location, Integer quantity, Set<MediaDTO> media,
            Date createdDate, Date modifiedDate, UserDTO owner, String collection) {
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Set<MediaDTO> getMedia() {
        return media;
    }

    public void setMedia(Set<MediaDTO> media) {
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

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public Boolean isForSale() {
        return forSale;
    }

    public void setForSale(Boolean forSale) {
        this.forSale = forSale;
    }
    
}
