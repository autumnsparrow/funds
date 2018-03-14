package com.palmcommerce.funds.alert.service.sms;

import java.util.Properties;



/**
 * 
 * 
 */
public class SmsServerConfig {
	
	String remoteUrl;
	String userId;
	String password;
	String bizType;
	/**
	 * @return the remoteUrl
	 */
	public String getRemoteUrl() {
		return remoteUrl;
	}
	/**
	 * @param remoteUrl the remoteUrl to set
	 */
	public void setRemoteUrl(String remoteUrl) {
		this.remoteUrl = remoteUrl;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the bizType
	 */
	public String getBizType() {
		return bizType;
	}
	/**
	 * @param bizType the bizType to set
	 */
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	
}
