package com.palmcommerce.funds.mock.client.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Protocol240005 extends BankHeader {

    private String realUsername;

    private String idType;

    private String idNumber;

    private String bankDatetime;

    private String phone;

    private String account;

    @Override
    public String toString() {
        return super.getSerialNumber() + "|" 
        		+ realUsername + "|" 
        		+ idType + "|" 
        		+ idNumber + "|" 
        		+ phone +"|"
        		+ account+"|"
        		+ super.getTransactionDatetime() + "|"
        		+ bankDatetime + "|";
    }

    public void initialize() {
        super.initialize();
        this.realUsername = SerialNumberGenerator.generateUserName();
        this.idType = "1";
        this.idNumber = SerialNumberGenerator.generate();
        this.bankDatetime = SerialNumberGenerator.getBankDatetime();
    }
}
