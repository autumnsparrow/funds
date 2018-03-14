package com.palmcommerce.oss.jpa.service;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { com.palmcommerce.oss.jpa.model.OssReconciliationStatics.class })
public interface OssReconciliationStaticsService {
	
	public int deleteOssReconciliationStaticsByBankIdAndTradeTime(String bankId,String tradeTime);
}
