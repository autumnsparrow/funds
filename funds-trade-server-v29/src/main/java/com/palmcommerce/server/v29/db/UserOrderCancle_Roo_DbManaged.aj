// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserOrderCancle;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect UserOrderCancle_Roo_DbManaged {
    
    @Column(name = "GAME_ID", length = 10)
    @NotNull
    private String UserOrderCancle.gameId;
    
    @Column(name = "ISSUE_NO", length = 20)
    @NotNull
    private String UserOrderCancle.issueNo;
    
    @Column(name = "TICKET_NUM")
    @NotNull
    private BigDecimal UserOrderCancle.ticketNum;
    
    @Column(name = "TOTAL_MONEY", precision = 15, scale = 2)
    @NotNull
    private BigDecimal UserOrderCancle.totalMoney;
    
    @Column(name = "PARTNER_ID", length = 30, unique = true)
    @NotNull
    private String UserOrderCancle.partnerId;
    
    @Column(name = "PORDER_ID", length = 30, unique = true)
    @NotNull
    private String UserOrderCancle.porderId;
    
    @Column(name = "USER_ID", length = 30)
    @NotNull
    private String UserOrderCancle.userId;
    
    @Column(name = "ORDER_STATUS")
    @NotNull
    private BigDecimal UserOrderCancle.orderStatus;
    
    @Column(name = "SUC_NUM")
    @NotNull
    private BigDecimal UserOrderCancle.sucNum;
    
    @Column(name = "SUC_MONEY", precision = 15, scale = 2)
    @NotNull
    private BigDecimal UserOrderCancle.sucMoney;
    
    @Column(name = "FAIL_NUM")
    @NotNull
    private BigDecimal UserOrderCancle.failNum;
    
    @Column(name = "FAIL_MONEY", precision = 15, scale = 2)
    @NotNull
    private BigDecimal UserOrderCancle.failMoney;
    
    @Column(name = "PRIZE_STATUS")
    @NotNull
    private BigDecimal UserOrderCancle.prizeStatus;
    
    @Column(name = "PRIZE_TYPE")
    @NotNull
    private BigDecimal UserOrderCancle.prizeType;
    
    @Column(name = "PAY_TYPE")
    @NotNull
    private BigDecimal UserOrderCancle.payType;
    
    @Column(name = "CREATE_TIME")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date UserOrderCancle.createTime;
    
    @Column(name = "PRIZE_MONEY", precision = 15, scale = 2)
    @NotNull
    private BigDecimal UserOrderCancle.prizeMoney;
    
    @Column(name = "PRIZE_MONEY_AFTER_TAX", precision = 15, scale = 2)
    @NotNull
    private BigDecimal UserOrderCancle.prizeMoneyAfterTax;
    
    public String UserOrderCancle.getGameId() {
        return gameId;
    }
    
    public void UserOrderCancle.setGameId(String gameId) {
        this.gameId = gameId;
    }
    
    public String UserOrderCancle.getIssueNo() {
        return issueNo;
    }
    
    public void UserOrderCancle.setIssueNo(String issueNo) {
        this.issueNo = issueNo;
    }
    
    public BigDecimal UserOrderCancle.getTicketNum() {
        return ticketNum;
    }
    
    public void UserOrderCancle.setTicketNum(BigDecimal ticketNum) {
        this.ticketNum = ticketNum;
    }
    
    public BigDecimal UserOrderCancle.getTotalMoney() {
        return totalMoney;
    }
    
    public void UserOrderCancle.setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
    
    public String UserOrderCancle.getPartnerId() {
        return partnerId;
    }
    
    public void UserOrderCancle.setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
    
    public String UserOrderCancle.getPorderId() {
        return porderId;
    }
    
    public void UserOrderCancle.setPorderId(String porderId) {
        this.porderId = porderId;
    }
    
    public String UserOrderCancle.getUserId() {
        return userId;
    }
    
    public void UserOrderCancle.setUserId(String userId) {
        this.userId = userId;
    }
    
    public BigDecimal UserOrderCancle.getOrderStatus() {
        return orderStatus;
    }
    
    public void UserOrderCancle.setOrderStatus(BigDecimal orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    public BigDecimal UserOrderCancle.getSucNum() {
        return sucNum;
    }
    
    public void UserOrderCancle.setSucNum(BigDecimal sucNum) {
        this.sucNum = sucNum;
    }
    
    public BigDecimal UserOrderCancle.getSucMoney() {
        return sucMoney;
    }
    
    public void UserOrderCancle.setSucMoney(BigDecimal sucMoney) {
        this.sucMoney = sucMoney;
    }
    
    public BigDecimal UserOrderCancle.getFailNum() {
        return failNum;
    }
    
    public void UserOrderCancle.setFailNum(BigDecimal failNum) {
        this.failNum = failNum;
    }
    
    public BigDecimal UserOrderCancle.getFailMoney() {
        return failMoney;
    }
    
    public void UserOrderCancle.setFailMoney(BigDecimal failMoney) {
        this.failMoney = failMoney;
    }
    
    public BigDecimal UserOrderCancle.getPrizeStatus() {
        return prizeStatus;
    }
    
    public void UserOrderCancle.setPrizeStatus(BigDecimal prizeStatus) {
        this.prizeStatus = prizeStatus;
    }
    
    public BigDecimal UserOrderCancle.getPrizeType() {
        return prizeType;
    }
    
    public void UserOrderCancle.setPrizeType(BigDecimal prizeType) {
        this.prizeType = prizeType;
    }
    
    public BigDecimal UserOrderCancle.getPayType() {
        return payType;
    }
    
    public void UserOrderCancle.setPayType(BigDecimal payType) {
        this.payType = payType;
    }
    
    public Date UserOrderCancle.getCreateTime() {
        return createTime;
    }
    
    public void UserOrderCancle.setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public BigDecimal UserOrderCancle.getPrizeMoney() {
        return prizeMoney;
    }
    
    public void UserOrderCancle.setPrizeMoney(BigDecimal prizeMoney) {
        this.prizeMoney = prizeMoney;
    }
    
    public BigDecimal UserOrderCancle.getPrizeMoneyAfterTax() {
        return prizeMoneyAfterTax;
    }
    
    public void UserOrderCancle.setPrizeMoneyAfterTax(BigDecimal prizeMoneyAfterTax) {
        this.prizeMoneyAfterTax = prizeMoneyAfterTax;
    }
    
}
