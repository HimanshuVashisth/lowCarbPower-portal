package com.lowCarbPower.lowCarbPowerportal.model;

public class UserModel {

	private String username;
	
	private String password;

	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserModel(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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
