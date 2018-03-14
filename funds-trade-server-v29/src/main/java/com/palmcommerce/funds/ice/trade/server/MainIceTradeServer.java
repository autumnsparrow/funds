package com.palmcommerce.funds.ice.trade.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.palmcommerce.funds.util.IceServerManager;
import com.palmcommerce.funds.util.SpringHelper;

/**
 * Hello world!
 *
 */
public class MainIceTradeServer 
{
   
	private static final Log logger=LogFactory.getLog(MainIceTradeServer.class);
	public static void main( String[] args )
    {
    	SpringHelper.init(new String[]{
    			"classpath:META-INF/spring/applicationContext-funds-configuration-local.xml",
    			"classpath:META-INF/spring/applicationContext-funds-id.xml",
    			"classpath:META-INF/spring/applicationContext-funds-packet.xml",
    			"classpath:META-INF/spring/applicationContext-funds-ice-metrics.xml",
    			"classpath:META-INF/spring/applicationContext-funds-bank.xml",
    			"classpath:META-INF/spring/applicationContext-funds-trade-basic.xml",
    			"classpath:META-INF/spring/applicationContext-funds-trade-connection.xml",
    			"classpath:META-INF/spring/applicationContext-funds-trade-ice-server.xml",
    			
    	});
    	
    	logger.info("***************Starting IceServer ****************");
		IceServerManager server=SpringHelper.getBean("iceServerManager");
		server.start();
		
    	
    	
    }
}
