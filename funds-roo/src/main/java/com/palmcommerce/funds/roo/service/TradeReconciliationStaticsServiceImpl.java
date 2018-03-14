package com.palmcommerce.funds.roo.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.palmcommerce.funds.roo.model.TradeReconciliationStatics;


public class TradeReconciliationStaticsServiceImpl implements TradeReconciliationStaticsService {

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.service.TradeReconciliationStaticsService#findTradeReconciliationStaticsesByBankDatetimeEquals(java.util.Date)
	 */
	
	 public static TypedQuery<TradeReconciliationStatics> findTradeReconciliationStaticsesByBankDatetimeEqualsAndTradecodeEqulas(Date bankDatetime,String tradecode) {
	        if (bankDatetime == null) throw new IllegalArgumentException("The bankDatetime argument is required");
	        EntityManager em = TradeReconciliationStatics.entityManager();
	        TypedQuery<TradeReconciliationStatics> q = em.createQuery("SELECT o FROM TradeReconciliationStatics AS o WHERE o.bankDatetime = :bankDatetime and o.tradeCode=:tradecode", TradeReconciliationStatics.class);
	        q.setParameter("bankDatetime", bankDatetime);
	        q.setParameter("tradecode", tradecode);
	        return q;
	    }
	@Override
	public TradeReconciliationStatics findTradeReconciliationStaticsesByBankDatetimeEquals(
			Date bankDatetime) {
		// TODO Auto-generated method stub
		return TradeReconciliationStatics.findTradeReconciliationStaticsesByBankDatetimeEquals(bankDatetime).getSingleResult();
	}
	@Override
	public int deleteTradeReconciliationByBankDatetimeEqualsAndTradecodeEqauls(
			Date bankDatetime, String tradecode) {
		// TODO Auto-generated method stub
		List<TradeReconciliationStatics> lst=findTradeReconciliationStaticsesByBankDatetimeEqualsAndTradecodeEqulas(bankDatetime,tradecode).getResultList();
		for(TradeReconciliationStatics t:lst){
			t.remove();
		}
		
		return lst.size();
	} 
}
