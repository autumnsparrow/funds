package com.palmcommerce.funds.roo.service;

import java.util.Date;

import org.springframework.roo.addon.layers.service.RooService;

import com.palmcommerce.funds.roo.model.TradeReconciliationStatics;

@RooService(domainTypes = { com.palmcommerce.funds.roo.model.TradeReconciliationStatics.class })
public interface TradeReconciliationStaticsService {
	
	TradeReconciliationStatics findTradeReconciliationStaticsesByBankDatetimeEquals(Date bankDatetime);
	int deleteTradeReconciliationByBankDatetimeEqualsAndTradecodeEqauls(Date bankDatetime,String tradecode);
}
