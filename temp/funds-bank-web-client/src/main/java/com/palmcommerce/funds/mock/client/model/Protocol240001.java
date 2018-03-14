package com.palmcommerce.funds.mock.client.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Protocol240001 extends BankHeader {

    private String userId;

	@Override
	public String toString() {
		return super.getSerialNumber()+"|" 
				 + userId + "|"
				 +super.getTransactionDatetime()+"|";
	}
    
    
}
