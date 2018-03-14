package com.palmcommerce.server.v29.service;

import org.springframework.roo.addon.layers.service.RooService;

import com.palmcommerce.server.v29.db.UserCharge;

@RooService(domainTypes = { com.palmcommerce.server.v29.db.UserCharge.class })
public interface UserChargeService {
	
	
	public UserCharge findUserChargeByOrderIdEquals(String orderId);
}
