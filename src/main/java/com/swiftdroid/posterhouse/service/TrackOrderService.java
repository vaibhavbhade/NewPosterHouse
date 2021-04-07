package com.swiftdroid.posterhouse.service;

import com.swiftdroid.posterhouse.model.delihivary.ShipmentTracker;

public interface TrackOrderService {
	

	ShipmentTracker getTrackingDetails(Long trackingId);

}
