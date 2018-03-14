/**
 * 
 */
package com.palmcommerce.funds.roo.tasklet.schedule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.j256.simplejmx.client.JmxClient;


/**
 * @author sparrow
 *
 */
public class ScheduledTaskJmxClient implements IScheduledTaskJMX {
	
	private static final Log logger=LogFactory.getLog(ScheduledTaskJmxClient.class);
	
	
	
	
	
	public ScheduledTaskJmxClient(String ip, int port, String username,
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

	

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.tasklet.schedule.IScheduledTaskJMX#changeState(int)
	 */
	@Override
	public void changeState(int state) {
		// TODO Auto-generated method stub
		JmxClient client=null;
		try {
			
			 client=new JmxClient(ip, port,username,password);
			logger.info("invokeOperation:changeState= ip="+ip+",port= "+port+",username="+username+",password"+password);
			client.invokeOperation(ScheduledTaskStateMachine.class.getPackage().getName(), ScheduledTaskStateMachine.class.getSimpleName(), "changeState", new String[]{String.valueOf(state)});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			client.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.tasklet.schedule.IScheduledTaskJMX#getScheduledState()
	 */
	@Override
	public String getScheduledState() {
		// TODO Auto-generated method stub
		JmxClient client=null;
		String state=null;
		try {
			
			 client=new JmxClient(ip, port,username,password);
			logger.info("getAttribute:getScheduledState= ip="+ip+",port= "+port+",username="+username+",password"+password);
			 state=(String)client.getAttribute(ScheduledTaskStateMachine.class.getPackage().getName(), ScheduledTaskStateMachine.class.getSimpleName(),  "scheduledState");
			
			//client.invokeOperation(ScheduledTaskStateMachine.class.getPackage().getName(), ScheduledTaskStateMachine.class.getSimpleName(), "getScheduledState", new String[]{String.valueOf()});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			client.close();
		}
		return state;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.tasklet.schedule.IScheduledTaskJMX#onCleanup()
	 */
	@Override
	public void onCleanup() {
		// TODO Auto-generated method stub
		JmxClient client=null;
		try {
			
			 client=new JmxClient(ip, port,username,password);
			logger.info("invokeOperation:onCleanup= ip="+ip+",port= "+port+",username="+username+",password"+password);
			client.invokeOperation(ScheduledTaskStateMachine.class.getPackage().getName(), ScheduledTaskStateMachine.class.getSimpleName(), "onCleanup", new String[]{});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			client.close();
		}
	}
	@Override
	public void changePreviousDays(int days) {
		// TODO Auto-generated method stub
		JmxClient client=null;
		try {
			
			 client=new JmxClient(ip, port,username,password);
			logger.info("invokeOperation:changePreviousDays= ip="+ip+",port= "+port+",username="+username+",password"+password);
			client.invokeOperation(ScheduledTaskStateMachine.class.getPackage().getName(), ScheduledTaskStateMachine.class.getSimpleName(), "changePreviousDays", new String[]{String.valueOf(days)});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			client.close();
		}
		
	}
	@Override
	public String getAccountDate() {
		// TODO Auto-generated method stub
		JmxClient client=null;
		String state=null;
		try {
			
			 client=new JmxClient(ip, port,username,password);
			logger.info("getAttribute:getAccountDate= ip="+ip+",port= "+port+",username="+username+",password"+password);
			 state=(String)client.getAttribute(ScheduledTaskStateMachine.class.getPackage().getName(), ScheduledTaskStateMachine.class.getSimpleName(),  "accountDate");
			
			//client.invokeOperation(ScheduledTaskStateMachine.class.getPackage().getName(), ScheduledTaskStateMachine.class.getSimpleName(), "getScheduledState", new String[]{String.valueOf()});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			client.close();
		}
		return state;
	}
	
	
	@Override
	public void sendReconciliationRequest(String bankcode, String date) {
		// TODO Auto-generated method stub
		JmxClient client=null;
		try {
			
			 client=new JmxClient(ip, port,username,password);
			logger.info("invokeOperation:sendReconciliationRequest= ip="+ip+",port= "+port+",username="+username+",password="+password);
			client.invokeOperation(ScheduledTaskStateMachine.class.getPackage().getName(), ScheduledTaskStateMachine.class.getSimpleName(), "sendReconciliationRequest", new String[]{bankcode,date});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			client.close();
		}
	}
	@Override
	public void reconciliation(String accountDate) {
		// TODO Auto-generated method stub
		JmxClient client=null;
		try {
			
			 client=new JmxClient(ip, port,username,password);
			logger.info("invokeOperation: reconciliation = ip="+ip+",port= "+port+",username="+username+",password="+password);
			client.invokeOperation(ScheduledTaskStateMachine.class.getPackage().getName(), ScheduledTaskStateMachine.class.getSimpleName(), "reconciliation", new String[]{accountDate});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			client.close();
		}
		
	}
	@Override
	public void reconciliationBank(String bankcode,String filename, String date) {
		// TODO Auto-generated method stub
		JmxClient client=null;
		try {
			
			 client=new JmxClient(ip, port,username,password);
			logger.info("invokeOperation:reconciliationBank= ip="+ip+",port= "+port+",username="+username+",password="+password);
			client.invokeOperation(ScheduledTaskStateMachine.class.getPackage().getName(), ScheduledTaskStateMachine.class.getSimpleName(), "reconciliationBank", new String[]{bankcode,filename,date});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			client.close();
		}
		
	}
	@Override
	public void loadRouteRule() {
		// TODO Auto-generated method stub
		JmxClient client=null;
		try {
			
			 client=new JmxClient(ip, port,username,password);
			logger.info("invokeOperation:loadRouteRule= ip="+ip+",port= "+port+",username="+username+",password="+password);
			client.invokeOperation(ScheduledTaskStateMachine.class.getPackage().getName(), ScheduledTaskStateMachine.class.getSimpleName(), "loadRouteRule", new String[]{});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			client.close();
		}
		
	}
	@Override
	public void reconciliationTrade(String tradecode, String bankdate) {
		// TODO Auto-generated method stub
		JmxClient client=null;
		try {
			
			 client=new JmxClient(ip, port,username,password);
			logger.info("invokeOperation:reconciliationTrade= ip="+ip+",port= "+port+",username="+username+",password="+password);
			client.invokeOperation(ScheduledTaskStateMachine.class.getPackage().getName(), ScheduledTaskStateMachine.class.getSimpleName(), "reconciliationTrade", new String[]{tradecode,bankdate});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			client.close();
		}
	}

}
