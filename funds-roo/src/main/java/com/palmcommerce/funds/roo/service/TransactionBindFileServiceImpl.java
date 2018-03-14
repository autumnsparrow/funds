package com.palmcommerce.funds.roo.service;

import java.util.Date;
import java.util.List;

import com.palmcommerce.funds.roo.model.TransactionBindFile;

public class TransactionBindFileServiceImpl implements TransactionBindFileService {
	public List<TransactionBindFile> findTransactionBindFileEntriesByBankDateAndTransCode(Date bankDatatime, String transCode){
		return TransactionBindFile.findTransactionBindFileEntriesByBankDateAndTransCode(bankDatatime, transCode);
	}
}
