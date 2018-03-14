package com.palmcommerce.oss.jpa.model;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(identifierType = SelectPartnerPopedomPK.class, versionField = "", table = "SELECT_PARTNER_POPEDOM", schema = "CQFC")
@RooDbManaged(automaticallyDelete = true)
public class SelectPartnerPopedom {
}
