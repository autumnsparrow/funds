// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.OssTransitionOperatorRole;
import com.palmcommerce.oss.jpa.model.OssTransitionOperatorRolePK;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

privileged aspect OssTransitionOperatorRole_Roo_Jpa_Entity {
    
    declare @type: OssTransitionOperatorRole: @Entity;
    
    declare @type: OssTransitionOperatorRole: @Table(schema = "CQFC", name = "OSS_TRANSITION_OPERATOR_ROLE");
    
    @EmbeddedId
    private OssTransitionOperatorRolePK OssTransitionOperatorRole.id;
    
    public OssTransitionOperatorRolePK OssTransitionOperatorRole.getId() {
        return this.id;
    }
    
    public void OssTransitionOperatorRole.setId(OssTransitionOperatorRolePK id) {
        this.id = id;
    }
    
}
