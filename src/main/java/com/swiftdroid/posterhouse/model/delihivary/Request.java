package com.swiftdroid.posterhouse.model.delihivary;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {

	@JsonProperty("shipments")
	private List<Shipment> shipments = new ArrayList();
	
	@JsonProperty("pickup_location")
	private PickupLocation pickupLocation;
	
	

	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Request(List<Shipment> shipments, PickupLocation pickupLocation) {
		super();
		this.shipments = shipments;
		this.pickupLocation = pickupLocation;
	}

	public List<Shipment> getShipments() {
		return shipments;
	}

	public void setShipments(List<Shipment> shipments) {
		this.shipments = shipments;
	}

	public PickupLocation getPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(PickupLocation pickupLocation) {
		this.pickupLocation = pickupLocation;
	}
	
}
