// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.funds.mock.client.model;

import com.palmcommerce.funds.mock.client.model.BankHeader;
import com.palmcommerce.funds.mock.client.model.FromCodeEnum;
import com.palmcommerce.funds.mock.client.model.ToCodeEnum;
import com.palmcommerce.funds.mock.client.model.TranscodeEnum;

privileged aspect BankHeader_Roo_JavaBean {
    
    public TranscodeEnum BankHeader.getTranscode() {
        return this.transcode;
    }
    
    public void BankHeader.setTranscode(TranscodeEnum transcode) {
        this.transcode = transcode;
    }
    
    public FromCodeEnum BankHeader.getFromcode() {
        return this.fromcode;
    }
    
    public void BankHeader.setFromcode(FromCodeEnum fromcode) {
        this.fromcode = fromcode;
    }
    
    public ToCodeEnum BankHeader.getTocode() {
        return this.tocode;
    }
    
    public void BankHeader.setTocode(ToCodeEnum tocode) {
        this.tocode = tocode;
    }
    
    public int BankHeader.getSiglen() {
        return this.siglen;
    }
    
    public void BankHeader.setSiglen(int siglen) {
        this.siglen = siglen;
    }
    
    public String BankHeader.getResponse() {
        return this.response;
    }
    
    public void BankHeader.setResponse(String response) {
        this.response = response;
    }
    
    public void BankHeader.setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    public void BankHeader.setTransactionDatetime(String transactionDatetime) {
        this.transactionDatetime = transactionDatetime;
    }
    
}
