package com.palmcommerce.funds.mock.client.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "SINGLE_TABLE")
public abstract class BankHeader {

    @Enumerated(EnumType.STRING)
    private TranscodeEnum transcode;

    @Enumerated(EnumType.STRING)
    private FromCodeEnum fromcode;

    @Enumerated(EnumType.STRING)
    private ToCodeEnum tocode;

    private int siglen;

    private String response;

    private String serialNumber;

    private String transactionDatetime;
    
    protected void initialize(){
    	this.siglen=128;
    	this.serialNumber=SerialNumberGenerator.generate();
    	this.transactionDatetime=SerialNumberGenerator.getTransactionDatetime();
    }

	public String getSerialNumber() {
		return serialNumber;
	}

	public String getTransactionDatetime() {
		return transactionDatetime;
	}
    
    
    
    
}
