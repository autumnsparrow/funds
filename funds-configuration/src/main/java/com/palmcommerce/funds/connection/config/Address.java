/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Sep 30, 2013
 *
 */
package com.palmcommerce.funds.connection.config;

import java.beans.ConstructorProperties;

/**
 * @author sparrow
 *
 */
public  class Address implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2783226082160557217L;
	String ip;
	int port;
	
	@ConstructorProperties({"ip","port"})
	public Address(String ip, int port) {
		super();
		this.ip = ip;
		this.port = port;
	}
	
	
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	@Override
	public String toString() {
		return "Address [ip=" + ip + ", port=" + port + "]";
	}
	
}
