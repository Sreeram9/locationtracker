package com.locationtracker.dao;

import java.util.Date;
import java.util.List;

import com.locationtracker.dto.Device;

public interface DeviceDAO {

	Device getDeviceById(int deviceId);
	List<Device> getDevicesByType(String type);
	Device getDeviceLocationDetailsBetween(int deviceId,Date fromDate,Date toDate);
	
}
