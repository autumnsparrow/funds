// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.funds.roo.service;

import com.palmcommerce.funds.roo.model.TransactionFile;
import com.palmcommerce.funds.roo.service.TransactionFileServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect TransactionFileServiceImpl_Roo_Service {
    
    declare @type: TransactionFileServiceImpl: @Service;
    
    declare @type: TransactionFileServiceImpl: @Transactional;
    
    public long TransactionFileServiceImpl.countAllTransactionFiles() {
        return TransactionFile.countTransactionFiles();
    }
    
    public void TransactionFileServiceImpl.deleteTransactionFile(TransactionFile transactionFile) {
        transactionFile.remove();
    }
    
    public TransactionFile TransactionFileServiceImpl.findTransactionFile(Long id) {
        return TransactionFile.findTransactionFile(id);
    }
    
    public List<TransactionFile> TransactionFileServiceImpl.findAllTransactionFiles() {
        return TransactionFile.findAllTransactionFiles();
    }
    
    public List<TransactionFile> TransactionFileServiceImpl.findTransactionFileEntries(int firstResult, int maxResults) {
        return TransactionFile.findTransactionFileEntries(firstResult, maxResults);
    }
    
    public void TransactionFileServiceImpl.saveTransactionFile(TransactionFile transactionFile) {
        transactionFile.persist();
    }
    
    public TransactionFile TransactionFileServiceImpl.updateTransactionFile(TransactionFile transactionFile) {
        return transactionFile.merge();
    }
    
}
