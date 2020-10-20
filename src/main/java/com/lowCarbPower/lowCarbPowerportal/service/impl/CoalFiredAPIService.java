package com.lowCarbPower.lowCarbPowerportal.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.github.dozermapper.core.Mapper;
import com.lowCarbPower.lowCarbPowerportal.audit.DomainConstants;
import com.lowCarbPower.lowCarbPowerportal.entity.PriceEntity;
import com.lowCarbPower.lowCarbPowerportal.exception.RestApiException;
import com.lowCarbPower.lowCarbPowerportal.model.PriceModel;
import com.lowCarbPower.lowCarbPowerportal.model.ProductOfferingBlockPrice;
import com.lowCarbPower.lowCarbPowerportal.repository.jpa.price.PriceRepository;
import com.lowCarbPower.lowCarbPowerportal.service.ICoalFiredAPIService;

@Service
public class CoalFiredAPIService implements ICoalFiredAPIService {

	private static final Logger log = LoggerFactory.getLogger(CoalFiredAPIService.class);
	
    @Autowired
    private RestTemplate restTemplate;
    
	@Autowired
	private PriceRepository priceRepository;
	
	@Autowired
	private Mapper mapper;
    
	@Override
	public ProductOfferingBlockPrice getSupplierRate(String coalFiredApiUrl) throws RestApiException {

        // headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type",MediaType.APPLICATION_JSON_VALUE);
        // post data
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(data, headers);       	        
        ResponseEntity<ProductOfferingBlockPrice> responseEntity = restTemplate.exchange(coalFiredApiUrl, 
        																			HttpMethod.GET, 
        																			request, 
        																			ProductOfferingBlockPrice.class);
                 
        
        ProductOfferingBlockPrice rateUpdate = (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null) ? 
        		responseEntity.getBody() : null;        		

        log.info("Response for sending block price request: statusCode={} block price={}{}", responseEntity.getStatusCode(), responseEntity.getBody().getPrice().getValue(), responseEntity.getBody().getPrice().getUnit());			        
        		
        return rateUpdate;
	}

	@Override
	public double calculateEstimatedMonthlyBill(double blockPrice, int provideValueInkWH) {
		double estimatedMonthlyCost = ( ( (blockPrice / DomainConstants._256kWh_ENERGY_BLOCK) / DomainConstants.MONTH_DAYS) * provideValueInkWH) * DomainConstants.MONTH_DAYS; 
		BigDecimal bdMonthlyCost = new BigDecimal(estimatedMonthlyCost).setScale(2, RoundingMode.HALF_UP);
		
		return bdMonthlyCost.doubleValue();
	}

	@Override
	public PriceModel findLatestRates() {
		PriceModel priceModel = new PriceModel();  
		PriceEntity entity = priceRepository.findFirstByOrderByIdDesc();
		
		if(entity != null)
			mapper.map(entity, priceModel);
		
		return priceModel;		
	}	

}
