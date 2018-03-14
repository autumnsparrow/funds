// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserOrder100;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect UserOrder100_Roo_DbManaged {
    
    @Column(name = "GAME_ID", length = 10)
    @NotNull
    private String UserOrder100.gameId;
    
    @Column(name = "ISSUE_NO", length = 20)
    @NotNull
    private String UserOrder100.issueNo;
    
    @Column(name = "TICKET_NUM")
    @NotNull
    private BigDecimal UserOrder100.ticketNum;
    
    @Column(name = "TOTAL_MONEY", precision = 15, scale = 2)
    @NotNull
    private BigDecimal UserOrder100.totalMoney;
    
    @Column(name = "PARTNER_ID", length = 30, unique = true)
    @NotNull
    private String UserOrder100.partnerId;
    
    @Column(name = "PORDER_ID", length = 30, unique = true)
    @NotNull
    private String UserOrder100.porderId;
    
    @Column(name = "USER_ID", length = 30)
    @NotNull
    private String UserOrder100.userId;
    
    @Column(name = "ORDER_STATUS")
    @NotNull
    private BigDecimal UserOrder100.orderStatus;
    
    @Column(name = "SUC_NUM")
    @NotNull
    private BigDecimal UserOrder100.sucNum;
    
    @Column(name = "SUC_MONEY", precision = 15, scale = 2)
    @NotNull
    private BigDecimal UserOrder100.sucMoney;
    
    @Column(name = "FAIL_NUM")
    @NotNull
    private BigDecimal UserOrder100.failNum;
    
    @Column(name = "FAIL_MONEY", precision = 15, scale = 2)
    @NotNull
    private BigDecimal UserOrder100.failMoney;
    
    @Column(name = "PRIZE_STATUS")
    @NotNull
    private BigDecimal UserOrder100.prizeStatus;
    
    @Column(name = "PRIZE_TYPE")
    @NotNull
    private BigDecimal UserOrder100.prizeType;
    
    @Column(name = "PAY_TYPE")
    @NotNull
    private BigDecimal UserOrder100.payType;
    
    @Column(name = "CREATE_TIME")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date UserOrder100.createTime;
    
    @Column(name = "PRIZE_MONEY", precision = 15, scale = 2)
    @NotNull
    private BigDecimal UserOrder100.prizeMoney;
    
    @Column(name = "PRIZE_MONEY_AFTER_TAX", precision = 15, scale = 2)
    @NotNull
    private BigDecimal UserOrder100.prizeMoneyAfterTax;
    
    public String UserOrder100.getGameId() {
        return gameId;
    }
    
    public void UserOrder100.setGameId(String gameId) {
        this.gameId = gameId;
    }
    
    public String UserOrder100.getIssueNo() {
        return issueNo;
    }
    
    public void UserOrder100.setIssueNo(String issueNo) {
        this.issueNo = issueNo;
    }
    
    public BigDecimal UserOrder100.getTicketNum() {
        return ticketNum;
    }
    
    public void UserOrder100.setTicketNum(BigDecimal ticketNum) {
        this.ticketNum = ticketNum;
    }
    
    public BigDecimal UserOrder100.getTotalMoney() {
        return totalMoney;
    }
    
    public void UserOrder100.setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
    
    public String UserOrder100.getPartnerId() {
        return partnerId;
    }
    
    public void UserOrder100.setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
    
    public String UserOrder100.getPorderId() {
        return porderId;
    }
    
    public void UserOrder100.setPorderId(String porderId) {
        this.porderId = porderId;
    }
    
    public String UserOrder100.getUserId() {
        return userId;
    }
    
    public void UserOrder100.setUserId(String userId) {
        this.userId = userId;
    }
    
    public BigDecimal UserOrder100.getOrderStatus() {
        return orderStatus;
    }
    
    public void UserOrder100.setOrderStatus(BigDecimal orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    public BigDecimal UserOrder100.getSucNum() {
        return sucNum;
    }
    
    public void UserOrder100.setSucNum(BigDecimal sucNum) {
        this.sucNum = sucNum;
    }
    
    public BigDecimal UserOrder100.getSucMoney() {
        return sucMoney;
    }
    
    public void UserOrder100.setSucMoney(BigDecimal sucMoney) {
        this.sucMoney = sucMoney;
    }
    
    public BigDecimal UserOrder100.getFailNum() {
        return failNum;
    }
    
    public void UserOrder100.setFailNum(BigDecimal failNum) {
        this.failNum = failNum;
    }
    
    public BigDecimal UserOrder100.getFailMoney() {
        return failMoney;
    }
    
    public void UserOrder100.setFailMoney(BigDecimal failMoney) {
        this.failMoney = failMoney;
    }
    
    public BigDecimal UserOrder100.getPrizeStatus() {
        return prizeStatus;
    }
    
    public void UserOrder100.setPrizeStatus(BigDecimal prizeStatus) {
        this.prizeStatus = prizeStatus;
    }
    
    public BigDecimal UserOrder100.getPrizeType() {
        return prizeType;
    }
    
    public void UserOrder100.setPrizeType(BigDecimal prizeType) {
        this.prizeType = prizeType;
    }
    
    public BigDecimal UserOrder100.getPayType() {
        return payType;
    }
    
    public void UserOrder100.setPayType(BigDecimal payType) {
        this.payType = payType;
    }
    
    public Date UserOrder100.getCreateTime() {
        return createTime;
    }
    
    public void UserOrder100.setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public BigDecimal UserOrder100.getPrizeMoney() {
        return prizeMoney;
    }
    
    public void UserOrder100.setPrizeMoney(BigDecimal prizeMoney) {
        this.prizeMoney = prizeMoney;
    }
    
    public BigDecimal UserOrder100.getPrizeMoneyAfterTax() {
        return prizeMoneyAfterTax;
    }
    
    public void UserOrder100.setPrizeMoneyAfterTax(BigDecimal prizeMoneyAfterTax) {
        this.prizeMoneyAfterTax = prizeMoneyAfterTax;
    }
    
}
