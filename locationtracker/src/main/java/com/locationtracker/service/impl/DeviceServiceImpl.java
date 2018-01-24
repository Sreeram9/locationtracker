package com.locationtracker.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.locationtracker.dao.DeviceDAO;
import com.locationtracker.dto.Device;
import com.locationtracker.exception.LocationTrackerRuntimeException;
import com.locationtracker.service.DeviceService;

public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private DeviceDAO deviceDAO;
	private static final DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	@Override
	public Device getDeviceById(int deviceId) {
		return deviceDAO.getDeviceById(deviceId);
	}

	@Override
	public List<Device> getDevicesByType(String type) {
		return deviceDAO.getDevicesByType(type);
	}

	@Override
	public Device getDeviceLocationDetailsBetween(int deviceId,String fromDate, String toDate) {
		Date from;
		Date to;
		try {
			from = formatter.parse(fromDate);
			to = formatter.parse(toDate);
		} catch (ParseException e) {
			throw new LocationTrackerRuntimeException("Invalid Dates");
		}
		return deviceDAO.getDeviceLocationDetailsBetween(deviceId,from, to);
	}

}
