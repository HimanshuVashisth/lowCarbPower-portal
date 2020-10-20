package com.lowCarbPower.lowCarbPowerportal.service;

import com.lowCarbPower.lowCarbPowerportal.model.CustomerModel;
import com.lowCarbPower.lowCarbPowerportal.model.UserModel;

public interface IRegistrationService {

	public CustomerModel register(UserModel userModel);
}
