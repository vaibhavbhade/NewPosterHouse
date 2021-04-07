package com.swiftdroid.posterhouse.model.delihivary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScanDetail {
	@JsonProperty("ScanDateTime")
	private String scanDateTime;
	@JsonProperty("ScanType")
	private String scanType;
	@JsonProperty("Scan")
	private String scan;
	@JsonProperty("StatusDateTime")
	private String statusDateTime;
	@JsonProperty("ScannedLocation")
	private String scannedLocation;
	@JsonProperty("Instructions")
	private String instructions;
	@JsonProperty("StatusCode")
	private String statusCode;
	
	
	
	@Override
	public String toString() {
		return "ScanDetail [scanDateTime=" + scanDateTime + ", scanType=" + scanType + ", scan=" + scan
				+ ", statusDateTime=" + statusDateTime + ", scannedLocation=" + scannedLocation + ", instructions="
				+ instructions + ", statusCode=" + statusCode + "]";
	}
	public String getScanDateTime() {
		return scanDateTime;
	}
	public void setScanDateTime(String scanDateTime) {
		this.scanDateTime = scanDateTime;
	}
	public String getScanType() {
		return scanType;
	}
	public void setScanType(String scanType) {
		this.scanType = scanType;
	}
	public String getScan() {
		return scan;
	}
	public void setScan(String scan) {
		this.scan = scan;
	}
	public String getStatusDateTime() {
		return statusDateTime;
	}
	public void setStatusDateTime(String statusDateTime) {
		this.statusDateTime = statusDateTime;
	}
	public String getScannedLocation() {
		return scannedLocation;
	}
	public void setScannedLocation(String scannedLocation) {
		this.scannedLocation = scannedLocation;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	
	
}
