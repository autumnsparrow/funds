// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserOrder101;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect UserOrder101_Roo_DbManaged {
    
    @Column(name = "GAME_ID", length = 10)
    @NotNull
    private String UserOrder101.gameId;
    
    @Column(name = "ISSUE_NO", length = 20)
    @NotNull
    private String UserOrder101.issueNo;
    
    @Column(name = "TICKET_NUM")
    @NotNull
    private BigDecimal UserOrder101.ticketNum;
    
    @Column(name = "TOTAL_MONEY", precision = 15, scale = 2)
    @NotNull
    private BigDecimal UserOrder101.totalMoney;
    
    @Column(name = "PARTNER_ID", length = 30, unique = true)
    @NotNull
    private String UserOrder101.partnerId;
    
    @Column(name = "PORDER_ID", length = 30, unique = true)
    @NotNull
    private String UserOrder101.porderId;
    
    @Column(name = "USER_ID", length = 30)
    @NotNull
    private String UserOrder101.userId;
    
    @Column(name = "ORDER_STATUS")
    @NotNull
    private BigDecimal UserOrder101.orderStatus;
    
    @Column(name = "SUC_NUM")
    @NotNull
    private BigDecimal UserOrder101.sucNum;
    
    @Column(name = "SUC_MONEY", precision = 15, scale = 2)
    @NotNull
    private BigDecimal UserOrder101.sucMoney;
    
    @Column(name = "FAIL_NUM")
    @NotNull
    private BigDecimal UserOrder101.failNum;
    
    @Column(name = "FAIL_MONEY", precision = 15, scale = 2)
    @NotNull
    private BigDecimal UserOrder101.failMoney;
    
    @Column(name = "PRIZE_STATUS")
    @NotNull
    private BigDecimal UserOrder101.prizeStatus;
    
    @Column(name = "PRIZE_TYPE")
    @NotNull
    private BigDecimal UserOrder101.prizeType;
    
    @Column(name = "PAY_TYPE")
    @NotNull
    private BigDecimal UserOrder101.payType;
    
    @Column(name = "CREATE_TIME")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date UserOrder101.createTime;
    
    @Column(name = "PRIZE_MONEY", precision = 15, scale = 2)
    @NotNull
    private BigDecimal UserOrder101.prizeMoney;
    
    @Column(name = "PRIZE_MONEY_AFTER_TAX", precision = 15, scale = 2)
    @NotNull
    private BigDecimal UserOrder101.prizeMoneyAfterTax;
    
    public String UserOrder101.getGameId() {
        return gameId;
    }
    
    public void UserOrder101.setGameId(String gameId) {
        this.gameId = gameId;
    }
    
    public String UserOrder101.getIssueNo() {
        return issueNo;
    }
    
    public void UserOrder101.setIssueNo(String issueNo) {
        this.issueNo = issueNo;
    }
    
    public BigDecimal UserOrder101.getTicketNum() {
        return ticketNum;
    }
    
    public void UserOrder101.setTicketNum(BigDecimal ticketNum) {
        this.ticketNum = ticketNum;
    }
    
    public BigDecimal UserOrder101.getTotalMoney() {
        return totalMoney;
    }
    
    public void UserOrder101.setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
    
    public String UserOrder101.getPartnerId() {
        return partnerId;
    }
    
    public void UserOrder101.setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
    
    public String UserOrder101.getPorderId() {
        return porderId;
    }
    
    public void UserOrder101.setPorderId(String porderId) {
        this.porderId = porderId;
    }
    
    public String UserOrder101.getUserId() {
        return userId;
    }
    
    public void UserOrder101.setUserId(String userId) {
        this.userId = userId;
    }
    
    public BigDecimal UserOrder101.getOrderStatus() {
        return orderStatus;
    }
    
    public void UserOrder101.setOrderStatus(BigDecimal orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    public BigDecimal UserOrder101.getSucNum() {
        return sucNum;
    }
    
    public void UserOrder101.setSucNum(BigDecimal sucNum) {
        this.sucNum = sucNum;
    }
    
    public BigDecimal UserOrder101.getSucMoney() {
        return sucMoney;
    }
    
    public void UserOrder101.setSucMoney(BigDecimal sucMoney) {
        this.sucMoney = sucMoney;
    }
    
    public BigDecimal UserOrder101.getFailNum() {
        return failNum;
    }
    
    public void UserOrder101.setFailNum(BigDecimal failNum) {
        this.failNum = failNum;
    }
    
    public BigDecimal UserOrder101.getFailMoney() {
        return failMoney;
    }
    
    public void UserOrder101.setFailMoney(BigDecimal failMoney) {
        this.failMoney = failMoney;
    }
    
    public BigDecimal UserOrder101.getPrizeStatus() {
        return prizeStatus;
    }
    
    public void UserOrder101.setPrizeStatus(BigDecimal prizeStatus) {
        this.prizeStatus = prizeStatus;
    }
    
    public BigDecimal UserOrder101.getPrizeType() {
        return prizeType;
    }
    
    public void UserOrder101.setPrizeType(BigDecimal prizeType) {
        this.prizeType = prizeType;
    }
    
    public BigDecimal UserOrder101.getPayType() {
        return payType;
    }
    
    public void UserOrder101.setPayType(BigDecimal payType) {
        this.payType = payType;
    }
    
    public Date UserOrder101.getCreateTime() {
        return createTime;
    }
    
    public void UserOrder101.setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public BigDecimal UserOrder101.getPrizeMoney() {
        return prizeMoney;
    }
    
    public void UserOrder101.setPrizeMoney(BigDecimal prizeMoney) {
        this.prizeMoney = prizeMoney;
    }
    
    public BigDecimal UserOrder101.getPrizeMoneyAfterTax() {
        return prizeMoneyAfterTax;
    }
    
    public void UserOrder101.setPrizeMoneyAfterTax(BigDecimal prizeMoneyAfterTax) {
        this.prizeMoneyAfterTax = prizeMoneyAfterTax;
    }
    
}
