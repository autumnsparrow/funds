// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.OssTradeWithdrawStatics;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

privileged aspect OssTradeWithdrawStatics_Roo_Jpa_Entity {
    
    declare @type: OssTradeWithdrawStatics: @Entity;
    
    declare @type: OssTradeWithdrawStatics: @Table(schema = "CQFC", name = "OSS_TRADE_WITHDRAW_STATICS");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private BigDecimal OssTradeWithdrawStatics.id;
    
    public BigDecimal OssTradeWithdrawStatics.getId() {
        return this.id;
    }
    
    public void OssTradeWithdrawStatics.setId(BigDecimal id) {
        this.id = id;
    }
    
}
