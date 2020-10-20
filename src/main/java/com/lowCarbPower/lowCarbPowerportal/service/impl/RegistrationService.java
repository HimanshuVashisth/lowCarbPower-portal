package com.lowCarbPower.lowCarbPowerportal.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.dozermapper.core.Mapper;
import com.lowCarbPower.lowCarbPowerportal.entity.CustomerEntity;
import com.lowCarbPower.lowCarbPowerportal.model.CustomerModel;
import com.lowCarbPower.lowCarbPowerportal.model.UserModel;
import com.lowCarbPower.lowCarbPowerportal.repository.jpa.customer.CustomerRepository;
import com.lowCarbPower.lowCarbPowerportal.service.IRegistrationService;

@Service
public class RegistrationService implements IRegistrationService {

	private static final Logger log = LoggerFactory.getLogger(RegistrationService.class);
	
	@Autowired
	private CustomerRepository customerRepository; 
	
	@Autowired
	private Mapper mapper;
	
	@Override
	public CustomerModel register(UserModel userModel) {
		CustomerModel customerModel = new CustomerModel();		
		CustomerEntity customerEntity = new CustomerEntity();
		
		customerModel.setUsername(userModel.getUsername());
		String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(userModel.getPassword());   
		customerModel.setPassword(sha256hex); 
		
		mapper.map(customerModel, customerEntity);
		
		CustomerEntity savedCustomerEntity = customerRepository.save(customerEntity);
		
		if(customerEntity != null)
			mapper.map(savedCustomerEntity,customerModel);
		
		return customerModel;
	}

}
