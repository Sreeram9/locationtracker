package com.locationtracker.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Location {

	String latitude;
	String longitude;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss",timezone="IST")
	@JsonDeserialize(using = MongoDateConverter.class)
	Date timestamp;

	public Location() {
		super();
	}

	public Location(String latitude, String longitude, Date timestamp) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.timestamp = timestamp;
	}


	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimeStamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Location [latitude=" + latitude + ", longitude=" + longitude + ", timestamp=" + timestamp + "]";
	}
}
