package com.palmcommerce.server.v29.service;

import com.palmcommerce.server.v29.db.UserCharge;


public class UserChargeServiceImpl implements UserChargeService {

	@Override
	public UserCharge findUserChargeByOrderIdEquals(String orderId) {
		// TODO Auto-generated method stub
		return UserCharge.findUserChargesByOrderIdEquals(orderId).getSingleResult();
	}
	
	
}
