package com.blstream.myhoard.biz.model;

public class GeoPointDTO {

	private Float lat;
	private Float lng;

	public GeoPointDTO() {
	}

	public GeoPointDTO(Float lat, Float lng) {
		this.lat = lat;
		this.lng = lng;
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

	@Override
	public String toString() {
		return "GeoPointDTO [lat=" + lat + ", lng=" + lng + "]";
	}

}