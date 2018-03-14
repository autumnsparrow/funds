package com.palmcommerce.server.v29.db;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString

@RooJpaActiveRecord(identifierType=UserAccountCanclePK.class,versionField = "", table = "USER_ACCOUNT", finders = { "findUserAccountsByUserIdEquals" })
public class UserAccount {

	@Column(name = "USER_ID", length = 30, unique = true)
    @NotNull
    private String userId;

	@Column(name = "PARTNER_ID", length = 20, unique = true)
    @NotNull
    private String partnerId;

	@Column(name = "P_USER_ID", length = 40, unique = true)
    @NotNull
    private String pUserId;

	@Column(name = "MONEY", precision = 15, scale = 2)
    @NotNull
    private BigDecimal money;

	@Column(name = "FREZZ_MONEY", precision = 15, scale = 2)
    @NotNull
    private BigDecimal frezzMoney;

	@Column(name = "ENABLE")
    @NotNull
    private BigDecimal enable;

	@Column(name = "CREATE_TIME")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date createTime;

	@Column(name = "CREDIT_MONEY", precision = 15, scale = 2)
    @NotNull
    private BigDecimal creditMoney;

	@Column(name = "T_CREDIT_MONEY", precision = 15, scale = 2)
    @NotNull
    private BigDecimal tCreditMoney;

	@Column(name = "EFFIECTIVE_TIME")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date effiectiveTime;

	@Column(name = "OPT_TIME")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date optTime;

	public String getUserId() {
        return userId;
    }

	public void setUserId(String userId) {
        this.userId = userId;
    }

	public String getPartnerId() {
        return partnerId;
    }

	public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

	public String getPUserId() {
        return pUserId;
    }

	public void setPUserId(String pUserId) {
        this.pUserId = pUserId;
    }

	public BigDecimal getMoney() {
        return money;
    }

	public void setMoney(BigDecimal money) {
        this.money = money;
    }

	public BigDecimal getFrezzMoney() {
        return frezzMoney;
    }

	public void setFrezzMoney(BigDecimal frezzMoney) {
        this.frezzMoney = frezzMoney;
    }

	public BigDecimal getEnable() {
        return enable;
    }

	public void setEnable(BigDecimal enable) {
        this.enable = enable;
    }

	public Date getCreateTime() {
        return createTime;
    }

	public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public BigDecimal getCreditMoney() {
        return creditMoney;
    }

	public void setCreditMoney(BigDecimal creditMoney) {
        this.creditMoney = creditMoney;
    }

	public BigDecimal getTCreditMoney() {
        return tCreditMoney;
    }

	public void setTCreditMoney(BigDecimal tCreditMoney) {
        this.tCreditMoney = tCreditMoney;
    }

	public Date getEffiectiveTime() {
        return effiectiveTime;
    }

	public void setEffiectiveTime(Date effiectiveTime) {
        this.effiectiveTime = effiectiveTime;
    }

	public Date getOptTime() {
        return optTime;
    }

	public void setOptTime(Date optTime) {
        this.optTime = optTime;
    }
}
