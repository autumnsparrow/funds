package com.palmcommerce.server.v29.db;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(identifierType = UserBankAccountPK.class, versionField = "", table = "USER_BANK_ACCOUNT", schema = "LOTTERY_V29_SX")
@RooDbManaged(automaticallyDelete = true)
public class UserBankAccount {
}
