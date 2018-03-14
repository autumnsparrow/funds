package com.palmcommerce.funds.id.jmx;
/**
 * 
 */


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.j256.simplejmx.client.JmxClient;


/**
 * @author sparrow
 *
 */
public class IdGeneratorJmxClient implements IIdGenerator {
	
private static final Log logger=LogFactory.getLog(IdGeneratorJmxClient.class);
	
	
	
	
	
	public IdGeneratorJmxClient(String ip, int port, String username,
			String password) {
		super();
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.password = password;
	}
	
	String ip;
	int port;
	
	String username;
	String password;
	

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 */
	public IdGeneratorJmxClient() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.id.impl.IIdGenerator#getLocalSerialForRemoteSerial(java.lang.String)
	 */
	@Override
	public String findLocalSerialByRemoteSerial(String serial) {
		// TODO Auto-generated method stub
		JmxClient client=null;
		String localSerial=null;
		try {
			
			 client=new JmxClient(ip, port,username,password);
			 logger.info("invokeOperation:findLocalSerialByRemoteSerial = ip="+ip+",port= "+port+",username="+username+",password="+password);
			 Object ret=client.invokeOperation("com.palmcommerce.funds.id.impl", "DefaultIdGenerator", "findLocalSerialByRemoteSerial", new String[]{serial});
			 if(ret!=null&&(ret instanceof String))
				 localSerial=(String)ret;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			client.close();
		}
		return localSerial;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.id.impl.IIdGenerator#getRemoteSerialByLocalSerial(java.lang.String)
	 */
	@Override
	public String findRemoteSerialByLocalSerial(String localSerial) {
		// TODO Auto-generated method stub
		JmxClient client=null;
		String remoteSerial=null;
		try {
			
			 client=new JmxClient(ip, port,username,password);
			 logger.info("invokeOperation:findRemoteSerialByLocalSerial = ip="+ip+",port= "+port+",username="+username+",password="+password);
			 Object ret=client.invokeOperation("com.palmcommerce.funds.id.impl", "DefaultIdGenerator", "findRemoteSerialByLocalSerial", new String[]{localSerial});
			 if(ret!=null&&(ret instanceof String))
				 localSerial=(String)ret;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			client.close();
		}
		return remoteSerial;
	}

}
