package com.lowCarbPower.lowCarbPowerportal.model;

public class CustomerModel {

	private Long customerId;
	
	private String username;
	
	private String password;

	public CustomerModel(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public CustomerModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerModel(Long customerId, String username, String password) {
		super();
		this.customerId = customerId;
		this.username = username;
		this.password = password;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
