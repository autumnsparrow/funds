// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.Sequence;
import java.math.BigDecimal;
import javax.persistence.Column;

privileged aspect Sequence_Roo_DbManaged {
    
    @Column(name = "SEQ_COUNT")
    private BigDecimal Sequence.seqCount;
    
    public BigDecimal Sequence.getSeqCount() {
        return seqCount;
    }
    
    public void Sequence.setSeqCount(BigDecimal seqCount) {
        this.seqCount = seqCount;
    }
    
}
