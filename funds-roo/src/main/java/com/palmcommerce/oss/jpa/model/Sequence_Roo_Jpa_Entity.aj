// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.Sequence;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

privileged aspect Sequence_Roo_Jpa_Entity {
    
    declare @type: Sequence: @Entity;
    
    declare @type: Sequence: @Table(schema = "CQFC", name = "SEQUENCE");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SEQ_NAME", length = 50)
    private String Sequence.seqName;
    
    public String Sequence.getSeqName() {
        return this.seqName;
    }
    
    public void Sequence.setSeqName(String id) {
        this.seqName = id;
    }
    
}
