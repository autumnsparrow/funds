package com.palmcommerce.funds.trade.ibatis.bean;

public class BankBindBean {
	
	private String seriesNo;
	private String bankCode;
	private String userId;
	private int bindType;
	private String userName;
	private String bankNumber;
	private String modifyTime;
	public String getSeriesNo() {
		return seriesNo;
	}
	public void setSeriesNo(String seriesNo) {
		this.seriesNo = seriesNo;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getBindType() {
		return bindType;
	}
	public void setBindType(int bindType) {
		this.bindType = bindType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBankNumber() {
		return bankNumber;
	}
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	

}
