package com.palmcommerce.funds.trade.ibatis.bean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class PayMoneyBean extends Response{
	
	public static int ACCOUNT_TYPE_NO=0;//未对仗
	public static int ACCOUNT_TYPE_YES=1;//对仗成功
	public static int ACCOUNT_TYPE_OVER=2;//多出笔数
	public static int ACCOUNT_TYPE_LACK=3;//缺少笔数
	public static int ACCOUNT_TYPE_ERROR=4;//对仗错误
	public static int ACCOUNT_TYPE_ERROR_OLD=5;//老综合接入的数据
	public static int ACCOUNT_TYPE_CALLBACK=-1;//冲正钱数
	
	private String seriesNo;
	private String userId;
	private String userName;
	private double money;
	private String partnerId;
	private String bankCode;
	private String accountTime;
	private Date createTime;
	private int accountType;
	private double balanceMoney;
	private String cancelSerialNo;
	
	public String getCancelSerialNo() {
		return cancelSerialNo;
	}

	public void setCancelSerialNo(String cancelSerialNo) {
		this.cancelSerialNo = cancelSerialNo;
	}

	public double getBalanceMoney() {
		return balanceMoney;
	}

	public void setBalanceMoney(double balanceMoney) {
		this.balanceMoney = balanceMoney;
	}

	@Override
	public List<String> fieldNames() {
		// TODO Auto-generated method stub
		List<String> fieldNames = super.fieldNames();
		fieldNames.addAll(Arrays.asList("transcode", "seriesNo","userId","userName","money","type","certify","accountMoney"));
		return fieldNames;
	}
	
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getSeriesNo() {
		return seriesNo;
	}
	public void setSeriesNo(String seriesNo) {
		this.seriesNo = seriesNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getAccountTime() {
		return accountTime;
	}
	public void setAccountTime(String accountTime) {
		this.accountTime = accountTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return "PayMoneyBean [seriesNo=" + seriesNo + ", userId=" + userId
				+ ", userName=" + userName + ", money=" + money
				+ ", partnerId=" + partnerId + ", bankCode=" + bankCode
				+ ", accountTime=" + accountTime + ", createTime=" + createTime
				+ ", accountType=" + accountType + ", balanceMoney="
				+ balanceMoney + ", cancelSerialNo=" + cancelSerialNo + "]";
	}
	
	
	
	
	

}
