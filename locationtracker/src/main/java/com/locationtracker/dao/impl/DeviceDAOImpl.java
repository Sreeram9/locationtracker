package com.locationtracker.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.locationtracker.dao.DeviceDAO;
import com.locationtracker.dto.Device;
import com.locationtracker.dto.Location;
import com.locationtracker.exception.LocationTrackerRuntimeException;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DeviceDAOImpl implements DeviceDAO {
	private static final MongoClient mongoClient = new MongoClient();
	private static final MongoDatabase database = mongoClient.getDatabase("location_tracker");
	private static final MongoCollection<Document> collection = database.getCollection("device");

	public Device getDeviceById(int deviceId){
		ObjectMapper mapper = new ObjectMapper();
		Document document = new Document().append("_id",deviceId);
		try {
			return mapper.readValue(collection.find(document).first().toJson(),Device.class);
		} catch (IOException e) {
			throw new LocationTrackerRuntimeException("Error while parsing Device docs",e);
		}
	}

	public List<Device> getDevicesByType(String type){
		ObjectMapper mapper = new ObjectMapper();
		Document document = new Document().append("type",type);
		Iterator<Document> iterator = collection.find(document).iterator();
		List<Device> devices= new ArrayList<>();
		while(iterator.hasNext()){
			try {
				devices.add(mapper.readValue(iterator.next().toJson(), Device.class));
			} catch (IOException e) {
				throw new LocationTrackerRuntimeException("Error while parsing Device docs",e);
			}
		}
		return devices;
	}
	
	public Device getDeviceLocationDetailsBetween(int deviceId ,Date fromDate,Date toDate){
		Device device =  getDeviceById(deviceId);
		device.setLocations(getFilteredLocations(device.getLocations(),fromDate,toDate));
		return device;
	}

	private List<Location> getFilteredLocations(List<Location> inputLocations,Date fromDate,Date toDate){
		List<Location> filteredLocations = new ArrayList<>();
		for(Location location:inputLocations) {
			if(location.getTimestamp().compareTo(fromDate) > 0 && location.getTimestamp().compareTo(toDate) < 0){
				filteredLocations.add(location);
			}
		}
		return filteredLocations;
	}

	public static void main(String[] args) {
		DeviceDAO deviceDAO = new DeviceDAOImpl();
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(2018, 0,20,00,20,2);
		calendar1.set(Calendar.MILLISECOND,0);
		calendar1.setTimeZone(TimeZone.getTimeZone("IST"));

		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(2018, 0,20,13,40,2);
		calendar2.set(Calendar.MILLISECOND,0);
		calendar2.setTimeZone(TimeZone.getTimeZone("IST"));
		System.out.println(deviceDAO.getDeviceLocationDetailsBetween(1,calendar1.getTime() ,calendar2.getTime()));
	}
}
