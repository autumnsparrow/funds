// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.OssPlatform;
import com.palmcommerce.oss.jpa.model.OssTradeAjustmentOrder;
import com.palmcommerce.oss.jpa.model.OssTradeUser;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect OssTradeAjustmentOrder_Roo_DbManaged {
    
    @ManyToOne
    @JoinColumn(name = "PLATFORM_ID", referencedColumnName = "ID")
    private OssPlatform OssTradeAjustmentOrder.platformId;
    
    @ManyToOne
    @JoinColumn(name = "TRADE_USER_ID", referencedColumnName = "ID")
    private OssTradeUser OssTradeAjustmentOrder.tradeUserId;
    
    @Column(name = "ORDER_STATE")
    @NotNull
    private BigDecimal OssTradeAjustmentOrder.orderState;
    
    @Column(name = "DATE_TIME")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date OssTradeAjustmentOrder.dateTime;
    
    @Column(name = "AMOUNT")
    @NotNull
    private BigDecimal OssTradeAjustmentOrder.amount;
    
    public OssPlatform OssTradeAjustmentOrder.getPlatformId() {
        return platformId;
    }
    
    public void OssTradeAjustmentOrder.setPlatformId(OssPlatform platformId) {
        this.platformId = platformId;
    }
    
    public OssTradeUser OssTradeAjustmentOrder.getTradeUserId() {
        return tradeUserId;
    }
    
    public void OssTradeAjustmentOrder.setTradeUserId(OssTradeUser tradeUserId) {
        this.tradeUserId = tradeUserId;
    }
    
    public BigDecimal OssTradeAjustmentOrder.getOrderState() {
        return orderState;
    }
    
    public void OssTradeAjustmentOrder.setOrderState(BigDecimal orderState) {
        this.orderState = orderState;
    }
    
    public Date OssTradeAjustmentOrder.getDateTime() {
        return dateTime;
    }
    
    public void OssTradeAjustmentOrder.setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
    
    public BigDecimal OssTradeAjustmentOrder.getAmount() {
        return amount;
    }
    
    public void OssTradeAjustmentOrder.setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
}
