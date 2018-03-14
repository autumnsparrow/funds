/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Sep 7, 2013-2:06:59 PM
 *
 */
package com.palmcommerce.funds.connection.config;



/**
 * @author sparrow
 * 
 * 
 * 	
 * 
 * </Mina>
 *
 */
public class FundsConfiguration {
	
	
	int sharedIoProcessor;
	
	int proxyThreadPoolExecutor;
	
	int taskExecutor;
	
	String routeTableConfig;
	
	String securityConfiguration;
	
	


	public String getSecurityConfiguration() {
		return securityConfiguration;
	}



	public void setSecurityConfiguration(String securityConfiguration) {
		this.securityConfiguration = securityConfiguration;
	}



	public int getSharedIoProcessor() {
		return sharedIoProcessor;
	}



	public void setSharedIoProcessor(int sharedIoProcessor) {
		this.sharedIoProcessor = sharedIoProcessor;
	}



	public int getProxyThreadPoolExecutor() {
		return proxyThreadPoolExecutor;
	}



	public void setProxyThreadPoolExecutor(int proxyThreadPoolExecutor) {
		this.proxyThreadPoolExecutor = proxyThreadPoolExecutor;
	}



	public int getTaskExecutor() {
		return taskExecutor;
	}



	public void setTaskExecutor(int taskExecutor) {
		this.taskExecutor = taskExecutor;
	}



	public String getRouteTableConfig() {
		return routeTableConfig;
	}



	public void setRouteTableConfig(String routeTableConfig) {
		this.routeTableConfig = routeTableConfig;
	}



	/**
	 * 
	 */
	public FundsConfiguration() {
		// TODO Auto-generated constructor stub
	}

}
