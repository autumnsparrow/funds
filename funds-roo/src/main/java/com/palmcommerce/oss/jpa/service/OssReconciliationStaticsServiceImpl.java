package com.palmcommerce.oss.jpa.service;

import java.util.List;

import javax.persistence.TypedQuery;

import com.palmcommerce.oss.jpa.model.OssReconciliationStatics;


public class OssReconciliationStaticsServiceImpl implements OssReconciliationStaticsService {

	/* (non-Javadoc)
	 * @see com.palmcommerce.oss.jpa.service.OssReconciliationStaticsService#deleteOssReconciliationStaticsByBankIdAndTradeTime(java.lang.String, java.lang.String)
	 */
	
	public int deleteOssReconciliationStaticsByBankIdAndTradeTime(
			String bankId, String tradeTime) {
		// TODO Auto-generated method stub
		TypedQuery<OssReconciliationStatics> query= OssReconciliationStatics.findOssReconciliationStaticsesByTradeTimeEqualsAndFinanceNameEquals(tradeTime, bankId);
		
		List<OssReconciliationStatics> entries=query.getResultList();
		if(entries!=null){
			for(OssReconciliationStatics entry:entries){
				entry.remove();
			}
		}
		
		return entries.size();
	
	}


}
