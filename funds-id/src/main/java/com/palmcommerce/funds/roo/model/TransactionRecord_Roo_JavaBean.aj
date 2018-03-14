// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.funds.roo.model;

import com.palmcommerce.funds.roo.model.TransactionMeta;
import com.palmcommerce.funds.roo.model.TransactionRecord;
import java.util.Date;

privileged aspect TransactionRecord_Roo_JavaBean {
    
    public String TransactionRecord.getUserId() {
        return this.userId;
    }
    
    public void TransactionRecord.setUserId(String userId) {
        this.userId = userId;
    }
    
    public String TransactionRecord.getUserName() {
        return this.userName;
    }
    
    public void TransactionRecord.setUserName(String userName) {
        this.userName = userName;
    }
    
    public String TransactionRecord.getIdType() {
        return this.idType;
    }
    
    public void TransactionRecord.setIdType(String idType) {
        this.idType = idType;
    }
    
    public String TransactionRecord.getIdNumber() {
        return this.idNumber;
    }
    
    public void TransactionRecord.setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
    
    public String TransactionRecord.getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void TransactionRecord.setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public long TransactionRecord.getTradeSystemDeposit() {
        return this.tradeSystemDeposit;
    }
    
    public void TransactionRecord.setTradeSystemDeposit(long tradeSystemDeposit) {
        this.tradeSystemDeposit = tradeSystemDeposit;
    }
    
    public String TransactionRecord.getBankAccount() {
        return this.bankAccount;
    }
    
    public void TransactionRecord.setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
    
    public Date TransactionRecord.getBankDatetime() {
        return this.bankDatetime;
    }
    
    public void TransactionRecord.setBankDatetime(Date bankDatetime) {
        this.bankDatetime = bankDatetime;
    }
    
    public int TransactionRecord.getBindingType() {
        return this.bindingType;
    }
    
    public void TransactionRecord.setBindingType(int bindingType) {
        this.bindingType = bindingType;
    }
    
    public int TransactionRecord.getChargeType() {
        return this.chargeType;
    }
    
    public void TransactionRecord.setChargeType(int chargeType) {
        this.chargeType = chargeType;
    }
    
    public long TransactionRecord.getAmount() {
        return this.amount;
    }
    
    public void TransactionRecord.setAmount(long amount) {
        this.amount = amount;
    }
    
    public String TransactionRecord.getCancelPaymentSerial() {
        return this.cancelPaymentSerial;
    }
    
    public void TransactionRecord.setCancelPaymentSerial(String cancelPaymentSerial) {
        this.cancelPaymentSerial = cancelPaymentSerial;
    }
    
    public TransactionMeta TransactionRecord.getTransactionMeta() {
        return this.transactionMeta;
    }
    
    public void TransactionRecord.setTransactionMeta(TransactionMeta transactionMeta) {
        this.transactionMeta = transactionMeta;
    }
    
    public String TransactionRecord.getFileName() {
        return this.fileName;
    }
    
    public void TransactionRecord.setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public int TransactionRecord.getFileSize() {
        return this.fileSize;
    }
    
    public void TransactionRecord.setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }
    
}