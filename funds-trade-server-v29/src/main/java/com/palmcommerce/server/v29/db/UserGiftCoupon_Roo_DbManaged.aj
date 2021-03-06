// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserGiftCoupon;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect UserGiftCoupon_Roo_DbManaged {
    
    @Column(name = "P_COUPON_ID", length = 30)
    private String UserGiftCoupon.pCouponId;
    
    @Column(name = "PARTNER_ID", length = 20)
    @NotNull
    private String UserGiftCoupon.partnerId;
    
    @Column(name = "USER_ID", length = 30, unique = true)
    private String UserGiftCoupon.userId;
    
    @Column(name = "TYPE")
    @NotNull
    private BigDecimal UserGiftCoupon.type;
    
    @Column(name = "STATUS")
    @NotNull
    private BigDecimal UserGiftCoupon.status;
    
    @Column(name = "MONEY", precision = 15, scale = 2)
    @NotNull
    private BigDecimal UserGiftCoupon.money;
    
    @Column(name = "USE_MONEY", precision = 15, scale = 2)
    @NotNull
    private BigDecimal UserGiftCoupon.useMoney;
    
    @Column(name = "ACTIVITY_ID", length = 30)
    private String UserGiftCoupon.activityId;
    
    @Column(name = "REV_COUPON_ID", length = 30)
    private String UserGiftCoupon.revCouponId;
    
    @Column(name = "EXPIRED_TIME")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date UserGiftCoupon.expiredTime;
    
    @Column(name = "CREATE_TIME")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date UserGiftCoupon.createTime;
    
    @Column(name = "COUPON_UNICODE", length = 50, unique = true)
    @NotNull
    private String UserGiftCoupon.couponUnicode;
    
    @Column(name = "SEND_TYPE")
    private BigDecimal UserGiftCoupon.sendType;
    
    @Column(name = "IS_GIVE")
    @NotNull
    private BigDecimal UserGiftCoupon.isGive;
    
    @Column(name = "GIVE_PHONES", length = 200)
    private String UserGiftCoupon.givePhones;
    
    public String UserGiftCoupon.getPCouponId() {
        return pCouponId;
    }
    
    public void UserGiftCoupon.setPCouponId(String pCouponId) {
        this.pCouponId = pCouponId;
    }
    
    public String UserGiftCoupon.getPartnerId() {
        return partnerId;
    }
    
    public void UserGiftCoupon.setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
    
    public String UserGiftCoupon.getUserId() {
        return userId;
    }
    
    public void UserGiftCoupon.setUserId(String userId) {
        this.userId = userId;
    }
    
    public BigDecimal UserGiftCoupon.getType() {
        return type;
    }
    
    public void UserGiftCoupon.setType(BigDecimal type) {
        this.type = type;
    }
    
    public BigDecimal UserGiftCoupon.getStatus() {
        return status;
    }
    
    public void UserGiftCoupon.setStatus(BigDecimal status) {
        this.status = status;
    }
    
    public BigDecimal UserGiftCoupon.getMoney() {
        return money;
    }
    
    public void UserGiftCoupon.setMoney(BigDecimal money) {
        this.money = money;
    }
    
    public BigDecimal UserGiftCoupon.getUseMoney() {
        return useMoney;
    }
    
    public void UserGiftCoupon.setUseMoney(BigDecimal useMoney) {
        this.useMoney = useMoney;
    }
    
    public String UserGiftCoupon.getActivityId() {
        return activityId;
    }
    
    public void UserGiftCoupon.setActivityId(String activityId) {
        this.activityId = activityId;
    }
    
    public String UserGiftCoupon.getRevCouponId() {
        return revCouponId;
    }
    
    public void UserGiftCoupon.setRevCouponId(String revCouponId) {
        this.revCouponId = revCouponId;
    }
    
    public Date UserGiftCoupon.getExpiredTime() {
        return expiredTime;
    }
    
    public void UserGiftCoupon.setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }
    
    public Date UserGiftCoupon.getCreateTime() {
        return createTime;
    }
    
    public void UserGiftCoupon.setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public String UserGiftCoupon.getCouponUnicode() {
        return couponUnicode;
    }
    
    public void UserGiftCoupon.setCouponUnicode(String couponUnicode) {
        this.couponUnicode = couponUnicode;
    }
    
    public BigDecimal UserGiftCoupon.getSendType() {
        return sendType;
    }
    
    public void UserGiftCoupon.setSendType(BigDecimal sendType) {
        this.sendType = sendType;
    }
    
    public BigDecimal UserGiftCoupon.getIsGive() {
        return isGive;
    }
    
    public void UserGiftCoupon.setIsGive(BigDecimal isGive) {
        this.isGive = isGive;
    }
    
    public String UserGiftCoupon.getGivePhones() {
        return givePhones;
    }
    
    public void UserGiftCoupon.setGivePhones(String givePhones) {
        this.givePhones = givePhones;
    }
    
}
