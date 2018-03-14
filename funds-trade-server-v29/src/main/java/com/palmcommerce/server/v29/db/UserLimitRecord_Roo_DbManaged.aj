// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserLimitRecord;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect UserLimitRecord_Roo_DbManaged {
    
    @Column(name = "USER_ID", length = 30, unique = true)
    @NotNull
    private String UserLimitRecord.userId;
    
    @Column(name = "PARTNER_ID", length = 30, unique = true)
    @NotNull
    private String UserLimitRecord.partnerId;
    
    @Column(name = "CHARGE_MONEY", precision = 15, scale = 2)
    @NotNull
    private BigDecimal UserLimitRecord.chargeMoney;
    
    @Column(name = "CHARGE_NUM")
    @NotNull
    private BigDecimal UserLimitRecord.chargeNum;
    
    @Column(name = "ENCASH_MONEY", precision = 15, scale = 2)
    private BigDecimal UserLimitRecord.encashMoney;
    
    @Column(name = "ENCASH_NUM")
    private BigDecimal UserLimitRecord.encashNum;
    
    @Column(name = "VOTE_MONEY", precision = 15, scale = 2)
    @NotNull
    private BigDecimal UserLimitRecord.voteMoney;
    
    @Column(name = "TYPE", unique = true)
    private BigDecimal UserLimitRecord.type;
    
    @Column(name = "CREATE_TIME")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date UserLimitRecord.createTime;
    
    public String UserLimitRecord.getUserId() {
        return userId;
    }
    
    public void UserLimitRecord.setUserId(String userId) {
        this.userId = userId;
    }
    
    public String UserLimitRecord.getPartnerId() {
        return partnerId;
    }
    
    public void UserLimitRecord.setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
    
    public BigDecimal UserLimitRecord.getChargeMoney() {
        return chargeMoney;
    }
    
    public void UserLimitRecord.setChargeMoney(BigDecimal chargeMoney) {
        this.chargeMoney = chargeMoney;
    }
    
    public BigDecimal UserLimitRecord.getChargeNum() {
        return chargeNum;
    }
    
    public void UserLimitRecord.setChargeNum(BigDecimal chargeNum) {
        this.chargeNum = chargeNum;
    }
    
    public BigDecimal UserLimitRecord.getEncashMoney() {
        return encashMoney;
    }
    
    public void UserLimitRecord.setEncashMoney(BigDecimal encashMoney) {
        this.encashMoney = encashMoney;
    }
    
    public BigDecimal UserLimitRecord.getEncashNum() {
        return encashNum;
    }
    
    public void UserLimitRecord.setEncashNum(BigDecimal encashNum) {
        this.encashNum = encashNum;
    }
    
    public BigDecimal UserLimitRecord.getVoteMoney() {
        return voteMoney;
    }
    
    public void UserLimitRecord.setVoteMoney(BigDecimal voteMoney) {
        this.voteMoney = voteMoney;
    }
    
    public BigDecimal UserLimitRecord.getType() {
        return type;
    }
    
    public void UserLimitRecord.setType(BigDecimal type) {
        this.type = type;
    }
    
    public Date UserLimitRecord.getCreateTime() {
        return createTime;
    }
    
    public void UserLimitRecord.setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
}
