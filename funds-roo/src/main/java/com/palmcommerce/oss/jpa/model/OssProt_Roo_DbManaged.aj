// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.OssProt;
import java.math.BigDecimal;
import javax.persistence.Column;

privileged aspect OssProt_Roo_DbManaged {
    
    @Column(name = "IP", length = 100)
    private String OssProt.ip;
    
    @Column(name = "PROT")
    private BigDecimal OssProt.prot;
    
    public String OssProt.getIp() {
        return ip;
    }
    
    public void OssProt.setIp(String ip) {
        this.ip = ip;
    }
    
    public BigDecimal OssProt.getProt() {
        return prot;
    }
    
    public void OssProt.setProt(BigDecimal prot) {
        this.prot = prot;
    }
    
}
