// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserChargeStat;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect UserChargeStat_Roo_DbManaged {
    
    @Column(name = "PARTNER_ID", length = 20)
    private String UserChargeStat.partnerId;
    
    @Column(name = "CHARGE_TYPE")
    private BigDecimal UserChargeStat.chargeType;
    
    @Column(name = "TOTAL_SUC_COUNT")
    private BigDecimal UserChargeStat.totalSucCount;
    
    @Column(name = "TOTAL_SUC_MONEY", precision = 20, scale = 2)
    private BigDecimal UserChargeStat.totalSucMoney;
    
    @Column(name = "TOTAL_FAIL_COUNT")
    private BigDecimal UserChargeStat.totalFailCount;
    
    @Column(name = "TOTAL_FAIL_MONEY", precision = 20, scale = 2)
    private BigDecimal UserChargeStat.totalFailMoney;
    
    @Column(name = "CREATE_TIME")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date UserChargeStat.createTime;
    
    @Column(name = "CHARGE_TIME")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date UserChargeStat.chargeTime;
    
    public String UserChargeStat.getPartnerId() {
        return partnerId;
    }
    
    public void UserChargeStat.setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
    
    public BigDecimal UserChargeStat.getChargeType() {
        return chargeType;
    }
    
    public void UserChargeStat.setChargeType(BigDecimal chargeType) {
        this.chargeType = chargeType;
    }
    
    public BigDecimal UserChargeStat.getTotalSucCount() {
        return totalSucCount;
    }
    
    public void UserChargeStat.setTotalSucCount(BigDecimal totalSucCount) {
        this.totalSucCount = totalSucCount;
    }
    
    public BigDecimal UserChargeStat.getTotalSucMoney() {
        return totalSucMoney;
    }
    
    public void UserChargeStat.setTotalSucMoney(BigDecimal totalSucMoney) {
        this.totalSucMoney = totalSucMoney;
    }
    
    public BigDecimal UserChargeStat.getTotalFailCount() {
        return totalFailCount;
    }
    
    public void UserChargeStat.setTotalFailCount(BigDecimal totalFailCount) {
        this.totalFailCount = totalFailCount;
    }
    
    public BigDecimal UserChargeStat.getTotalFailMoney() {
        return totalFailMoney;
    }
    
    public void UserChargeStat.setTotalFailMoney(BigDecimal totalFailMoney) {
        this.totalFailMoney = totalFailMoney;
    }
    
    public Date UserChargeStat.getCreateTime() {
        return createTime;
    }
    
    public void UserChargeStat.setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Date UserChargeStat.getChargeTime() {
        return chargeTime;
    }
    
    public void UserChargeStat.setChargeTime(Date chargeTime) {
        this.chargeTime = chargeTime;
    }
    
}
