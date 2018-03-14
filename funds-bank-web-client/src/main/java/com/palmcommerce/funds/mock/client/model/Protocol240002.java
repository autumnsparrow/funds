package com.palmcommerce.funds.mock.client.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Protocol240002 extends BankHeader {

    private String userId;

    private String userName;

    private String idType;

    private String idNumber;

    private String bindType;

    private String bankAccount;

    private String bankDatetime;

    private String account;

    @Override
    public String toString() {
        return super.getSerialNumber()+"|"
        		+userId + "|" 
        		+ userName + "|" 
        		+ idType + "|" 
        		+ idNumber + "|" 
        		+ bindType+ "|"
        		+ bankAccount + "|"
        		+ super.getTransactionDatetime()+ "|" 
        		+ bankDatetime+"|";
    }

    public void initialize() {
        super.initialize();
        this.userId = SerialNumberGenerator.generateUserId();
        this.userName = SerialNumberGenerator.generateUserName();
        this.idType = "1";
        this.idNumber = SerialNumberGenerator.generate();
        this.bindType = "0";
        this.bankAccount = SerialNumberGenerator.generate();
        this.bankDatetime = SerialNumberGenerator.getBankDatetime();
    }
}
