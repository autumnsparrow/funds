package com.palmcommerce.funds.roo.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.palmcommerce.funds.roo.model.TransactionFile;


public class TransactionFileServiceImpl implements TransactionFileService {
	
	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.service.TransactionFileService#deleteTransactionFilesByBankDatetimeEquals(java.util.Date)
	 */
	@Override
	public int deleteTransactionFilesByBankDatetimeEquals(Date bankDatetime) {
		// TODO Auto-generated method stub
		//return TransactionFile.deleteTransactionFilesByBankDatetimeEquals(bankDatetime);
		 if (bankDatetime == null) throw new IllegalArgumentException("The bankDatetime argument is required");
	        EntityManager em = TransactionFile.entityManager();
	        TypedQuery<TransactionFile> q = em.createQuery("select o FROM TransactionFile AS o WHERE o.bankDatetime = :bankDatetime", TransactionFile.class);
	        q.setParameter("bankDatetime", bankDatetime);
	        
	        List<TransactionFile> files=q.getResultList();
	        if(files!=null){
	        	for(TransactionFile  f:files){
	        		f.remove();
	        	}
	        }
	        
	        return files.size();
	}

	public List<TransactionFile> findTransactionFileEntriesByBankDateAndTransCode(Date bankDatatime, String transCode){
		return TransactionFile.findTransactionFileEntriesByBankDateAndTransCode(bankDatatime, transCode);
	}
	
	
}
