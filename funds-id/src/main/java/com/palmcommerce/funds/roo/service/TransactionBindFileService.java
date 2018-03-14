package com.palmcommerce.funds.roo.service;
import java.util.Date;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.palmcommerce.funds.roo.model.TransactionBindFile;

@RooService(domainTypes = { com.palmcommerce.funds.roo.model.TransactionBindFile.class })
public interface TransactionBindFileService {
	public List<TransactionBindFile> findTransactionBindFileEntriesByBankDateAndTransCode(Date bankDatatime, String transCode);
}
