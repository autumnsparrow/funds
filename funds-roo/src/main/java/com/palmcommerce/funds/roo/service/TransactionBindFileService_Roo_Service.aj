// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.funds.roo.service;

import com.palmcommerce.funds.roo.model.TransactionBindFile;
import com.palmcommerce.funds.roo.service.TransactionBindFileService;
import java.util.List;

privileged aspect TransactionBindFileService_Roo_Service {
    
    public abstract long TransactionBindFileService.countAllTransactionBindFiles();    
    public abstract void TransactionBindFileService.deleteTransactionBindFile(TransactionBindFile transactionBindFile);    
    public abstract TransactionBindFile TransactionBindFileService.findTransactionBindFile(Long id);    
    public abstract List<TransactionBindFile> TransactionBindFileService.findAllTransactionBindFiles();    
    public abstract List<TransactionBindFile> TransactionBindFileService.findTransactionBindFileEntries(int firstResult, int maxResults);    
    public abstract void TransactionBindFileService.saveTransactionBindFile(TransactionBindFile transactionBindFile);    
    public abstract TransactionBindFile TransactionBindFileService.updateTransactionBindFile(TransactionBindFile transactionBindFile);    
}
