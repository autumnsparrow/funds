package com.palmcommerce.funds.roo.service;

import java.util.Date;
import java.util.List;

import com.palmcommerce.funds.roo.model.TransactionRemitFile;

public class TransactionRemitFileServiceImpl implements TransactionRemitFileService {

	@Override
	public List<TransactionRemitFile> findTransactionRemitFileEntriesByBankDateAndTransCode(
			Date bankDatatime, String transCode) {
		return TransactionRemitFile.findTransactionRemitFileEntriesByBankDateAndTransCode(bankDatatime, transCode);
	}
}
