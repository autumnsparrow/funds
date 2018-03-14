/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 16, 2013
 *
 */
package com.palmcommerce.funds.configuration.v2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.j256.simplejmx.client.JmxClient;

/**
 * @author sparrow
 *
 */
public class ConfigurationManagerJMXClient implements IConfigurationManagerJMX {

	//private static final Log logger=LogFactory.getLog(ConfigurationManagerJMXClient.class);
	/**
	 * 
	 */
	public ConfigurationManagerJMXClient() {
		// TODO Auto-generated constructor stub
	}
	private static final Log logger=LogFactory.getLog(ConfigurationManagerJMXClient.class);
	String ip;
	int port;
	
	String username;
	String password;
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	
	
	
	
	
	public ConfigurationManagerJMXClient(String ip, int port, String username,
			String password) {
		super();
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.password = password;
	}
	public ConfigurationManagerJMXClient(String ip, int port) {
		super();
		this.ip = ip;
		this.port = port;
	}
	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.configuration.v2.IConfigurationManagerJMX#updateServerRules()
	 */
	public void updateServerRules() {
		// TODO Auto-generated method stub
		JmxClient client=null;
		try {
			
			 client=new JmxClient(ip, port,username,password);
			logger.info("invokeOperation:updateServerRules= ip="+ip+",port= "+port+",username="+username+",password"+password);
			client.invokeOperation(ConfigurationManager.class.getPackage().getName(), ConfigurationManager.class.getSimpleName(), "updateServerRules", new String[]{});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			client.close();
		}
		
	}
	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.configuration.v2.IConfigurationManagerJMX#updateRouteRules()
	 */
	public void updateRouteRules() {
		// TODO Auto-generated method stub
		JmxClient client=null;
		try {

			 client=new JmxClient(ip, port,username,password);

				logger.info("invokeOperation:updateRouteRules= ip="+ip+",port= "+port+",username="+username+",password"+password);
				
			client.invokeOperation(ConfigurationManager.class.getPackage().getName(), ConfigurationManager.class.getSimpleName(), "updateRouteRules", new String[]{});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			client.close();
		}
		
	}
	
	

	

}
