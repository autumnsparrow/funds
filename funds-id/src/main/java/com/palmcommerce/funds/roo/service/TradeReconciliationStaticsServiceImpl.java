package com.palmcommerce.funds.roo.service;

import java.util.Date;

import com.palmcommerce.funds.roo.model.TradeReconciliationStatics;


public class TradeReconciliationStaticsServiceImpl implements TradeReconciliationStaticsService {

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.service.TradeReconciliationStaticsService#findTradeReconciliationStaticsesByBankDatetimeEquals(java.util.Date)
	 */
	@Override
	public TradeReconciliationStatics findTradeReconciliationStaticsesByBankDatetimeEquals(
			Date bankDatetime) {
		// TODO Auto-generated method stub
		return TradeReconciliationStatics.findTradeReconciliationStaticsesByBankDatetimeEquals(bankDatetime).getSingleResult();
	}
}
