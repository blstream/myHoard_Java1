package com.blstream.myhoard.db.model;

import java.util.Date;

public class CommentDS {

    private int id;
    private String content;
    private Date createdDate;
    private Date modifiedDate;
    private Date createdDateClient;
    private Date modifiedDateClient;
    private UserDS owner;
    private CollectionDS collection;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
