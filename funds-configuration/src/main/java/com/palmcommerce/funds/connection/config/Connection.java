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
public  class Connection implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3234242267657380502L;
	int max;
	int min;
	
	@ConstructorProperties({"max","min"})
	public Connection(int max, int min) {
		super();
		this.max = max;
		this.min = min;
	}
	
	
	public Connection() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	@Override
	public String toString() {
		return "Connection [max=" + max + ", min=" + min + "]";
	}
	
	
}

