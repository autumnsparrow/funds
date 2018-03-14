package com.palmcommerce.funds.trade.ibatis.bean;

import java.util.Date;

/**
 * UserCharge entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class UserCharge implements java.io.Serializable {

	// Fields

	private Long ichargeId;
	private Double money;
	private Date chrgeTime;
	private Date creatTime;
	private String userId;
	private String payComId;
	private String payOrderId;
	
	private String returnCode;
	private String returnMsg;

	private String status;
	
	private int userChargeType;
	
	
	
	
	
	// Constructors

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public int getUserChargeType() {
		return userChargeType;
	}

	public void setUserChargeType(int userChargeType) {
		this.userChargeType = userChargeType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/** default constructor */
	public UserCharge() {
	}

	/** minimal constructor */
	public UserCharge(Long ichargeId, Double money, Date chrgeTime,
			Date creatTime, String userId, String payComId) {
		this.ichargeId = ichargeId;
		this.money = money;
		this.chrgeTime = chrgeTime;
		this.creatTime = creatTime;
		this.userId = userId;
		this.payComId = payComId;
	}

	/** full constructor */
	public UserCharge(Long ichargeId, Double money, Date chrgeTime,
			Date creatTime, String userId, String payComId, String payOrderId,
			String returnMsg) {
		this.ichargeId = ichargeId;
		this.money = money;
		this.chrgeTime = chrgeTime;
		this.creatTime = creatTime;
		this.userId = userId;
		this.payComId = payComId;
		this.payOrderId = payOrderId;
		this.returnMsg = returnMsg;
	}

	// Property accessors

	public Long getIchargeId() {
		return this.ichargeId;
	}

	public void setIchargeId(Long ichargeId) {
		this.ichargeId = ichargeId;
	}

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Date getChrgeTime() {
		return this.chrgeTime;
	}

	public void setChrgeTime(Date chrgeTime) {
		this.chrgeTime = chrgeTime;
	}

	public Date getCreatTime() {
		return this.creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPayComId() {
		return this.payComId;
	}

	public void setPayComId(String payComId) {
		this.payComId = payComId;
	}

	public String getPayOrderId() {
		return this.payOrderId;
	}

	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}

	public String getReturnMsg() {
		return this.returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

}