package com.lowCarbPower.lowCarbPowerportal.service;

import com.lowCarbPower.lowCarbPowerportal.model.CustomerModel;

public interface ILoginService {

	public CustomerModel login(String username);
}
