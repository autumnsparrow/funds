// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.OssPlatform;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

privileged aspect OssPlatform_Roo_Jpa_Entity {
    
    declare @type: OssPlatform: @Entity;
    
    declare @type: OssPlatform: @Table(schema = "CQFC", name = "OSS_PLATFORM");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private BigDecimal OssPlatform.id;
    
    public BigDecimal OssPlatform.getId() {
        return this.id;
    }
    
    public void OssPlatform.setId(BigDecimal id) {
        this.id = id;
    }
    
}
