package com.palmcommerce.funds.proxy;

import java.util.Date;

import com.palmcommerce.funds.connection.mina.ctx.MinaContext;
import com.palmcommerce.funds.id.impl.GlobalIdGenerator;
import com.palmcommerce.funds.id.model.LocalAndRemoteSerialRelation;
import com.palmcommerce.funds.id.service.LocalAndRemoteSerialRelationService;
import com.palmcommerce.funds.packet.process.IProtocolFilter;

import com.palmcommerce.funds.util.SpringHelper;

/**
 * Hello world!
 *
 */
public class MainProxyServer 
{
    public static void main( String[] args )
    {
    	SpringHelper.init(new String[]{
    			"classpath:META-INF/spring/applicationContext-funds-ca-rest-client.xml",
    			"classpath:META-INF/spring/applicationContext-funds-configuration.xml",
    			"classpath:META-INF/spring/applicationContext-funds-id-oracle.xml",
    			"classpath:META-INF/spring/applicationContext-funds-packet.xml",
    			 "classpath:META-INF/spring/applicationContext-funds-ice-proxy.xml",
    			"classpath:META-INF/spring/applicationContext-funds-ice-metrics.xml",
    			"classpath:META-INF/spring/applicationContext-funds-connection-with-ice.xml",
    			//"classpath:META-INF/spring/applicationContext-funds-trade.xml",
    	});
    	
//    	// Here can get the caRestClient
//    	// copy the funds-ca-rest-client.properties into /META-INF/funds
//    	CaRestClient caRestClient=SpringHelper.getBean("caRestClient");
//    	log("caRestBaseUrl ---> "+caRestClient.getBaseUrl());
//    	
//    	// copy funds-configuraiton.properties,listeners.xml
//    	// 
//    	ConfigurationManager configurationManager=(ConfigurationManager)SpringHelper.getBean("configurationManager");
//    	
//    	log("configuration:config  ---> "+configurationManager.getConfig());
//    	
    	
//    	
//    	log(" global id generator:"+generator.getDelayTimeSeconds());
//    	
//    	IPacketSecurity packetSecurity=SpringHelper.getBean("packetSecurity");
//    	
//    	log("packet :"+packetSecurity.toString());
//  
//    	LocalAndRemoteSerialRelationService 	localAndRemoteSerialRelationService=SpringHelper.getBean("localAndRemoteSerialRelationService");
//    	
//        LocalAndRemoteSerialRelation relation=new LocalAndRemoteSerialRelation();
//        relation.setLocalSerialNumber("22222");
//        relation.setRemoteSerialNumber("3333");
//        relation.setCreateTime(new Date());
//        relation.persist();
//    	
    	IProtocolFilter  idGeneratorProtocolFilter=SpringHelper.getBean("idGeneratorProtocolFilterHandler");
//    	// if using the i
    	GlobalIdGenerator generator=SpringHelper.getBean("globalIdGenerator");
    	generator.start();
        
    	//T250004 p250004;
    	
    //	IProtocolProcessor tradeClientProtocolHandler=SpringHelper.getBean("oldTradeProtocolHandler");
    //	DefaultTradeClientProtocolHandler tradeClientProtocolHandler=SpringHelper.getBean("tradeClientProtocolHandler");
    	
    	MinaContext minaContext=SpringHelper.getBean("minaContext");
    	
    	 	
    	//minaContext.setServerSideProtocolProcessor(tradeClientProtocolHandler);
    	//minaContext.setClientSideProtocolProcessor(tradeClientProtocolHandler);
    	// only the proxy model need that part.
    	// server or client don't need that id generator .
    	minaContext.setProtocolFilter(idGeneratorProtocolFilter);
    	
    	
    	
    	minaContext.startWithProxyMode();
    }
}
