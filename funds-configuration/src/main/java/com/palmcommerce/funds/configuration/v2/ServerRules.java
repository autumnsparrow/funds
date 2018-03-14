/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 15, 2013
 *
 */
package com.palmcommerce.funds.configuration.v2;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;
import org.apache.commons.digester3.annotations.rules.SetNext;

/**
 * @author sparrow
 *
 */
@ObjectCreate(pattern="ServerRules")
public class ServerRules {

	/**
	 * 
	 <Listeners>
 *  	<!--litener for the bank to trade-->
 *  	<Listener port="80" ip="192.168.1.86" maxConnections="100" minConnections="10" pingInterval="5" class="com.palmcommerce.protocol.impl.p2t.P240006"/>
 *	      
 *       <!-- listner for the trade to bank -->
		 <Listener port="90" ip="192.168.1.86" maxConnections="50" minConnection="10" pingInterval="5" class="com.palmcommerce.protocol.impl.p2t.P240006"/>
 *  
 *  </Listeners>
 *  
 *  			+-------------+
 *  			| Oss  Server | <<Route Rule Server>>
 *  			+-------------+
 *  
 *     +------+
 *     |proxy |
 *     +------+
 *     
 *     			+----------------+
 *     			| Secruity Server|  <<Security Rule Server>>
 *     			+----------------+
 *  
	 */
	public ServerRules() {
		// TODO Auto-generated constructor stub
	}
	
	private Map<Integer,Listener> mapOfListeners=new HashMap<Integer,Listener>();
	private Map<Integer,ForwardListener> mapOfForwardListeners=new HashMap<Integer,ForwardListener>();
	
	
	@BeanPropertySetter(pattern="ServerRules/SharedProcessorNumber")
	int sharedProcessNumber;
	@BeanPropertySetter(pattern="ServerRules/TaskExecutorNumber")
	int taskExecutorNumber;
	@BeanPropertySetter(pattern="ServerRules/ProxyThreadPollExecutorNumber")
	int proxyThreadPoolExcutorNumber;
	@BeanPropertySetter(pattern="ServerRules/ForwardSharedProcessorNumber")
	int forardProcessorNumber;
	@BeanPropertySetter(pattern="ServerRules/ShouldUsingForwardRoute")
	boolean shouldUsingForwardRoute;
	
	
	



	/**
	 * @return the shouldUsingForwardRoute
	 */
	public boolean isShouldUsingForwardRoute() {
		return shouldUsingForwardRoute;
	}

	/**
	 * @param shouldUsingForwardRoute the shouldUsingForwardRoute to set
	 */
	public void setShouldUsingForwardRoute(boolean shouldUsingForwardRoute) {
		this.shouldUsingForwardRoute = shouldUsingForwardRoute;
	}

	/**
	 * @return the forardProcessorNumber
	 */
	public int getForardProcessorNumber() {
		return forardProcessorNumber;
	}

	/**
	 * @param forardProcessorNumber the forardProcessorNumber to set
	 */
	public void setForardProcessorNumber(int forardProcessorNumber) {
		this.forardProcessorNumber = forardProcessorNumber;
	}

	private RouteRule routeRule;
	private Proxy proxy;
	
	private LocalConfiguration localConfiguration;
	
	/**
	 * @return the localConfiguration
	 */
	public LocalConfiguration getLocalConfiguration() {
		return localConfiguration;
	}

	/**
	 * @param localConfiguration the localConfiguration to set
	 */
	@SetNext
	public void setLocalConfiguration(LocalConfiguration localConfiguration) {
		this.localConfiguration = localConfiguration;
	}

	public Collection<Listener> getListeners(){
		return this.mapOfListeners.values();
	}
	
	public Collection<ForwardListener> getForwardListeners(){
		return this.mapOfForwardListeners.values();
	}
	
	/**
	 * @param listener the listener to set
	 */
	@SetNext
	public void setListener(Listener listener) {
		this.mapOfListeners.put(Integer.valueOf(listener.getPort()), listener);
	}
	
	
	public Listener getListener(int port){
		return this.mapOfListeners.get(Integer.valueOf(port));
	}
	
	@SetNext
	public void setForwardListener(ForwardListener forwardListener){
		this.mapOfForwardListeners.put(Integer.valueOf(forwardListener.getPort()), forwardListener);
	}
	
	public ForwardListener getForwardListener(int port){
		return this.mapOfForwardListeners.get(Integer.valueOf(port));
	}

	/**
	 * @return the routeRule
	 */
	public RouteRule getRouteRule() {
		return routeRule;
	}


	/**
	 * @param routeRule the routeRule to set
	 */
	@SetNext
	public void setRouteRule(RouteRule routeRule) {
		this.routeRule = routeRule;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ServerRules [mapOfListeners=" + mapOfListeners
				+ ", securityRule="  + ", routeRule=" + routeRule
				+ "]";
	}


	/**
	 * @return the proxy
	 */
	public Proxy getProxy() {
		return proxy;
	}


	/**
	 * @param proxy the proxy to set
	 */
	@SetNext
	public void setProxy(Proxy proxy) {
		this.proxy = proxy;
	}


	/**
	 * @return the sharedProcessNumber
	 */
	public int getSharedProcessNumber() {
		return sharedProcessNumber;
	}


	/**
	 * @param sharedProcessNumber the sharedProcessNumber to set
	 */
	
	public void setSharedProcessNumber(int sharedProcessNumber) {
		this.sharedProcessNumber = sharedProcessNumber;
	}


	/**
	 * @return the taskExecutorNumber
	 */
	public int getTaskExecutorNumber() {
		return taskExecutorNumber;
	}


	/**
	 * @param taskExecutorNumber the taskExecutorNumber to set
	 */
	public void setTaskExecutorNumber(int taskExecutorNumber) {
		this.taskExecutorNumber = taskExecutorNumber;
	}


	/**
	 * @return the proxyThreadPoolExcutorNumber
	 */
	public int getProxyThreadPoolExcutorNumber() {
		return proxyThreadPoolExcutorNumber;
	}


	/**
	 * @param proxyThreadPoolExcutorNumber the proxyThreadPoolExcutorNumber to set
	 */
	public void setProxyThreadPoolExcutorNumber(int proxyThreadPoolExcutorNumber) {
		this.proxyThreadPoolExcutorNumber = proxyThreadPoolExcutorNumber;
	}
	
	
	
	
	
	
	
	

}
