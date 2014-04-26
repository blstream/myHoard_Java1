package com.blstream.myhoard.biz.model;

import com.blstream.myhoard.biz.serializer.RestDateSerializer;
import com.blstream.myhoard.biz.serializer.UserSerializer;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class CommentDTO {

    private String id;
    private String content;
    @JsonProperty("created_date")
    @JsonSerialize(using = RestDateSerializer.class)
    private Date createdDate;
    @JsonSerialize(using = UserSerializer.class)
    private UserDTO owner;
    private String collection;
    @JsonIgnore
    private CollectionDTO collectionDTO;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    public CollectionDTO getCollectionDTO() {
        return collectionDTO;
    }

    public void setCollectionDTO(CollectionDTO collectionDTO) {
        this.collectionDTO = collectionDTO;
    }
}
