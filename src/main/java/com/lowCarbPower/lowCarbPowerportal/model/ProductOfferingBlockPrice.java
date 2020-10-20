package com.lowCarbPower.lowCarbPowerportal.model;

public class ProductOfferingBlockPrice {
	
	String id;
	String href;
	String description;
	String lifesycleStatus;
	String name;
	String priceType;
	String version;
	PriceModel price;
	UnitOfMeasure unitOfMeasure;
	ValidFor validFor;
	
	public ProductOfferingBlockPrice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductOfferingBlockPrice(String id, String href, String description, String lifesycleStatus, String name,
			String priceType, String version, PriceModel price, UnitOfMeasure unitOfMeasure, ValidFor validFor) {
		super();
		this.id = id;
		this.href = href;
		this.description = description;
		this.lifesycleStatus = lifesycleStatus;
		this.name = name;
		this.priceType = priceType;
		this.version = version;
		this.price = price;
		this.unitOfMeasure = unitOfMeasure;
		this.validFor = validFor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLifesycleStatus() {
		return lifesycleStatus;
	}

	public void setLifesycleStatus(String lifesycleStatus) {
		this.lifesycleStatus = lifesycleStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public PriceModel getPrice() {
		return price;
	}

	public void setPrice(PriceModel price) {
		this.price = price;
	}

	public UnitOfMeasure getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public ValidFor getValidFor() {
		return validFor;
	}

	public void setValidFor(ValidFor validFor) {
		this.validFor = validFor;
	}
}
