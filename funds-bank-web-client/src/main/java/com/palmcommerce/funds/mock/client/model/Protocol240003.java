package com.palmcommerce.funds.mock.client.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Protocol240003 extends BankHeader {

    private String userId;

    private String userName;

    private String chargeType;

    private String chargeAccount;

    private String bankDatetime;

	@Override
	public String toString() {
		return  super.getSerialNumber()+"|"
				+userId + "|"
				+ userName+ "|" 
				+ chargeType + "|"
				+ chargeAccount + "|" 
				+ super.getTransactionDatetime()+"|"
				+ bankDatetime + "|";
	}
	
	public void initialize(){
		super.initialize();
		this.userId=SerialNumberGenerator.generateUserId();
		this.userName=SerialNumberGenerator.generateUserName();
		this.chargeType="1";
		this.chargeAccount="100";
		this.bankDatetime=SerialNumberGenerator.getBankDatetime();
	}
    
    
}
