package com.lowCarbPower.lowCarbPowerportal.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lowCarbPower.lowCarbPowerportal.exception.RestApiException;
import com.lowCarbPower.lowCarbPowerportal.exception.ThirdPartyException;
import com.lowCarbPower.lowCarbPowerportal.model.CustomerModel;
import com.lowCarbPower.lowCarbPowerportal.model.UserModel;
import com.lowCarbPower.lowCarbPowerportal.service.impl.RegistrationService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/register")
public class RegistrationController {

	
	private final Logger log = LoggerFactory.getLogger(RegistrationController.class);
	
    @Autowired
    private RegistrationService registrationService;
	
    @ApiOperation(value = "Registration Service")
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public ResponseEntity<CustomerModel> registerUser(@RequestBody UserModel userModel) throws ThirdPartyException, IOException, RestApiException {
    	log.info("New User Registration service");    	
    	CustomerModel customerModel = registrationService.register(userModel);
    	
        if (customerModel != null) {        	
            return ResponseEntity.ok(customerModel);
        }else{
            return ResponseEntity.noContent().build();
        }
    }
}
