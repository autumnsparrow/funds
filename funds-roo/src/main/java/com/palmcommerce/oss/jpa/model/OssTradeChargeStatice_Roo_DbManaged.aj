// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.OssBank;
import com.palmcommerce.oss.jpa.model.OssPlatform;
import com.palmcommerce.oss.jpa.model.OssTradeChargeStatice;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect OssTradeChargeStatice_Roo_DbManaged {
    
    @ManyToOne
    @JoinColumn(name = "BANK_ID", referencedColumnName = "ID")
    private OssBank OssTradeChargeStatice.bankId;
    
    @ManyToOne
    @JoinColumn(name = "PLATFORM_ID", referencedColumnName = "ID")
    private OssPlatform OssTradeChargeStatice.platformId;
    
    @Column(name = "METHOD_ID")
    private BigDecimal OssTradeChargeStatice.methodId;
    
    @Column(name = "TRADE_DATE")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date OssTradeChargeStatice.tradeDate;
    
    @Column(name = "ORDER_NUMBER")
    @NotNull
    private BigDecimal OssTradeChargeStatice.orderNumber;
    
    @Column(name = "ORDER_AMOUNT")
    private BigDecimal OssTradeChargeStatice.orderAmount;
    
    public OssBank OssTradeChargeStatice.getBankId() {
        return bankId;
    }
    
    public void OssTradeChargeStatice.setBankId(OssBank bankId) {
        this.bankId = bankId;
    }
    
    public OssPlatform OssTradeChargeStatice.getPlatformId() {
        return platformId;
    }
    
    public void OssTradeChargeStatice.setPlatformId(OssPlatform platformId) {
        this.platformId = platformId;
    }
    
    public BigDecimal OssTradeChargeStatice.getMethodId() {
        return methodId;
    }
    
    public void OssTradeChargeStatice.setMethodId(BigDecimal methodId) {
        this.methodId = methodId;
    }
    
    public Date OssTradeChargeStatice.getTradeDate() {
        return tradeDate;
    }
    
    public void OssTradeChargeStatice.setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }
    
    public BigDecimal OssTradeChargeStatice.getOrderNumber() {
        return orderNumber;
    }
    
    public void OssTradeChargeStatice.setOrderNumber(BigDecimal orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    public BigDecimal OssTradeChargeStatice.getOrderAmount() {
        return orderAmount;
    }
    
    public void OssTradeChargeStatice.setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }
    
}