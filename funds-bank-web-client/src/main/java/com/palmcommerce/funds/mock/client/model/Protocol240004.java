package com.palmcommerce.funds.mock.client.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Protocol240004 extends BankHeader {

    private String cancelSerialNumber;

    private String userId;

    private String userName;

    private String amount;

    private String bankDatetime;

	@Override
	public String toString() {
		return  super.getSerialNumber()+"|"
				+cancelSerialNumber+ "|" 
				+ userId + "|" + userName + "|"
				+ amount + "|" 
				+super.getTransactionDatetime()+"|"
				+ bankDatetime + "|";
	}
	
	public void initialize(){
		super.initialize();
		this.cancelSerialNumber=SerialNumberGenerator.generate();
		this.userId=SerialNumberGenerator.generateUserId();
		this.userName=SerialNumberGenerator.generateUserName();
		this.amount="10";
		this.bankDatetime=SerialNumberGenerator.getBankDatetime();
		
	}
    
    
}
