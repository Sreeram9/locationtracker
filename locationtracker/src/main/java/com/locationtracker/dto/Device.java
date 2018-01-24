package com.locationtracker.dto;

import java.util.List;

public class Device {

	String _id;
	String type;
	List<Location> locations;
	
	public Device() {
		super();
	}
	public Device(String _id, String type, List<Location> locations) {
		super();
		this._id = _id;
		this.type = type;
		this.locations = locations;
	}
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	
	public String get_Id() {
		return _id;
	}
	public void set_Id(String _id) {
		this._id = _id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Location> getLocations() {
		return locations;
	}
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
	@Override
	public String toString() {
		return "Device [_id=" + _id + ", type=" + type + ", locations=" + locations + "]";
	}
}
