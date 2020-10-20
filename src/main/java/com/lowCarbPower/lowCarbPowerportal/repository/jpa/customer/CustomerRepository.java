package com.lowCarbPower.lowCarbPowerportal.repository.jpa.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lowCarbPower.lowCarbPowerportal.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

	public CustomerEntity findByUsername(String username);
}
