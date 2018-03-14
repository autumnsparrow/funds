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
import java.util.HashMap;



/**
 * @author sparrow
 *
 */

public  class Route{
	// from where
	String fromWhere;
	String ip;
	int port;
	HashMap<String,ToWhere> toWheres;
	
	
	
	
	@ConstructorProperties({"fromWhere","ip","port"})
	public Route(String fromWhere, String ip, int port) {
		super();
		this.fromWhere = fromWhere;
		this.ip = ip;
		this.port = port;
		
		
	}
	
	
	
	public Route() {
		super();
		// TODO Auto-generated constructor stub
		toWheres=new HashMap<String, ToWhere>();
	}
	public String getFromWhere() {
		return fromWhere;
	}
	public void setFromWhere(String fromWhere) {
		this.fromWhere = fromWhere;
	}
	
	public void addToWhere(ToWhere toWhere){
		this.toWheres.put(toWhere.where, toWhere);
	}
	
	public ToWhere getToWhere(String toWhere){
		return this.toWheres.get(toWhere);
	}
	
	public HashMap<String, ToWhere> getToWheres(){
		return toWheres;
	}
	
	
	@Override
	public String toString() {
		StringBuffer sb=new StringBuffer();
		sb.append("\nRoute [fromWhere=") .append(fromWhere).append(",ip=").append(ip).append(",").append("port=").append(port).append(",toWheres=");
		
		for(ToWhere toWhere:toWheres.values()){
			sb.append(toWhere.toString()).append(",");
		}
		sb.append("]\n");
		return sb.toString();
		
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
	
	
	
	
//	String
	
}
