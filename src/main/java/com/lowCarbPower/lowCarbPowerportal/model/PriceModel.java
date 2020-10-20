package com.lowCarbPower.lowCarbPowerportal.model;

public class PriceModel {

	String unit;
	double value;
	
	public PriceModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PriceModel(String unit, double value) {
		super();
		this.unit = unit;
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}
