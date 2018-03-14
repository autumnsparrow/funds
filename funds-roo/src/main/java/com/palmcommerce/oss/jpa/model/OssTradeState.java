package com.palmcommerce.oss.jpa.model;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "OSS_TRADE_STATE", schema = "CQFC")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "ossTradeTasks" })
public class OssTradeState {
}
