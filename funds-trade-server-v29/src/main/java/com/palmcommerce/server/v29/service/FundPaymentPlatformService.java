package com.palmcommerce.server.v29.service;

import org.springframework.roo.addon.layers.service.RooService;
import org.springframework.stereotype.Service;

import com.palmcommerce.funds.protocol.trade.IStatus;
import com.palmcommerce.funds.service.ProtocolStorageException;


@RooService(domainTypes = { com.palmcommerce.server.v29.db.UserAccount.class })
public interface FundPaymentPlatformService extends IStatus{
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * @param bankCode  bank code Bxxxxxxx
	 * @param bankDate  bank date 20131224
	 * @param transactionTime   bank transaction time 2013-12-24 16:30:23
	 * @param paymentTerminalType bank terminal type  01- account  02- atm 03-web online
	 * @param orderId    funds payment platform serial	
	 * @param userId	userid
	 * @param userName  username
	 * @param payMoney  pay money  yang: 2.02 (yuan)
	 * @return
	 * @throws ProtocolStorageException transaction failed exception
	 */
	public boolean chargeByBank(
			String bankCode,
			String bankDate,
			String transactionTime,
			String paymentTerminalType,
			String orderId,
			String userId,
			String userName,
			double payMoney
			
			)throws ProtocolStorageException;
}
