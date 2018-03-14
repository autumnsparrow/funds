// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserBankAccountPK;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect UserBankAccountPK_Roo_Identifier {
    
    declare @type: UserBankAccountPK: @Embeddable;
    
    @Column(name = "SERIES_NO", nullable = false, length = 50)
    private String UserBankAccountPK.seriesNo;
    
    @Column(name = "USER_ID", nullable = false, length = 20)
    private String UserBankAccountPK.userId;
    
    @Column(name = "USER_NAME", nullable = false, length = 50)
    private String UserBankAccountPK.userName;
    
    @Column(name = "PAY_MONEY", nullable = false, precision = 11, scale = 2)
    private BigDecimal UserBankAccountPK.payMoney;
    
    @Column(name = "CREATE_TIME", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date UserBankAccountPK.createTime;
    
    @Column(name = "BANK_CODE", nullable = false, length = 10)
    private String UserBankAccountPK.bankCode;
    
    @Column(name = "ACCOUNT_TIME", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date UserBankAccountPK.accountTime;
    
    @Column(name = "PARTNER_ID", nullable = false, length = 20)
    private String UserBankAccountPK.partnerId;
    
    @Column(name = "ACCOUNT_TYPE", nullable = false)
    private BigDecimal UserBankAccountPK.accountType;
    
    @Column(name = "BALANCE_MONEY", nullable = false, precision = 11, scale = 2)
    private BigDecimal UserBankAccountPK.balanceMoney;
    
    public UserBankAccountPK.new(String seriesNo, String userId, String userName, BigDecimal payMoney, Date createTime, String bankCode, Date accountTime, String partnerId, BigDecimal accountType, BigDecimal balanceMoney) {
        super();
        this.seriesNo = seriesNo;
        this.userId = userId;
        this.userName = userName;
        this.payMoney = payMoney;
        this.createTime = createTime;
        this.bankCode = bankCode;
        this.accountTime = accountTime;
        this.partnerId = partnerId;
        this.accountType = accountType;
        this.balanceMoney = balanceMoney;
    }

    private UserBankAccountPK.new() {
        super();
    }

    public String UserBankAccountPK.getSeriesNo() {
        return seriesNo;
    }
    
    public String UserBankAccountPK.getUserId() {
        return userId;
    }
    
    public String UserBankAccountPK.getUserName() {
        return userName;
    }
    
    public BigDecimal UserBankAccountPK.getPayMoney() {
        return payMoney;
    }
    
    public Date UserBankAccountPK.getCreateTime() {
        return createTime;
    }
    
    public String UserBankAccountPK.getBankCode() {
        return bankCode;
    }
    
    public Date UserBankAccountPK.getAccountTime() {
        return accountTime;
    }
    
    public String UserBankAccountPK.getPartnerId() {
        return partnerId;
    }
    
    public BigDecimal UserBankAccountPK.getAccountType() {
        return accountType;
    }
    
    public BigDecimal UserBankAccountPK.getBalanceMoney() {
        return balanceMoney;
    }
    
}