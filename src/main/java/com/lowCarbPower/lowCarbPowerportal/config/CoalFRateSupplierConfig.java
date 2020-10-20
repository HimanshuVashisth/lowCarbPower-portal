package com.lowCarbPower.lowCarbPowerportal.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.github.dozermapper.core.Mapper;
import com.lowCarbPower.lowCarbPowerportal.entity.PriceEntity;
import com.lowCarbPower.lowCarbPowerportal.exception.RestApiException;
import com.lowCarbPower.lowCarbPowerportal.model.PriceModel;
import com.lowCarbPower.lowCarbPowerportal.model.ProductOfferingBlockPrice;
import com.lowCarbPower.lowCarbPowerportal.repository.jpa.price.PriceRepository;
import com.lowCarbPower.lowCarbPowerportal.service.impl.CoalFiredAPIService;

@Configuration
@EnableScheduling
public class CoalFRateSupplierConfig {

	private static final Logger log = LoggerFactory.getLogger(CoalFRateSupplierConfig.class);
	
	@Autowired
    private Environment environment;
	
	@Autowired
	private CoalFiredAPIService coalAPIService;
	
	@Autowired
	private Mapper mapper;
	
	@Autowired
	private PriceRepository priceRepository;
	
    @Scheduled(cron = "${cron_expression}")
    public PriceEntity scheduleSupplierRateCronExpression() {
    	String coalFiredApiUrl = environment.getProperty(ConfigProperties.COAL_FIRED_API_URL);
    	PriceEntity priceEntity = new PriceEntity(); 
    	
    	try {
    		ProductOfferingBlockPrice latestBLockOfferPrice = coalAPIService.getSupplierRate(coalFiredApiUrl);
    		PriceModel priceModel = latestBLockOfferPrice.getPrice();    		
    		    		   
    		if(priceModel != null) {
    			String unit = priceModel.getUnit();
    			double price = priceModel.getValue();
    			
    			PriceEntity priceRepoEntity = priceRepository.findFirstByOrderByIdDesc();
    			
    			/**
    			 * Save latest price info returned from api only 
    			 * If there is no initial entry for block price then make one
    			 * 			OR
    			 * If the unit or price is different from the last saved value
    			 * */
    			if(priceRepoEntity == null ||
    					( (priceRepoEntity != null) && (priceRepoEntity.getUnit() != unit) && (priceRepoEntity.getValue() != price) )) {
    	            mapper.map(priceModel, priceEntity);
    	            
    	            priceRepository.save(priceEntity);
    	            log.info("Saved priceEntity with latest price : {} {}", priceEntity.getValue(), priceEntity.getUnit());
    			}    			
    			
    		}
    		            
		} catch (RestApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return priceEntity;
    }
}
