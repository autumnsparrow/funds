/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 15, 2013
 *
 */
package com.palmcommerce.funds.configuration.v2;

import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;

/**
 * @author sparrow
 *
 *
 *
 *<RouteRuleEntry>
			<ToCode/>
			<Ip/>
			<Port/>
			<Bank/>
			<UserIdLength/>
			<UserIdFlag/>
			<ForwardFlag>true</ForwardFlag>
	<RouteRuleEntry>
 */

@ObjectCreate(pattern="RouteRuleResult/RouteRuleEntry")
public class RouteRuleEntry {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RouteRuleEntry [toCode=" + toCode + ", ip=" + ip + ", port="
				+ port + ", bank=" + bank + ", userIdLength=" + userIdLength
				+ ", userIdFlag=" + userIdFlag + "]";
	}
	/**
	 * 
	 */
	public RouteRuleEntry() {
		// TODO Auto-generated constructor stub
	}
	
	
	@BeanPropertySetter(pattern="RouteRuleResult/RouteRuleEntry/ToCode")
	String toCode;
	@BeanPropertySetter(pattern="RouteRuleResult/RouteRuleEntry/Ip")
	String ip;
	@BeanPropertySetter(pattern="RouteRuleResult/RouteRuleEntry/Port")
	int port;
	@BeanPropertySetter(pattern="RouteRuleResult/RouteRuleEntry/Bank")
	boolean bank;
	@BeanPropertySetter(pattern="RouteRuleResult/RouteRuleEntry/UserIdLength")
	int userIdLength;
	@BeanPropertySetter(pattern="RouteRuleResult/RouteRuleEntry/UserIdFlag")
	String userIdFlag;
	@BeanPropertySetter(pattern="RouteRuleResult/RouteRuleEntry/ForwardFlag")
	boolean forwardFlag;
	
	
	/**
	 * @return the forwardFlag
	 */
	public boolean isForwardFlag() {
		return forwardFlag;
	}
	/**
	 * @param forwardFlag the forwardFlag to set
	 */
	public void setForwardFlag(boolean forwardFlag) {
		this.forwardFlag = forwardFlag;
	}
	/**
	 * 
	 * 
	 * 
	 * @param userId
	 * @return
	 */
	public boolean checkUserId(String userId){
		boolean ret=false;
		if(userId!=null){
			if(userId.length()==userIdLength&&(userIdFlag.length()>0&&userIdFlag!=null)){
				if(userIdFlag.equals("*")||userId.startsWith(userIdFlag))
						ret=true;
			}
		}
		return ret;
			
	}
	
	/**
	 * @return the userIdLength
	 */
	public int getUserIdLength() {
		return userIdLength;
	}
	/**
	 * @param userIdLength the userIdLength to set
	 */
	public void setUserIdLength(int userIdLength) {
		this.userIdLength = userIdLength;
	}
	/**
	 * @return the userIdFlag
	 */
	public String getUserIdFlag() {
		return userIdFlag;
	}
	/**
	 * @param userIdFlag the userIdFlag to set
	 */
	public void setUserIdFlag(String userIdFlag) {
		this.userIdFlag = userIdFlag;
	}
	/**
	 * @return the isBank
	 */
	public boolean isBank() {
		return bank;
	}
	/**
	 * @param isBank the isBank to set
	 */
	public void setBank(boolean isBank) {
		this.bank = isBank;
	}
	/**
	 * @return the toCode
	 */
	public String getToCode() {
		return toCode;
	}
	/**
	 * @param toCode the toCode to set
	 */
	public void setToCode(String toCode) {
		this.toCode = toCode;
	}
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}
	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}
	
	
	

}
