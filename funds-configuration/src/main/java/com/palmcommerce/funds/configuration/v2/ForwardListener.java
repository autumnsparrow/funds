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
import org.apache.commons.digester3.annotations.rules.SetProperty;

/**
 * @author sparrow
 *
 */
@ObjectCreate(pattern="ServerRules/ForwardListener")
public class ForwardListener {

	/**
	 <Listener port="80" ip="192.168.1.86" maxConnections="100" minConnections="10" pingInterval="5" clazz="com.palmcommerce.protocol.impl.p2t.P240006"/>
 *	    
 *<Listerner>
 *	<Port/>
 *	<Ip/>
 *  <MaxConnections/>
 *  <MinConnections/>
 *  <PingInterval/>
 *  <Clazz/>
 * </Listener>
	 */
	public ForwardListener() {
		// TODO Auto-generated constructor stub
	}
	@BeanPropertySetter(pattern="ServerRules/ForwardListener/Port")
	int port;
	@BeanPropertySetter(pattern="ServerRules/ForwardListener/Ip")
	String ip;
	
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
	

}
