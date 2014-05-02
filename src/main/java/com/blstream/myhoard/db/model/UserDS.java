package com.blstream.myhoard.db.model;

import java.util.Set;

public class UserDS {

    private int id;
    private String username;
    private String email;
    private String password;
    private Set<ItemDS> items;
    private Set<CollectionDS> collections;
    private Set<CollectionDS> favoriteCollections;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<ItemDS> getItems() {
        return items;
    }

    public void setItems(Set<ItemDS> items) {
        this.items = items;
    }

    public Set<CollectionDS> getCollections() {
        return collections;
    }

    public void setCollections(Set<CollectionDS> collections) {
        this.collections = collections;
    }

	public Set<CollectionDS> getFavoriteCollections() {
		return favoriteCollections;
	}

	public void setFavoriteCollections(Set<CollectionDS> favoriteCollections) {
		this.favoriteCollections = favoriteCollections;
	}

}
