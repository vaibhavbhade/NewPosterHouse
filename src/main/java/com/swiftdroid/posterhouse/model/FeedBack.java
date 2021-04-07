package com.swiftdroid.posterhouse.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FeedBack {

	@Id
	@GeneratedValue
	private Long id;
	
	private String username;
	
	private String email;

	private String PhoneNo;
	
	private String feedback;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhoneNo() {
		return PhoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		PhoneNo = phoneNo;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public FeedBack() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
	
}
