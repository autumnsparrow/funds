// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.OssWithdrawStandard;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

privileged aspect OssWithdrawStandard_Roo_Jpa_Entity {
    
    declare @type: OssWithdrawStandard: @Entity;
    
    declare @type: OssWithdrawStandard: @Table(schema = "CQFC", name = "OSS_WITHDRAW_STANDARD");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private BigDecimal OssWithdrawStandard.id;
    
    public BigDecimal OssWithdrawStandard.getId() {
        return this.id;
    }
    
    public void OssWithdrawStandard.setId(BigDecimal id) {
        this.id = id;
    }
    
}
