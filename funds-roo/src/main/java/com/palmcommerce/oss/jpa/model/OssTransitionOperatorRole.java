package com.palmcommerce.oss.jpa.model;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(identifierType = OssTransitionOperatorRolePK.class, versionField = "", table = "OSS_TRANSITION_OPERATOR_ROLE", schema = "CQFC")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "vc2LoginName", "numRoleId" })
public class OssTransitionOperatorRole {
}
