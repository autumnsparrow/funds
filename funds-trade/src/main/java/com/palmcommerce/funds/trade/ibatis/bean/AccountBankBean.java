package com.palmcommerce.funds.trade.ibatis.bean;

import java.util.Date;

public class AccountBankBean {
	
	private long bankReportId;
	private String bankCode;
	private long correctNum;
	private double correctMoney;
	private long overNum;
	private double overMoney;
	private long lackNum;
	private double lackMoney;
	private long errorNum;
	private double errorMoney;
	private int status;
	private String tradeTime;
	private Date createTime;
	private Date operatorTime;
	
	private double totalMoney;
	
	
	
	public double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public long getBankReportId() {
		return bankReportId;
	}
	public void setBankReportId(long bankReportId) {
		this.bankReportId = bankReportId;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public long getCorrectNum() {
		return correctNum;
	}
	public void setCorrectNum(long correctNum) {
		this.correctNum = correctNum;
	}
	public double getCorrectMoney() {
		return correctMoney;
	}
	public void setCorrectMoney(double correctMoney) {
		this.correctMoney = correctMoney;
	}
	public long getOverNum() {
		return overNum;
	}
	public void setOverNum(long overNum) {
		this.overNum = overNum;
	}
	public double getOverMoney() {
		return overMoney;
	}
	public void setOverMoney(double overMoney) {
		this.overMoney = overMoney;
	}
	public long getLackNum() {
		return lackNum;
	}
	public void setLackNum(long lackNum) {
		this.lackNum = lackNum;
	}
	public double getLackMoney() {
		return lackMoney;
	}
	public void setLackMoney(double lackMoney) {
		this.lackMoney = lackMoney;
	}
	public long getErrorNum() {
		return errorNum;
	}
	public void setErrorNum(long errorNum) {
		this.errorNum = errorNum;
	}
	public double getErrorMoney() {
		return errorMoney;
	}
	public void setErrorMoney(double errorMoney) {
		this.errorMoney = errorMoney;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getOperatorTime() {
		return operatorTime;
	}
	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}
	@Override
	public String toString() {
		return "AccountBankBean [bankReportId=" + bankReportId + ", bankCode="
				+ bankCode + ", correctNum=" + correctNum + ", correctMoney="
				+ correctMoney + ", overNum=" + overNum + ", overMoney="
				+ overMoney + ", lackNum=" + lackNum + ", lackMoney="
				+ lackMoney + ", errorNum=" + errorNum + ", errorMoney="
				+ errorMoney + ", status=" + status + ", tradeTime="
				+ tradeTime + ", createTime=" + createTime + ", operatorTime="
				+ operatorTime + ", totalMoney=" + totalMoney + "]";
	}
	
	
	

}
