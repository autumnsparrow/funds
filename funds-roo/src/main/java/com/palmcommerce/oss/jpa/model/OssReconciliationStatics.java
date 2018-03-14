package com.palmcommerce.oss.jpa.model;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "ossTradesystemrStaticss" })
@RooJpaActiveRecord(versionField = "", table = "OSS_RECONCILIATION_STATICS", schema = "CQFC", finders = { "findOssReconciliationStaticsesByTradeTimeEqualsAndFinanceNameEquals" })
public class OssReconciliationStatics {
}
