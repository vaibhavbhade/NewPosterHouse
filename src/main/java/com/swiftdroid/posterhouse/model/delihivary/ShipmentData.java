package com.swiftdroid.posterhouse.model.delihivary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShipmentData {
	@JsonProperty("Shipment")
	private Shipments shipment;
	
	

	

	@Override
	public String toString() {
		return "ShipmentData [shipment=" + shipment + "]";
	}

	public Shipments getShipment() {
		return shipment;
	}

	public void setShipment(Shipments shipment) {
		this.shipment = shipment;
	}
	
}