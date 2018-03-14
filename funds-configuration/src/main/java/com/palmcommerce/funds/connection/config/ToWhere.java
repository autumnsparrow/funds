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
public  class ToWhere implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3132192903832159341L;
	/**
	 * 
	 */
	
	String where;
	Connection connection;
	Ping ping;
	Address address;
	
	
	public ToWhere() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getWhere() {
		return where;
	}
	public void setWhere(String where) {
		this.where = where;
	}
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public Ping getPing() {
		return ping;
	}
	public void setPing(Ping ping) {
		this.ping = ping;
	}
	
	@Override
	public String toString() {
		return "\nToWhere [where=" + where + ", connection=" + connection
				+ ", ping=" + ping + ", address=" + address + "]";
	}
	
	
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * JMX
	 */
	int connectionMax;
	int connectionMin;
	int pingInterval;
	String pingClazz;
	String addressIp;
	int addressPort;

	
	@ConstructorProperties({"where","connectionMax","connectionMin","pingInterval","pingClazz","addressIp","addressPort"})
	public ToWhere(String where, int connectionMax, int connectionMin,
			int pingInterval, String pingClazz, String addressIp,
			int addressPort) {
		super();
		this.where = where;
		this.connectionMax = connectionMax;
		this.connectionMin = connectionMin;
		this.pingInterval = pingInterval;
		this.pingClazz = pingClazz;
		this.addressIp = addressIp;
		this.addressPort = addressPort;
	}


	public void setClone(ToWhere t){
		this.setAddressIp(t.addressIp);
		this.setAddressPort(t.addressPort);
		this.setConnectionMax(t.connectionMax);
		this.setConnectionMin(t.connectionMin);
		this.setPingClazz(t.pingClazz);
		this.setPingInterval(t.pingInterval);
	}





	/**
	 * @param connectionMax the connectionMax to set
	 */
	public void setConnectionMax(int connectionMax) {
		this.connection.max = connectionMax;
	}







	/**
	 * @param connectionMin the connectionMin to set
	 */
	public void setConnectionMin(int connectionMin) {
		this.connection.min = connectionMin;
	}







	/**
	 * @param pingInterval the pingInterval to set
	 */
	public void setPingInterval(int pingInterval) {
		this.ping.interval = pingInterval;
	}







	/**
	 * @param pingClazz the pingClazz to set
	 */
	public void setPingClazz(String pingClazz) {
		this.ping.clazz = pingClazz;
	}







	/**
	 * @param addressIp the addressIp to set
	 */
	public void setAddressIp(String addressIp) {
		this.address.ip = addressIp;
	}







	/**
	 * @param addressPort the addressPort to set
	 */
	public void setAddressPort(int addressPort) {
		this.address.port = addressPort;
	}







	/**
	 * @return the connectionMax
	 */
	public int getConnectionMax() {
		return this.connection.max;
	}







	/**
	 * @return the connectionMin
	 */
	public int getConnectionMin() {
		return this.connection.min;
	}







	/**
	 * @return the pingInterval
	 */
	public int getPingInterval() {
		return this.ping.getInterval();
	}







	/**
	 * @return the pingClazz
	 */
	public String getPingClazz() {
		return this.ping.getClazz();
	}







	/**
	 * @return the addressIp
	 */
	public String getAddressIp() {
		return this.address.ip;
	}







	/**
	 * @return the addressPort
	 */
	public int getAddressPort() {
		return this.address.port;
	}
	
	
	
	
}
