package com.swiftdroid.posterhouse.model.delihivary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Status {
	@JsonProperty("Status")
	private String status;
	@JsonProperty("StatusLocation")
	private String statusLocation;
	@JsonProperty("StatusDateTime")
	private String statusDateTime;
	@JsonProperty("RecievedBy")
	private String recievedBy;
	@JsonProperty("Instructions")
	private String instructions;
	@JsonProperty("StatusType")
	private String statusType;
	@JsonProperty("StatusCode")
	private String statusCode;
	
	
	
	@Override
	public String toString() {
		return "Status [status=" + status + ", statusLocation=" + statusLocation + ", statusDateTime=" + statusDateTime
				+ ", recievedBy=" + recievedBy + ", instructions=" + instructions + ", statusType=" + statusType
				+ ", statusCode=" + statusCode + "]";
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusLocation() {
		return statusLocation;
	}
	public void setStatusLocation(String statusLocation) {
		this.statusLocation = statusLocation;
	}
	public String getStatusDateTime() {
		return statusDateTime;
	}
	public void setStatusDateTime(String statusDateTime) {
		this.statusDateTime = statusDateTime;
	}
	public String getRecievedBy() {
		return recievedBy;
	}
	public void setRecievedBy(String recievedBy) {
		this.recievedBy = recievedBy;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public String getStatusType() {
		return statusType;
	}
	public void setStatusType(String statusType) {
		this.statusType = statusType;

	}
	
	
	
}
