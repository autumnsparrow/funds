// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.OssTradeType;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

privileged aspect OssTradeType_Roo_Jpa_Entity {
    
    declare @type: OssTradeType: @Entity;
    
    declare @type: OssTradeType: @Table(schema = "CQFC", name = "OSS_TRADE_TYPE");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TYPE_ID")
    private BigDecimal OssTradeType.typeId;
    
    public BigDecimal OssTradeType.getTypeId() {
        return this.typeId;
    }
    
    public void OssTradeType.setTypeId(BigDecimal id) {
        this.typeId = id;
    }
    
}
