// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.PartnerAccount;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect PartnerAccount_Roo_DbManaged {
    
    @Column(name = "CASH", precision = 14, scale = 2)
    @NotNull
    private BigDecimal PartnerAccount.cash;
    
    @Column(name = "CREDIT_LIMIT", precision = 14, scale = 2)
    @NotNull
    private BigDecimal PartnerAccount.creditLimit;
    
    @Column(name = "CREAT_TIME")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date PartnerAccount.creatTime;
    
    @Column(name = "DEFAULT_LIMIT", precision = 14, scale = 2)
    @NotNull
    private BigDecimal PartnerAccount.defaultLimit;
    
    public BigDecimal PartnerAccount.getCash() {
        return cash;
    }
    
    public void PartnerAccount.setCash(BigDecimal cash) {
        this.cash = cash;
    }
    
    public BigDecimal PartnerAccount.getCreditLimit() {
        return creditLimit;
    }
    
    public void PartnerAccount.setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }
    
    public Date PartnerAccount.getCreatTime() {
        return creatTime;
    }
    
    public void PartnerAccount.setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
    
    public BigDecimal PartnerAccount.getDefaultLimit() {
        return defaultLimit;
    }
    
    public void PartnerAccount.setDefaultLimit(BigDecimal defaultLimit) {
        this.defaultLimit = defaultLimit;
    }
    
}