package com.swiftdroid.posterhouse.model.delihivary;

import java.util.Arrays;

public class Packages {
	
	private String status;
	private String client;
	private String sort_code; 
	private String[] remarks; 
	private String waybill; 
	private String cod_amount;
	private String payment; 
	private boolean serviceable;
	private String refnum;
	
	
	
	public Packages() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Packages(String status, String client, String sort_code, String[] remarks, String waybill, String cod_amount,
			String payment, boolean serviceable, String refnum) {
		super();
		this.status = status;
		this.client = client;
		this.sort_code = sort_code;
		this.remarks = remarks;
		this.waybill = waybill;
		this.cod_amount = cod_amount;
		this.payment = payment;
		this.serviceable = serviceable;
		this.refnum = refnum;
	}
	
	@Override
	public String toString() {
		return "Packages [status=" + status + ", client=" + client + ", sort_code=" + sort_code + ", remarks="
				+ Arrays.toString(remarks) + ", waybill=" + waybill + ", cod_amount=" + cod_amount + ", payment="
				+ payment + ", serviceable=" + serviceable + ", refnum=" + refnum + "]";
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getSort_code() {
		return sort_code;
	}
	public void setSort_code(String sort_code) {
		this.sort_code = sort_code;
	}
	public String[] getRemarks() {
		return remarks;
	}
	public void setRemarks(String[] remarks) {
		this.remarks = remarks;
	}
	public String getWaybill() {
		return waybill;
	}
	public void setWaybill(String waybill) {
		this.waybill = waybill;
	}
	public String getCod_amount() {
		return cod_amount;
	}
	public void setCod_amount(String cod_amount) {
		this.cod_amount = cod_amount;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public boolean isServiceable() {
		return serviceable;
	}
	public void setServiceable(boolean serviceable) {
		this.serviceable = serviceable;
	}
	public String getRefnum() {
		return refnum;
	}
	public void setRefnum(String refnum) {
		this.refnum = refnum;
	}
	
	

}