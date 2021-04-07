package com.swiftdroid.posterhouse.model.delihivary;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Shipments {
	@JsonProperty("Origin")
	private String origin;
	@JsonProperty("Status")
	private Status status;
	@JsonProperty("PickUpDate")
	private String pickUpDate;
	@JsonProperty("ChargedWeight")
	private String chargedWeight;
	@JsonProperty("OrderType")
	private String orderType;
	@JsonProperty("Destination")
	private String destination;
//	@JsonProperty("Consignee")
//	private Consignee consignee;
	@JsonProperty("ReferenceNo")
	private String referenceNo;
	@JsonProperty("ReturnedDate")
	private String returnedDate;
	@JsonProperty("DestRecieveDate")
	private String destRecieveDate;
	@JsonProperty("OriginRecieveDate")
	private String originRecieveDate;
	@JsonProperty("OutDestinationDate")
	private String outDestinationDate;
	@JsonProperty("CODAmount")
	private String codAmount;
	@JsonProperty("FirstAttemptDate")
	private String firstAttemptDate;
	@JsonProperty("ReverseInTransit")
	private String reverseInTransit;
	@JsonProperty("Scans")
	private Scans scans[];
	@JsonProperty("SenderName")
	private String senderName;
	@JsonProperty("AWB")
	private String awb;
	@JsonProperty("DispatchCount")
	private String dispatchCount;
	@JsonProperty("InvoiceAmount")
	private String invoiceAmount;
	
	

	@Override
	public String toString() {
		return "Shipments [origin=" + origin + ", status=" + status + ", pickUpDate=" + pickUpDate
				+ ", chargedWeight=" + chargedWeight + ", orderType=" + orderType + ", destination=" + destination
				+ ", referenceNo=" + referenceNo + ", returnedDate=" + returnedDate + ", destRecieveDate="
				+ destRecieveDate + ", originRecieveDate=" + originRecieveDate + ", outDestinationDate="
				+ outDestinationDate + ", codAmount=" + codAmount + ", firstAttemptDate=" + firstAttemptDate
				+ ", reverseInTransit=" + reverseInTransit + ", scans=" + Arrays.toString(scans) + ", senderName="
				+ senderName + ", awb=" + awb + ", dispatchCount=" + dispatchCount + ", invoiceAmount="
				+ invoiceAmount + "]";
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getPickUpDate() {
		return pickUpDate;
	}
	public void setPickUpDate(String pickUpDate) {
		this.pickUpDate = pickUpDate;
	}
	public String getChargedWeight() {
		return chargedWeight;
	}
	public void setChargedWeight(String chargedWeight) {
		this.chargedWeight = chargedWeight;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
//	public Consignee getConsignee() {
//		return consignee;
//	}
//	public void setConsignee(Consignee consignee) {
//		this.consignee = consignee;
//	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public String getReturnedDate() {
		return returnedDate;
	}
	public void setReturnedDate(String returnedDate) {
		this.returnedDate = returnedDate;
	}
	public String getDestRecieveDate() {
		return destRecieveDate;
	}
	public void setDestRecieveDate(String destRecieveDate) {
		this.destRecieveDate = destRecieveDate;
	}
	public String getOriginRecieveDate() {
		return originRecieveDate;
	}
	public void setOriginRecieveDate(String originRecieveDate) {
		this.originRecieveDate = originRecieveDate;
	}
	public String getOutDestinationDate() {
		return outDestinationDate;
	}
	public void setOutDestinationDate(String outDestinationDate) {
		this.outDestinationDate = outDestinationDate;
	}
	public String getCodAmount() {
		return codAmount;
	}
	public void setCodAmount(String codAmount) {
		this.codAmount = codAmount;
	}
	public String getFirstAttemptDate() {
		return firstAttemptDate;
	}
	public void setFirstAttemptDate(String firstAttemptDate) {
		this.firstAttemptDate = firstAttemptDate;
	}
	public String getReverseInTransit() {
		return reverseInTransit;
	}
	public void setReverseInTransit(String reverseInTransit) {
		this.reverseInTransit = reverseInTransit;
	}
	public Scans[] getScans() {
		return scans;
	}
	public void setScans(Scans[] scans) {
		this.scans = scans;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getAwb() {
		return awb;
	}
	public void setAwb(String awb) {
		this.awb = awb;
	}
	public String getDispatchCount() {
		return dispatchCount;
	}
	public void setDispatchCount(String dispatchCount) {
		this.dispatchCount = dispatchCount;
	}
	public String getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	
	

}
