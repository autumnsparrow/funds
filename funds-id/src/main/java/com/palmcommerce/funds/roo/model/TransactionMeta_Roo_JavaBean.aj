// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.funds.roo.model;

import com.palmcommerce.funds.roo.model.TransactionMeta;
import com.palmcommerce.funds.roo.model.TransactionRecord;
import java.util.Date;

privileged aspect TransactionMeta_Roo_JavaBean {
    
    public String TransactionMeta.getGlobalSerial() {
        return this.globalSerial;
    }
    
    public void TransactionMeta.setGlobalSerial(String globalSerial) {
        this.globalSerial = globalSerial;
    }
    
    public String TransactionMeta.getTranscode() {
        return this.transcode;
    }
    
    public void TransactionMeta.setTranscode(String transcode) {
        this.transcode = transcode;
    }
    
    public String TransactionMeta.getFromCode() {
        return this.fromCode;
    }
    
    public void TransactionMeta.setFromCode(String fromCode) {
        this.fromCode = fromCode;
    }
    
    public String TransactionMeta.getToCode() {
        return this.toCode;
    }
    
    public void TransactionMeta.setToCode(String toCode) {
        this.toCode = toCode;
    }
    
    public Date TransactionMeta.getTradeDateTime() {
        return this.tradeDateTime;
    }
    
    public void TransactionMeta.setTradeDateTime(Date tradeDateTime) {
        this.tradeDateTime = tradeDateTime;
    }
    
    public String TransactionMeta.getPaymentSerial() {
        return this.paymentSerial;
    }
    
    public void TransactionMeta.setPaymentSerial(String paymentSerial) {
        this.paymentSerial = paymentSerial;
    }
    
    public String TransactionMeta.getTradeSerial() {
        return this.tradeSerial;
    }
    
    public void TransactionMeta.setTradeSerial(String tradeSerial) {
        this.tradeSerial = tradeSerial;
    }
    
    public String TransactionMeta.getTransactionState() {
        return this.transactionState;
    }
    
    public void TransactionMeta.setTransactionState(String transactionState) {
        this.transactionState = transactionState;
    }
    
    public String TransactionMeta.getTransactionMessage() {
        return this.transactionMessage;
    }
    
    public void TransactionMeta.setTransactionMessage(String transactionMessage) {
        this.transactionMessage = transactionMessage;
    }
    
    public TransactionRecord TransactionMeta.getTransactionRecord() {
        return this.transactionRecord;
    }
    
    public void TransactionMeta.setTransactionRecord(TransactionRecord transactionRecord) {
        this.transactionRecord = transactionRecord;
    }
    
}
