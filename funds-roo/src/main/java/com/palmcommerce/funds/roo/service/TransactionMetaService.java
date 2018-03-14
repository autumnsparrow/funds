package com.palmcommerce.funds.roo.service;

import java.util.Date;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.palmcommerce.funds.roo.model.TransactionMeta;


@RooService(domainTypes = { com.palmcommerce.funds.roo.model.TransactionMeta.class })
public interface TransactionMetaService {
	public int countTransactionMetaEntriesByBankDateAndBankCode(Date bankDatatime, String bankCode);
	  public List<TransactionMeta> findTransactionMetaEntriesByBankDateAndBankCode(Date bankDatatime, String bankCode,int first,int max);
	  public List<TransactionMeta> findChargedTransactionMetaEntriesByBankDateAndBankCode(Date bankDatatime, String bankCode);
	  public List<TransactionMeta> findCanceledTransactionMetaEntriesByBankDateAndBankCode(Date bankDatatime, String bankCode);
	  public List<TransactionMeta> findTransactionMetasByTradeDateTimeBetweenAndTransCode(Date minTradeDateTime, Date maxTradeDateTime,boolean isCharge);
	  public String findBankNumberByCardNumber(String cardNumber);
		  
}
