/**
 * 
 */
package com.palmcommerce.funds.alert.service.impl;

/**
 * @author Administrator
 *
 */
public class AlertMessage {
	/**
	 * 
	 */
	public AlertMessage() {
		// TODO Auto-generated constructor stub
	}
	String operatorName;  
	String userName;      
	String tradeName;  
	
	int numbers;    
	int times;		
	
	String state;
	
	String context;
	String money;	
	String Amount;   
	
	String time;      
	String sendAddr; 
	
	
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMoney() {
		return money;
	}
	
	public int getNumbers() {
		return numbers;
	}
	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getAmount() {
		return Amount;
	}
	public void setAmount(String amount) {
		Amount = amount;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSendAddr() {
		return sendAddr;
	}
	public void setSendAddr(String sendAddr) {
		this.sendAddr = sendAddr;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	

}
