// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.funds.ca.model;

import com.palmcommerce.funds.ca.model.Cacrts;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect Cacrts_Roo_Jpa_Entity {
    
    declare @type: Cacrts: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Cacrts.id;
    
    @Version
    @Column(name = "version")
    private Integer Cacrts.version;
    
    public Long Cacrts.getId() {
        return this.id;
    }
    
    public void Cacrts.setId(Long id) {
        this.id = id;
    }
    
    public Integer Cacrts.getVersion() {
        return this.version;
    }
    
    public void Cacrts.setVersion(Integer version) {
        this.version = version;
    }
    
}