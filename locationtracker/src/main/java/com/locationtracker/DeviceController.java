package com.locationtracker;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.locationtracker.dto.Device;
import com.locationtracker.service.DeviceService;

@Controller
public class DeviceController {

	@Autowired
	private DeviceService deviceService;

	@ResponseBody 
	@RequestMapping(value = "/device/{id}", method = RequestMethod.GET)
	public Device getDeviceDetailsById(@PathVariable("id")int deviceId) {
		return deviceService.getDeviceById(deviceId);
	}

	@ResponseBody 
	@RequestMapping(value = "/device/{id}/dates", method = RequestMethod.GET)
	public Device getDeviceLocationBetween(@PathVariable("id")int deviceId,@RequestParam("from") String fromDate,
			@RequestParam("to") String toDate) {
		return deviceService.getDeviceLocationDetailsBetween(deviceId, fromDate, toDate);
	}

	@ResponseBody 
	@RequestMapping(value = "/devices/{type}", method = RequestMethod.GET)
	public List<Device> getDeviceDetailsByType(@PathVariable("type")String type) {
		return deviceService.getDevicesByType(type);
	}
}