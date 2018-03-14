package com.palmcommerce.oss.jpa.model;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(identifierType = OssTradeOrder2PK.class, versionField = "", table = "OSS_TRADE_ORDER_2", schema = "CQFC")
@RooDbManaged(automaticallyDelete = true)
public class OssTradeOrder2 {
}
