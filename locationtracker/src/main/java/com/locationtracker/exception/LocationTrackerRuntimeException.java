package com.locationtracker.exception;

public class LocationTrackerRuntimeException extends RuntimeException {
  
	private static final long serialVersionUID = 1L;

	public LocationTrackerRuntimeException(String string) {
		super(string);
	}

	public LocationTrackerRuntimeException(String string, Exception e) {
	    super(string,e);
	}

}
