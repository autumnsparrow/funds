/**
 * 
 */
package com.palmcommerce.funds.trade.ibatis.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.j256.simplejmx.client.JmxClient;


/**
 * @author sparrow
 *
 */
public class ReconciliationJmxClient implements IReconciliationJmx {
	private static final Log logger=LogFactory.getLog(ReconciliationJmxClient.class);
	private String ip;
	private int port;
	private String username;
	private String password;
	/**
	 * 
	 */
	public ReconciliationJmxClient() {
		// TODO Auto-generated constructor stub
	}
	
	

	public ReconciliationJmxClient(String ip, int port, String username,
			String password) {
		super();
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.password = password;
	}



	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.trade.ibatis.impl.IReconciliationJmx#reconciliation(java.lang.String)
	 */
	public void reconciliation(String date) {
		// TODO Auto-generated method stub
		JmxClient client=null;
		
		try {
			
			 client=new JmxClient(ip, port,username,password);
			logger.info("getAttribute:reconciliation= ip="+ip+",port= "+port+",username="+username+",password="+password);
			// state=(String)client.getAttribute(OldTradeClientProtocolHandler.class.getPackage().getName(), OldTradeClientProtocolHandler.class.getSimpleName(),  "reconciliationDate");
			
			client.invokeOperation(OldTradeClientProtocolHandler.class.getPackage().getName(), OldTradeClientProtocolHandler.class.getSimpleName(), "reconciliation", new String[]{date});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			client.close();
		}
	}
	

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.trade.ibatis.impl.IReconciliationJmx#getReconciliationDate()
	 */
	public String getReconciliationDate() {
		// TODO Auto-generated method stub
		JmxClient client=null;
		String state=null;
		try {
			
			 client=new JmxClient(ip, port,username,password);
			logger.info("getAttribute:getReconciliationDate= ip="+ip+",port= "+port+",username="+username+",password="+password);
			 state=(String)client.getAttribute(OldTradeClientProtocolHandler.class.getPackage().getName(), OldTradeClientProtocolHandler.class.getSimpleName(),  "reconciliationDate");
			
			//client.invokeOperation(ScheduledTaskStateMachine.class.getPackage().getName(), ScheduledTaskStateMachine.class.getSimpleName(), "getScheduledState", new String[]{String.valueOf()});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			client.close();
		}
		return state;
		//return null;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.trade.ibatis.impl.IReconciliationJmx#changeReconciliationDate(java.lang.String)
	 */
	public void changeReconciliationDate(String d) {
		// TODO Auto-generated method stub
		JmxClient client=null;
		
		try {
			
			 client=new JmxClient(ip, port,username,password);
			logger.info("getAttribute:getAccountDate= ip="+ip+",port= "+port+",username="+username+",password="+password);
			// state=(String)client.getAttribute(OldTradeClientProtocolHandler.class.getPackage().getName(), OldTradeClientProtocolHandler.class.getSimpleName(),  "reconciliationDate");
			
			client.invokeOperation(OldTradeClientProtocolHandler.class.getPackage().getName(), OldTradeClientProtocolHandler.class.getSimpleName(), "changeReconciliationDate", new String[]{d});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			client.close();
		}
		

	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.trade.ibatis.impl.IReconciliationJmx#cleanStates()
	 */
	public void cleanStates() {
		// TODO Auto-generated method stub
		JmxClient client=null;
		
		try {
			
			 client=new JmxClient(ip, port,username,password);
			logger.info("getAttribute:cleanStates= ip="+ip+",port= "+port+",username="+username+",password="+password);
			// state=(String)client.getAttribute(OldTradeClientProtocolHandler.class.getPackage().getName(), OldTradeClientProtocolHandler.class.getSimpleName(),  "reconciliationDate");
			
			client.invokeOperation(OldTradeClientProtocolHandler.class.getPackage().getName(), OldTradeClientProtocolHandler.class.getSimpleName(), "cleanStates", new String[]{});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			client.close();
		}
	}



	public void reconciliationByDate(String filename, String bankdate) {
		// TODO Auto-generated method stub
JmxClient client=null;
		
		try {
			
			 client=new JmxClient(ip, port,username,password);
			logger.info("getAttribute:getAccountDate= ip="+ip+",port= "+port+",username="+username+",password="+password);
			// state=(String)client.getAttribute(OldTradeClientProtocolHandler.class.getPackage().getName(), OldTradeClientProtocolHandler.class.getSimpleName(),  "reconciliationDate");
			
			client.invokeOperation(OldTradeClientProtocolHandler.class.getPackage().getName(), OldTradeClientProtocolHandler.class.getSimpleName(), "reconciliationByDate", new String[]{filename,bankdate});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			client.close();
		}
		
	}

}
