package com.lowCarbPower.lowCarbPowerportal.model;

public class UnitOfMeasure {

	int amount;
	String units;
	
	public UnitOfMeasure() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UnitOfMeasure(int amount, String units) {
		super();
		this.amount = amount;
		this.units = units;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}
	
	
}
