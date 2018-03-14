/**
 * 
 */
package com.palmcommerce.funds.roo.ice.excute;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.palmcommerce.funds.configuration.v2.FundsScheduledExecutorService;
import com.palmcommerce.funds.connection.mina.ctx.MinaContext;

import com.palmcommerce.funds.packet.process.IProtocolFilter;
import com.palmcommerce.funds.packet.process.IProtocolProcessor;
import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.impl.t2p.T250011;
import com.palmcommerce.funds.roo.protocol.impl.DefaultProtocolProcessHandler;
import com.palmcommerce.funds.roo.tasklet.schedule.ScheduledTask;
import com.palmcommerce.funds.roo.util.DateConvertor;
import com.palmcommerce.funds.service.ProtocolProcessException;
import com.palmcommerce.funds.util.IceServerManager;
import com.palmcommerce.funds.util.SpringHelper;



/**
 * @author sparrow
 *
 */
public class MainProxyStorageWithoutSftp {

	/**
	 * 
	 */
	public MainProxyStorageWithoutSftp() {
		// TODO Auto-generated constructor stub
	}
	

	private static Log logger = LogFactory.getLog(MainProxyStorageWithoutSftp.class);
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stubl

		logger.info("***************InitServlet init START****************");
		// spring 初始化
		logger.info("***************INIT SpringHelper START ****************");
		SpringHelper.init(new String[]{
    			"classpath:META-INF/spring/applicationContext-funds-ca-rest-client.xml",
    			"classpath:META-INF/spring/applicationContext-funds-ice-metrics.xml",
    			"classpath:META-INF/spring/applicationContext-funds-configuration.xml",
    			
    			"classpath:META-INF/spring/applicationContext-funds-packet.xml",
    			//"classpath:META-INF/spring/applicationContext-funds-protocol.xml",
    			//"classpath:META-INF/spring/applicationContext-funds-id.xml",
    			"classpath:META-INF/spring/applicationContext-funds-connection-without-filter.xml",
    			
    			"classpath:META-INF/spring/applicationContext-funds-roo*.xml",
    			//"classpath:META-INF/spring/applicationContext-funds-sftp-*.xml"
    			
    	});
		logger.info("***************INIT SpringHelper END ****************");
		
		
		IProtocolProcessor scheduledTradeClientProtocolHandler=SpringHelper.getBean("scheduledTradeClientProtocolHandler");
    	
    	MinaContext minaContext=SpringHelper.getBean("minaContext");
    	
    	 	
    	minaContext.setClientSideProtocolProcessor(scheduledTradeClientProtocolHandler);
    	
    	// only the proxy model need that part.
    	// server or client don't need that id generator .
    	//minaContext.setProtocolFilter(idGeneratorProtocolFilter);
    	minaContext.startWithAcceptorOrConnectorMode();
    	logger.info("***************INIT Connection END ****************");
		// loading the client node.
		
    	
    	// schedule the service
//    	FundsScheduledExecutorService scheduledExecutorService=SpringHelper.getBean("scheduledExecutorService");
//    	ScheduledTask scheduledTask=SpringHelper.getBean("scheduledTask");
//    	
//    	
//    	scheduledExecutorService
//    	.getScheduledExecutorService()
//    	.scheduleWithFixedDelay(scheduledTask, 0, scheduledTask.getTaskRepeatInterval(), TimeUnit.SECONDS);
    	logger.info("***************INIT Scheduled Task END ****************");
		
		
    	DefaultProtocolProcessHandler processor=(DefaultProtocolProcessHandler)SpringHelper.getBean("protocolProcessHandler");
    	
    	
//    	T250011 t = (T250011) ProtocolDriverManager
//				.instance("250011", "P0000001", "B0000003");
//		t.request.setAccountTime(DateConvertor.getBankTimeByDate("20131119"));
//
//		// try {
//
//		logger.info("Sending the request:" + t.toString());
//		// Release that par when connection.
//		scheduledTradeClientProtocolHandler.send(t);
    	
//    	T250011 t=(T250011) ProtocolDriverManager.instance("250011", "T0000001", "B0000001");
//    	t.request.setAccountTime("20131107");
//    	try {
//			processor.t250011(t);
//		} catch (ProtocolProcessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	
    	logger.info("***************Starting IceServer ****************");
		IceServerManager server=SpringHelper.getBean("iceServerManager");
		server.start();
		
		
//    	GlobalIdGenerator generator=SpringHelper.getBean("globalIdGenerator");
//    	generator.start();
		
		//IProtocolFilter  idGeneratorProtocolFilter=SpringHelper.getBean("idGeneratorProtocolFilterHandler");
        
    
    	
	}

}
