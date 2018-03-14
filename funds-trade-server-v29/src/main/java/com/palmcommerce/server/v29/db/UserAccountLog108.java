package com.palmcommerce.server.v29.db;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "USER_ACCOUNT_LOG108", schema = "LOTTERY_V29_SX", finders = { "findUserAccountLog108sByForigenIdEquals" })
@RooDbManaged(automaticallyDelete = true)
public class UserAccountLog108 {
}
