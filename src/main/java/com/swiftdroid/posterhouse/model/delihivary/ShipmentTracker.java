package com.swiftdroid.posterhouse.model.delihivary;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShipmentTracker {
	
	@JsonProperty("ShipmentData")
	private ShipmentData shipmentData[];
	
	
	
	@Override
	public String toString() {
		return "ShipmentTracker [ShipmentData=" + Arrays.toString(shipmentData) + "]";
	}

	public ShipmentData[] getShipmentData() {
		return shipmentData;
	}

	public void setShipmentData(ShipmentData[] shipmentData) {
		this.shipmentData = shipmentData;
	} 
	
}
