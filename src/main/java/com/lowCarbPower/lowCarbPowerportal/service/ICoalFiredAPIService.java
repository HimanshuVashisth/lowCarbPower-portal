package com.lowCarbPower.lowCarbPowerportal.service;

import com.lowCarbPower.lowCarbPowerportal.exception.RestApiException;
import com.lowCarbPower.lowCarbPowerportal.model.PriceModel;
import com.lowCarbPower.lowCarbPowerportal.model.ProductOfferingBlockPrice;

public interface ICoalFiredAPIService {

	public ProductOfferingBlockPrice getSupplierRate(String coalFiredApiUrl) throws RestApiException;
	
	public double calculateEstimatedMonthlyBill(double blockPrice, int providedValueInkWH);
	
	public PriceModel findLatestRates();
}
