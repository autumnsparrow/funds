// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.OssTradeChargeStatice;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

privileged aspect OssTradeChargeStatice_Roo_Jpa_Entity {
    
    declare @type: OssTradeChargeStatice: @Entity;
    
    declare @type: OssTradeChargeStatice: @Table(schema = "CQFC", name = "OSS_TRADE_CHARGE_STATICE");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private BigDecimal OssTradeChargeStatice.id;
    
    public BigDecimal OssTradeChargeStatice.getId() {
        return this.id;
    }
    
    public void OssTradeChargeStatice.setId(BigDecimal id) {
        this.id = id;
    }
    
}
