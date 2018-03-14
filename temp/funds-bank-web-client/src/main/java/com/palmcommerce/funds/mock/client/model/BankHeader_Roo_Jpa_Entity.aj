// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.funds.mock.client.model;

import com.palmcommerce.funds.mock.client.model.BankHeader;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Version;

privileged aspect BankHeader_Roo_Jpa_Entity {
    
    declare @type: BankHeader: @Entity;
    
    declare @type: BankHeader: @Inheritance(strategy = InheritanceType.SINGLE_TABLE);
    
    declare @type: BankHeader: @DiscriminatorColumn;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long BankHeader.id;
    
    @Version
    @Column(name = "version")
    private Integer BankHeader.version;
    
    public Long BankHeader.getId() {
        return this.id;
    }
    
    public void BankHeader.setId(Long id) {
        this.id = id;
    }
    
    public Integer BankHeader.getVersion() {
        return this.version;
    }
    
    public void BankHeader.setVersion(Integer version) {
        this.version = version;
    }
    
}