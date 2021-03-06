// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserOrder103;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect UserOrder103_Roo_DbManaged {
    
    @Column(name = "GAME_ID", length = 10)
    @NotNull
    private String UserOrder103.gameId;
    
    @Column(name = "ISSUE_NO", length = 20)
    @NotNull
    private String UserOrder103.issueNo;
    
    @Column(name = "TICKET_NUM")
    @NotNull
    private BigDecimal UserOrder103.ticketNum;
    
    @Column(name = "TOTAL_MONEY", precision = 15, scale = 2)
    @NotNull
    private BigDecimal UserOrder103.totalMoney;
    
    @Column(name = "PARTNER_ID", length = 30, unique = true)
    @NotNull
    private String UserOrder103.partnerId;
    
    @Column(name = "PORDER_ID", length = 30, unique = true)
    @NotNull
    private String UserOrder103.porderId;
    
    @Column(name = "USER_ID", length = 30)
    @NotNull
    private String UserOrder103.userId;
    
    @Column(name = "ORDER_STATUS")
    @NotNull
    private BigDecimal UserOrder103.orderStatus;
    
    @Column(name = "SUC_NUM")
    @NotNull
    private BigDecimal UserOrder103.sucNum;
    
    @Column(name = "SUC_MONEY", precision = 15, scale = 2)
    @NotNull
    private BigDecimal UserOrder103.sucMoney;
    
    @Column(name = "FAIL_NUM")
    @NotNull
    private BigDecimal UserOrder103.failNum;
    
    @Column(name = "FAIL_MONEY", precision = 15, scale = 2)
    @NotNull
    private BigDecimal UserOrder103.failMoney;
    
    @Column(name = "PRIZE_STATUS")
    @NotNull
    private BigDecimal UserOrder103.prizeStatus;
    
    @Column(name = "PRIZE_TYPE")
    @NotNull
    private BigDecimal UserOrder103.prizeType;
    
    @Column(name = "PAY_TYPE")
    @NotNull
    private BigDecimal UserOrder103.payType;
    
    @Column(name = "CREATE_TIME")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date UserOrder103.createTime;
    
    @Column(name = "PRIZE_MONEY", precision = 15, scale = 2)
    @NotNull
    private BigDecimal UserOrder103.prizeMoney;
    
    @Column(name = "PRIZE_MONEY_AFTER_TAX", precision = 15, scale = 2)
    @NotNull
    private BigDecimal UserOrder103.prizeMoneyAfterTax;
    
    public String UserOrder103.getGameId() {
        return gameId;
    }
    
    public void UserOrder103.setGameId(String gameId) {
        this.gameId = gameId;
    }
    
    public String UserOrder103.getIssueNo() {
        return issueNo;
    }
    
    public void UserOrder103.setIssueNo(String issueNo) {
        this.issueNo = issueNo;
    }
    
    public BigDecimal UserOrder103.getTicketNum() {
        return ticketNum;
    }
    
    public void UserOrder103.setTicketNum(BigDecimal ticketNum) {
        this.ticketNum = ticketNum;
    }
    
    public BigDecimal UserOrder103.getTotalMoney() {
        return totalMoney;
    }
    
    public void UserOrder103.setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
    
    public String UserOrder103.getPartnerId() {
        return partnerId;
    }
    
    public void UserOrder103.setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
    
    public String UserOrder103.getPorderId() {
        return porderId;
    }
    
    public void UserOrder103.setPorderId(String porderId) {
        this.porderId = porderId;
    }
    
    public String UserOrder103.getUserId() {
        return userId;
    }
    
    public void UserOrder103.setUserId(String userId) {
        this.userId = userId;
    }
    
    public BigDecimal UserOrder103.getOrderStatus() {
        return orderStatus;
    }
    
    public void UserOrder103.setOrderStatus(BigDecimal orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    public BigDecimal UserOrder103.getSucNum() {
        return sucNum;
    }
    
    public void UserOrder103.setSucNum(BigDecimal sucNum) {
        this.sucNum = sucNum;
    }
    
    public BigDecimal UserOrder103.getSucMoney() {
        return sucMoney;
    }
    
    public void UserOrder103.setSucMoney(BigDecimal sucMoney) {
        this.sucMoney = sucMoney;
    }
    
    public BigDecimal UserOrder103.getFailNum() {
        return failNum;
    }
    
    public void UserOrder103.setFailNum(BigDecimal failNum) {
        this.failNum = failNum;
    }
    
    public BigDecimal UserOrder103.getFailMoney() {
        return failMoney;
    }
    
    public void UserOrder103.setFailMoney(BigDecimal failMoney) {
        this.failMoney = failMoney;
    }
    
    public BigDecimal UserOrder103.getPrizeStatus() {
        return prizeStatus;
    }
    
    public void UserOrder103.setPrizeStatus(BigDecimal prizeStatus) {
        this.prizeStatus = prizeStatus;
    }
    
    public BigDecimal UserOrder103.getPrizeType() {
        return prizeType;
    }
    
    public void UserOrder103.setPrizeType(BigDecimal prizeType) {
        this.prizeType = prizeType;
    }
    
    public BigDecimal UserOrder103.getPayType() {
        return payType;
    }
    
    public void UserOrder103.setPayType(BigDecimal payType) {
        this.payType = payType;
    }
    
    public Date UserOrder103.getCreateTime() {
        return createTime;
    }
    
    public void UserOrder103.setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public BigDecimal UserOrder103.getPrizeMoney() {
        return prizeMoney;
    }
    
    public void UserOrder103.setPrizeMoney(BigDecimal prizeMoney) {
        this.prizeMoney = prizeMoney;
    }
    
    public BigDecimal UserOrder103.getPrizeMoneyAfterTax() {
        return prizeMoneyAfterTax;
    }
    
    public void UserOrder103.setPrizeMoneyAfterTax(BigDecimal prizeMoneyAfterTax) {
        this.prizeMoneyAfterTax = prizeMoneyAfterTax;
    }
    
}
