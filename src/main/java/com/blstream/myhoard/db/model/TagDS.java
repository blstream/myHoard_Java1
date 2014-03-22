package com.blstream.myhoard.db.model;

import java.util.HashSet;
import java.util.Set;

public class TagDS {

	int id;
	String name;
	Set<CollectionDS> collections = new HashSet<CollectionDS>(0);

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<CollectionDS> getCollections() {
		return this.collections;
	}

	public void setCollections(Set<CollectionDS> collections) {
		this.collections = collections;
	}

	public TagDS() {

	}

	public TagDS(String name) {
		this.id = 0;
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TagDS other = (TagDS) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CollectionDS [id=" + id + ", name=" + name + "]";
	}
}
