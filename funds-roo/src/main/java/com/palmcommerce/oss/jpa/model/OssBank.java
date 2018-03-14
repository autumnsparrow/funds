package com.palmcommerce.oss.jpa.model;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "ossTradeChargeOrders", "ossTradeChargeStatices", "ossTradeTasks" })
@RooJpaActiveRecord(versionField = "", table = "OSS_BANK", schema = "CQFC", finders = { "findOssBanksByBankIdEquals" })
public class OssBank {
}
