package com.palmcommerce.funds.trade.ibatis.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.palmcommerce.funds.trade.ibatis.bean.TranscationFile;
import com.palmcommerce.funds.trade.ibatis.dao.TransactionFileDao;
import com.palmcommerce.funds.trade.ibatis.dao.impl.TransactionFileDaoImpl;
import com.palmcommerce.funds.trade.ibatis.services.TransactionFileService;

public class TransactionFileServiceImpl implements TransactionFileService {
	
	
	TransactionFileDao transactionFileDao;
	
	/**
	 * @return the transactionFileDao
	 */
	public TransactionFileDao getTransactionFileDao() {
		return transactionFileDao;
	}

	/**
	 * @param transactionFileDao the transactionFileDao to set
	 */
	public void setTransactionFileDao(TransactionFileDao transactionFileDao) {
		this.transactionFileDao = transactionFileDao;
	}

	public List<TranscationFile> findTransactionFileEntriesByBankDateAndTransCode(
			String bankDatatime, String transCode) {
		return transactionFileDao.findTransactionFileEntriesByBankDateAndTransCode(bankDatatime, transCode);
	}

}
