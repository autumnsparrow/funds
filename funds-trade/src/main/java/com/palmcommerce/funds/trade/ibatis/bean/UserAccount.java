package com.palmcommerce.funds.trade.ibatis.bean;

import java.util.Date;

public class UserAccount {

	private String userId;
	private double cash;
	private double gift;
	private int status;
	private Date updateTime;
	private int type;
	private double freezeCash;
	private double creditMoney;
	private double gGreditMoney;
	private Date effiectiveTime;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public double getCash() {
		return cash;
	}
	public void setCash(double cash) {
		this.cash = cash;
	}
	public double getGift() {
		return gift;
	}
	public void setGift(double gift) {
		this.gift = gift;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getFreezeCash() {
		return freezeCash;
	}
	public void setFreezeCash(double freezeCash) {
		this.freezeCash = freezeCash;
	}
	public double getCreditMoney() {
		return creditMoney;
	}
	public void setCreditMoney(double creditMoney) {
		this.creditMoney = creditMoney;
	}
	public double getgGreditMoney() {
		return gGreditMoney;
	}
	public void setgGreditMoney(double gGreditMoney) {
		this.gGreditMoney = gGreditMoney;
	}
	public Date getEffiectiveTime() {
		return effiectiveTime;
	}
	public void setEffiectiveTime(Date effiectiveTime) {
		this.effiectiveTime = effiectiveTime;
	}
	
	
	
	
	
	
}
