package com.lowCarbPower.lowCarbPowerportal.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lowCarbPower.lowCarbPowerportal.config.CoalFRateSupplierConfig;
import com.lowCarbPower.lowCarbPowerportal.entity.PriceEntity;
import com.lowCarbPower.lowCarbPowerportal.exception.RestApiException;
import com.lowCarbPower.lowCarbPowerportal.exception.ThirdPartyException;
import com.lowCarbPower.lowCarbPowerportal.model.PriceModel;
import com.lowCarbPower.lowCarbPowerportal.service.impl.CoalFiredAPIService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/estimate")
public class BillingEstimationController {

	private final Logger log = LoggerFactory.getLogger(BillingEstimationController.class);
	
    @Autowired
    private CoalFiredAPIService coalFiredService;
	
    @ApiOperation(value = "Monthly estimated bill")
    @RequestMapping(value = "/monthly",method = RequestMethod.GET)
    public ResponseEntity<PriceModel> getCampaignDetailsfromRequest(@RequestParam(value="provideValueInkWH") int provideValueInkWH,
    																@RequestParam(value="customerId") Long customerId) 
    																		throws ThirdPartyException, IOException, RestApiException {	
    	log.info("Calculate estimated monthly bill");    	
    	PriceModel priceModel = coalFiredService.findLatestRates();
    	
        if (priceModel != null) {        	 
        	double estimatedMonthlyBill = coalFiredService.calculateEstimatedMonthlyBill(priceModel.getValue(), provideValueInkWH);        	
        	log.info("CustomerId: {} Price of {} kWh is: {} {}", customerId, provideValueInkWH, estimatedMonthlyBill, priceModel.getUnit());
        	
        	priceModel.setUnit(priceModel.getUnit());
        	priceModel.setValue(estimatedMonthlyBill);
        	
            return ResponseEntity.ok(priceModel);
        }else{
            return ResponseEntity.noContent().build();
        }
    }
	
}
