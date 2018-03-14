// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserAccountLog105;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect UserAccountLog105_Roo_DbManaged {
    
    @Column(name = "USER_ID", length = 30)
    @NotNull
    private String UserAccountLog105.userId;
    
    @Column(name = "PARTNER_ID", length = 30)
    @NotNull
    private String UserAccountLog105.partnerId;
    
    @Column(name = "OPT_CODE", length = 30)
    @NotNull
    private String UserAccountLog105.optCode;
    
    @Column(name = "OPT_MONEY", precision = 15, scale = 2)
    @NotNull
    private BigDecimal UserAccountLog105.optMoney;
    
    @Column(name = "CASH", precision = 15, scale = 2)
    @NotNull
    private BigDecimal UserAccountLog105.cash;
    
    @Column(name = "FREZZ_MONEY", precision = 15, scale = 2)
    @NotNull
    private BigDecimal UserAccountLog105.frezzMoney;
    
    @Column(name = "CREATE_TIME")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date UserAccountLog105.createTime;
    
    @Column(name = "FORIGEN_ID", length = 25)
    @NotNull
    private String UserAccountLog105.forigenId;
    
    public String UserAccountLog105.getUserId() {
        return userId;
    }
    
    public void UserAccountLog105.setUserId(String userId) {
        this.userId = userId;
    }
    
    public String UserAccountLog105.getPartnerId() {
        return partnerId;
    }
    
    public void UserAccountLog105.setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
    
    public String UserAccountLog105.getOptCode() {
        return optCode;
    }
    
    public void UserAccountLog105.setOptCode(String optCode) {
        this.optCode = optCode;
    }
    
    public BigDecimal UserAccountLog105.getOptMoney() {
        return optMoney;
    }
    
    public void UserAccountLog105.setOptMoney(BigDecimal optMoney) {
        this.optMoney = optMoney;
    }
    
    public BigDecimal UserAccountLog105.getCash() {
        return cash;
    }
    
    public void UserAccountLog105.setCash(BigDecimal cash) {
        this.cash = cash;
    }
    
    public BigDecimal UserAccountLog105.getFrezzMoney() {
        return frezzMoney;
    }
    
    public void UserAccountLog105.setFrezzMoney(BigDecimal frezzMoney) {
        this.frezzMoney = frezzMoney;
    }
    
    public Date UserAccountLog105.getCreateTime() {
        return createTime;
    }
    
    public void UserAccountLog105.setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public String UserAccountLog105.getForigenId() {
        return forigenId;
    }
    
    public void UserAccountLog105.setForigenId(String forigenId) {
        this.forigenId = forigenId;
    }
    
}