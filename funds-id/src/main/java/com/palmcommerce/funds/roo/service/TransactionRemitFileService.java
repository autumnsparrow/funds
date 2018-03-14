package com.palmcommerce.funds.roo.service;
import java.util.Date;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.palmcommerce.funds.roo.model.TransactionRemitFile;

@RooService(domainTypes = { com.palmcommerce.funds.roo.model.TransactionRemitFile.class })
public interface TransactionRemitFileService {
	
	public List<TransactionRemitFile> findTransactionRemitFileEntriesByBankDateAndTransCode(Date bankDatatime, String transCode);
}
