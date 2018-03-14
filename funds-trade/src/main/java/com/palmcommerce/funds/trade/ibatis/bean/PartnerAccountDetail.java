package com.palmcommerce.funds.trade.ibatis.bean;

public class PartnerAccountDetail {

	private String partnerId;
	
	private String partnerName;
	
	private String gameId;
	
	private String gameName;
	
	private double saleMoney;
	
	private double publishRatio;
	
	private double publishCharge;
	
	private double proxyRatio;
	
	private double proxyCharge;
	
	private double prizeMoney;
	
	private double pay;//应缴款
	
	private double bankPay;//已交款
	
	private double account;//累计欠款
	
	private String accountTime;
	
	

	public String getAccountTime() {
		return accountTime;
	}

	public void setAccountTime(String accountTime) {
		this.accountTime = accountTime;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public double getSaleMoney() {
		return saleMoney;
	}

	public void setSaleMoney(double saleMoney) {
		this.saleMoney = saleMoney;
	}

	public double getPublishRatio() {
		return publishRatio;
	}

	public void setPublishRatio(double publishRatio) {
		this.publishRatio = publishRatio;
	}

	public double getPublishCharge() {
		return publishCharge;
	}

	public void setPublishCharge(double publishCharge) {
		this.publishCharge = publishCharge;
	}

	public double getProxyRatio() {
		return proxyRatio;
	}

	public void setProxyRatio(double proxyRatio) {
		this.proxyRatio = proxyRatio;
	}

	public double getProxyCharge() {
		return proxyCharge;
	}

	public void setProxyCharge(double proxyCharge) {
		this.proxyCharge = proxyCharge;
	}

	public double getPrizeMoney() {
		return prizeMoney;
	}

	public void setPrizeMoney(double prizeMoney) {
		this.prizeMoney = prizeMoney;
	}

	public double getPay() {
		return pay;
	}

	public void setPay(double pay) {
		this.pay = pay;
	}

	public double getBankPay() {
		return bankPay;
	}

	public void setBankPay(double bankPay) {
		this.bankPay = bankPay;
	}

	public double getAccount() {
		return account;
	}

	public void setAccount(double account) {
		this.account = account;
	}
	
	
}
