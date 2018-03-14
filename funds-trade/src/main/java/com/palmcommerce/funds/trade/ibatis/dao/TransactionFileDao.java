package com.palmcommerce.funds.trade.ibatis.dao;

import java.util.Date;
import java.util.List;

import com.palmcommerce.funds.trade.ibatis.bean.TranscationFile;;

public interface TransactionFileDao {

	
	
	public List<TranscationFile> findTransactionFileEntriesByBankDateAndTransCode(String bankDatatime, String transCode);
}
