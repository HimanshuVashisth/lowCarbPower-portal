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
import com.lowCarbPower.lowCarbPowerportal.service.impl.LoginService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/auth")
public class LoginController {

	private final Logger log = LoggerFactory.getLogger(LoginController.class);
		
    @Autowired
    private LoginService loginService;
        
    @ApiOperation(value = "Customer Login Service")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity<CustomerModel> getCustomerDetailsfromRequest(@RequestBody UserModel usermodel) throws ThirdPartyException, IOException, RestApiException {	
    	log.info("User Authentication service");    	
    	CustomerModel customerModel = loginService.login(usermodel.getUsername());
    	
        if (customerModel != null) {        	
            return ResponseEntity.ok(customerModel);
        }else{
            return ResponseEntity.noContent().build();
        }
    }
}
