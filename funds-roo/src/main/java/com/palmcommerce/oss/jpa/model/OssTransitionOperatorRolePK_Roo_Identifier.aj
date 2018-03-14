// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.OssTransitionOperatorRolePK;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

privileged aspect OssTransitionOperatorRolePK_Roo_Identifier {
    
    declare @type: OssTransitionOperatorRolePK: @Embeddable;
    
    @Column(name = "VC2_LOGIN_NAME", nullable = false, length = 64)
    private String OssTransitionOperatorRolePK.vc2LoginName;
    
    @Column(name = "NUM_ROLE_ID", nullable = false)
    private BigDecimal OssTransitionOperatorRolePK.numRoleId;
    
    public OssTransitionOperatorRolePK.new(String vc2LoginName, BigDecimal numRoleId) {
        super();
        this.vc2LoginName = vc2LoginName;
        this.numRoleId = numRoleId;
    }

    private OssTransitionOperatorRolePK.new() {
        super();
    }

    public String OssTransitionOperatorRolePK.getVc2LoginName() {
        return vc2LoginName;
    }
    
    public BigDecimal OssTransitionOperatorRolePK.getNumRoleId() {
        return numRoleId;
    }
    
}