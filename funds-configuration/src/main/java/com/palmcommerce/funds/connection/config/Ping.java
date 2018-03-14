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
public  class Ping implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7407877654392679821L;
	int interval;
	String clazz;
	
	
	@ConstructorProperties({"interval","clazz"})
	public Ping(int interval, String clazz) {
		super();
		this.interval = interval;
		this.clazz = clazz;
	}
	
	
	public Ping() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	@Override
	public String toString() {
		return "Ping [interval=" + interval + ", clazz=" + clazz + "]";
	}
	
	
	
	
}
