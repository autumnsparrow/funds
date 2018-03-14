/**
 * 
 */
package com.palmcommerce.funds.trade.ibatis.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sparrow
 *
 */
public class UserAccountLogBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5550983604172750567L;
	/**
	 * 
	 */
	public UserAccountLogBean() {
		// TODO Auto-generated constructor stub
	}
	
	long logId;
	double money;
	String optCode;
	String userId;
	Date createDate;
	double cash;
	double gift;
	long forigenId;
	public long getLogId() {
		return logId;
	}
	public void setLogId(long logId) {
		this.logId = logId;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getOptCode() {
		return optCode;
	}
	public void setOptCode(String optCode) {
		this.optCode = optCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	public long getForigenId() {
		return forigenId;
	}
	public void setForigenId(long forigenId) {
		this.forigenId = forigenId;
	}
	
	

}
