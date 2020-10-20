package com.lowCarbPower.lowCarbPowerportal.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.dozermapper.core.Mapper;
import com.lowCarbPower.lowCarbPowerportal.entity.CustomerEntity;
import com.lowCarbPower.lowCarbPowerportal.model.CustomerModel;
import com.lowCarbPower.lowCarbPowerportal.repository.jpa.customer.CustomerRepository;
import com.lowCarbPower.lowCarbPowerportal.service.ILoginService;

@Service
public class LoginService implements ILoginService {

	private static final Logger log = LoggerFactory.getLogger(LoginService.class);
	
	@Autowired
	private CustomerRepository customerRepository; 
	
	@Autowired
	private Mapper mapper;
	
	@Override
	public CustomerModel login(String username) {
		CustomerModel customerModel = new CustomerModel();
		CustomerEntity customerEntity = customerRepository.findByUsername(username);
		
		if(customerEntity != null)
			mapper.map(customerEntity,customerModel);
		
		return customerModel;
	}		
	
}
