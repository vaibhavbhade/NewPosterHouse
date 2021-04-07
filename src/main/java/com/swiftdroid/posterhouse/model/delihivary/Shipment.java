package com.swiftdroid.posterhouse.model.delihivary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Shipment {
	
	private String add;
    private String phone;
    
    @JsonProperty("payment_mode")
    private String paymentMode;
    
    private String name;
    private String pin;
    private String order;
    
    
    
	public Shipment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Shipment(String add, String phone, String paymentMode, String name, String pin, String order) {
		super();
		this.add = add;
		this.phone = phone;
		this.paymentMode = paymentMode;
		this.name = name;
		this.pin = pin;
		this.order = order;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
    
    
}