package com.palmcommerce.oss.jpa.model;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(identifierType = OssOperatePopedomPK.class, versionField = "", table = "OSS_OPERATE_POPEDOM", schema = "CQFC")
@RooDbManaged(automaticallyDelete = true)
public class OssOperatePopedom {
}
