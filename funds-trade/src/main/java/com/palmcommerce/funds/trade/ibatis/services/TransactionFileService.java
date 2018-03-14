package com.palmcommerce.funds.trade.ibatis.services;

import java.util.Date;
import java.util.List;

import com.palmcommerce.funds.trade.ibatis.bean.TranscationFile;


public interface TransactionFileService {
	public List<TranscationFile> findTransactionFileEntriesByBankDateAndTransCode(String bankDatatime, String transCode);
}
