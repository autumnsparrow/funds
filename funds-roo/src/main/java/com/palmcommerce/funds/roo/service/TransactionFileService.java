package com.palmcommerce.funds.roo.service;

import java.util.Date;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.palmcommerce.funds.roo.model.TransactionFile;

@RooService(domainTypes = { com.palmcommerce.funds.roo.model.TransactionFile.class })
public interface TransactionFileService {
	
	public List<TransactionFile> findTransactionFileEntriesByBankDateAndTransCode(Date bankDatatime, String transCode);
	
	public int deleteTransactionFilesByBankDatetimeEquals(Date bankDatetime,String bankId);

}
