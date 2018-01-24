package com.locationtracker.service;

import java.util.List;

import com.locationtracker.dto.Device;

public interface DeviceService {

	Device getDeviceById(int deviceId);
	List<Device> getDevicesByType(String type);
	Device  getDeviceLocationDetailsBetween(int deviceId,String fromDate,String toDate);
}
