package com.palmcommerce.oss.jpa.model;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "OSS_ROLE", schema = "CQFC")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "ossOperatePopedoms", "ossTransitionOperatorRoles", "selectPartnerPopedoms" })
public class OssRole {
}
