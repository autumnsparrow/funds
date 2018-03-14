package com.palmcommerce.configuration.load.test;


import com.palmcommerce.funds.connection.mina.ctx.MinaContext;
import com.palmcommerce.funds.id.impl.GlobalIdGenerator;



import com.palmcommerce.funds.packet.process.IProtocolFilter;
import com.palmcommerce.funds.packet.process.IProtocolProcessor;
import com.palmcommerce.funds.packet.security.IPacketSecurity;
import com.palmcommerce.funds.packet.security.PacketSecurityHandler;
import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.impl.p2t.P240003;
import com.palmcommerce.funds.protocol.impl.t2p.T250004;
import com.palmcommerce.funds.protocol.impl.t2p.T250005;
import com.palmcommerce.funds.protocol.impl.t2p.T250006;
import com.palmcommerce.funds.protocol.impl.t2p.T250007;
import com.palmcommerce.funds.protocol.impl.t2p.T250008;
import com.palmcommerce.funds.protocol.impl.t2p.T250009;
import com.palmcommerce.funds.protocol.impl.t2p.T250010;
import com.palmcommerce.funds.protocol.impl.t2p.T250011;
import com.palmcommerce.funds.service.ProtocolProcessException;
import com.palmcommerce.funds.trade.impl.DefaultBankClientProtocolHandler;
import com.palmcommerce.funds.trade.impl.DefaultTradeClientProtocolHandler;
import com.palmcommerce.funds.util.DateConvertor;
import com.palmcommerce.funds.util.Metrics;
import com.palmcommerce.funds.util.SpringHelper;

/**
 * Hello world!
 *
 */
public class TradeClient 
{
	
	public static void log(String message){
		System.out.println(message);
	}
	
	
    public static void main( String[] args )
    {
       	
    	SpringHelper.init(new String[]{
    			"classpath:META-INF/spring/applicationContext-funds-ca-rest-client.xml",
    			"classpath:META-INF/spring/applicationContext-funds-configuration-local.xml",
    			"classpath:META-INF/spring/applicationContext-funds-id-oracle.xml",
    			"classpath:META-INF/spring/applicationContext-funds-packet.xml",
    			"classpath:META-INF/spring/applicationContext-funds-ice-metrics.xml",
    			"classpath:META-INF/spring/applicationContext-funds-connection-without-filter.xml",
    			"classpath:META-INF/spring/applicationContext-funds-bank.xml",
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
    	
    	
//    	IProtocolFilter  idGeneratorProtocolFilter=SpringHelper.getBean("idGeneratorProtocolFilterHandler");
//    	// if using the i
    	GlobalIdGenerator generator=SpringHelper.getBean("globalIdGenerator");
    	generator.start();
    	/*RedisLocalAndRemoteRelationshipService redisLocalAndRemoteRelationshipService=SpringHelper.getBean("redisLocalAndRemoteRelationshipService");
    	IdMap idmap=new IdMap();
    	idmap.setLocal("xxx1111111");
    	idmap.setRemote("xxx22222");
    	redisLocalAndRemoteRelationshipService.persist(idmap);
    	String local=redisLocalAndRemoteRelationshipService.findLocalByRemote("22222");
        String remote=redisLocalAndRemoteRelationshipService.findRemoteByLocal(local);*/
    	//T250004 p250004;
    	
    //	IProtocolProcessor tradeServerProtocolHandler=SpringHelper.getBean("oldTradeProtocolHandler");
    	DefaultTradeClientProtocolHandler tradeClientProtocolHandler=SpringHelper.getBean("tradeClientProtocolHandler");
    	DefaultBankClientProtocolHandler bankClientProtocolHandler=SpringHelper.getBean("bankClientProtocolHandler");
    	//    	
    	//DefaultBankClientProtocolHandler  bankClientProtocolHandler=SpringHelper.getBean("bankClientProtocolHandler");
    	MinaContext minaContext=SpringHelper.getBean("minaContext");
//    	
//    	 	
    	//minaContext.setServerSideProtocolProcessor(tradeServerProtocolHandler);
    	minaContext.setClientSideProtocolProcessor(tradeClientProtocolHandler);
//    	// only the proxy model need that part.
//    	// server or client don't need that id generator .
//    	//minaContext.setProtocolFilter(idGeneratorProtocolFilter);
//    	
//    	
//    	
    	minaContext.startWithAcceptorOrConnectorMode();
    	
    	//t240003(generator, bankClientProtocolHandler);
    	//t250004(generator, tradeClientProtocolHandler);
    	//t250011(generator, tradeClientProtocolHandler);
//    	t250004(generator, tradeClientProtocolHandler);
//    	T250004 t250004=(T250004)ProtocolDriverManager.instance("250004", "B0000002", "B0000002");
//    
//    	t250004.request.setAccount("5201521327808293");
//    	t250004.request.setAccountTime(DateConvertor.getBankTime());
//    	t250004.request.setCenterNumber(generator.nextSerialId());
//    	t250004.request.setPaymentAmount(String.valueOf(100));
//    	t250004.request.setPaymentType(String.valueOf(0));
//    	t250004.request.setTransactionTime(DateConvertor.getTradeTime());
//    	t250004.request.setUserId("10000400");
//    	t250004.request.setUserName("王兰");
//    	
//    	try {
//			tradeClientProtocolHandler.send(t250004);
//		} catch (ProtocolProcessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	
//    	log(t250004.getResponsePacket());
//    	minaContext.startWithAcceptorOrConnectorMode();
        /*
         * 中心流水号	字符	最长20位	Y	用于中心标识每一笔请求交易（必须唯一）
用户编号	字符	最少8位，最长15位	Y	用户ID，该值对每个用户是唯一的
用户姓名	字符	最长10位	Y	用户的姓名
缴费类型	字符	2位	Y	银行定义 例如02终端缴费03网银缴费
缴费金额	字符	最长20位	Y	单位为分
账户	字符	最长20位	Y	银行卡号
交易时间	字符	时间格式为：YYYY-MM-DD HH:MM:SS	Y	格式为：YYYY-MM-DD HH:MM:SS
账务日期	字符	时间格式为：YYYYMMDD	Y	格式为：YYYYMMDD
         * */
//    	
//    	T250004 t250004=(T250004)ProtocolDriverManager.instance("250004", "T0000003", "BT000001");
//    	t250004.request.setCenterNumber(generator.nextSerialId());
//    	t250004.request.setUserId("10000401");
//    	t250004.request.setUserName("王兰");
//    	t250004.request.setPaymentType("01");
//    	t250004.request.setPaymentAmount(String.valueOf(100));
//    	t250004.request.setAccount("6228671133896465");
//    	t250004.request.setTransactionTime(DateConvertor.getTradeTime());
//    	t250004.request.setAccountTime(DateConvertor.getBankTime()); 
//  	
//		tradeClientProtocolHandler.send(t250004);
//    	log(t250004.getResponsePacket());
   	
    
    	
    	/*
    	 * 中心流水号	字符	最长20位	Y	用于中心标识每一笔请求交易（必须唯一）
实时缴款流水号	字符	最长20位	Y	上一笔未成功缴款交易的流水号
用户编号	字符	最少8位，最长15位	Y	用户ID，该值对每个用户是唯一的
用户姓名	字符	最长10位	Y	用户的姓名
缴费金额	字符	最长20位	Y	单位为分
交易时间	字符	时间格式为：YYYY-MM-DD HH:MM :SS	Y	格式为：YYYY-MM-DD HH:MM:SS
账务日期	字符	格式为：YYYYMMDD	Y	格式为：YYYYMMDD
    	 * */
    	
//    	T250005 t250005=(T250005)ProtocolDriverManager.instance("T250005", "T0000001", "BT000001");
//
//    	t250005.request.setCenterNumber(generator.nextSerialId());
//    	t250005.request.setRealTimePaymentNumber(generator.nextSerialId());
//    	t250005.request.setUserId("10000400");
//    	t250005.request.setUserName("王兰");
//    	t250005.request.setPaymentAmount(String.valueOf(100));
//    	t250005.request.setTransactionTime(DateConvertor.getTradeTime());
//    	t250005.request.setAccountTime(DateConvertor.getBankTime());    	
//
//			tradeClientProtocolHandler.send(t250005);
//    	
//    	log(t250005.getResponsePacket());
//    	
    	/*T250005 t250005=(T250005)ProtocolDriverManager.instance("T250005", "T0000001", "BT000001");

    	t250005.request.setCenterNumber(generator.nextSerialId());
    	t250005.request.setRealTimePaymentNumber(generator.nextSerialId());
    	t250005.request.setUserId("10000400");
    	t250005.request.setUserName("王兰");
    	t250005.request.setPaymentAmount(String.valueOf(100));
    	t250005.request.setTransactionTime(DateConvertor.getTradeTime());
    	t250005.request.setAccountTime(DateConvertor.getBankTime());    	

			tradeClientProtocolHandler.send(t250005);*/
    	
//    	log(t250005.getResponsePacket());
    	
    	
    	/*
    	 * 中心流水号	字符	最长20位	Y	用于中心标识每一笔请求交易（必须唯一）
用户编号	字符	最少8位，最长15位	Y	用户ID，该值对每个用户是唯一的
用户姓名	字符	最长10位	Y	用户的姓名
交易时间	字符	时间格式为：YYYY-MM-DD HH:MM:SS	Y	格式为：YYYY-MM-DD HH:MM:SS
账务日期	字符	格式为：YYYYMMDD	N	格式为：YYYYMMDD
    	 * */
//    	T250006 t250006=(T250006)ProtocolDriverManager.instance("T250006", "T0000001", "BT000001");
//
//    	t250006.request.setCenterNumber(generator.nextSerialId());
//    	t250006.request.setUserId("10000400");
//    	t250006.request.setUserName("王兰");
//    	t250006.request.setTransactionTime(DateConvertor.getTradeTime());
//    	t250006.request.setAccountTime(DateConvertor.getBankTime());    	
//    	try {
//			tradeClientProtocolHandler.send(t250006);
//		} catch (ProtocolProcessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	
//    	log(t250006.getResponsePacket());
    	
    	
    	/*
    	 * 中心流水号	字符	最长20位	Y	中心流水号用于标识每一笔汇付退款交易（必须唯一）
用户编号	字符	最少8位，最长15位	Y	用户ID，该值对每个用户是唯一的
用户姓名	字符	最长10位	Y	用户的姓名
汇付金额	字符	最长20位	Y	单位为分
账户	字符	最长20位	Y	银行卡号
交易时间	字符	时间格式为：YYYY-MM-DD HH:MM:SS	Y	格式为：YYYY-MM-DD HH:MM:SS
账务日期	字符	时间格式为：YYYYMMDD	Y	格式为：YYYYMMDD
    	 * */
//    	T250007 t250007=(T250007)ProtocolDriverManager.instance("T250007", "T0000001", "BT000001");
//
//    	t250007.request.setCenterNumber(generator.nextSerialId());
//    	t250007.request.setUserId("10000400");
//    	t250007.request.setUserName("王兰");
//    	t250007.request.setRemittanceAmount(String.valueOf(100));
//    	t250007.request.setAccount("5201521327808293");
//    	t250007.request.setTransactionTime(DateConvertor.getTradeTime());
//    	t250007.request.setAccountTime(DateConvertor.getBankTime());    	
//    	try {
//			tradeClientProtocolHandler.send(t250007);
//		} catch (ProtocolProcessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	
//    	log(t250007.getResponsePacket());
    	
    	
    	/*
    	 * 文件名称	字符		Y	文件名
文件大小	字符		Y	字节数
账务日期	字符	格式为：YYYYMMDD	Y	格式为：YYYYMMDD

    	 * */
//    	T250008 t250008=(T250008)ProtocolDriverManager.instance("250008", "T0000001", "BT000001");
//
//    	t250008.request.setFileName("trade");
//    	t250008.request.setFileSize(String.valueOf(100));
//    	t250008.request.setAccountTime(DateConvertor.getBankTime());    	
//    	
//			tradeClientProtocolHandler.send(t250008);
//		
//    	
//    	log(t250008.getResponsePacket());
    	
    	/*
    	 * 账务日期	字符	格式为：YYYYMMDD	Y	格式为：YYYYMMDD
    	 * */
//    	T250009 t250009=(T250009)ProtocolDriverManager.instance("T250009", "T0000001", "BT000001");
//
//    	t250009.request.setAccountTime(DateConvertor.getBankTime());    	
//    	try {
//			tradeClientProtocolHandler.send(t250009);
//		} catch (ProtocolProcessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	log(t250009.getResponsePacket());
    	
    	
    	/*
    	 * 绑定日期	字符		Y	格式为：YYYYMMDD(如果为空，表示查询所有的绑定信息)
    	 * */
//    	T250010 t250010=(T250010)ProtocolDriverManager.instance("T250010", "T0000001", "BT000001");
//
//    	t250010.request.setBindingTime(DateConvertor.getBankTime());
//    	
//    	try {
//			tradeClientProtocolHandler.send(t250010);
//		} catch (ProtocolProcessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	log(t250010.getResponsePacket());
    	
    	
    	/*
    	 * 账务日期	字符		Y	格式为：YYYYMMDD
    	 * */
    	T250011 t250011=(T250011)ProtocolDriverManager.instance("250011", "P0000001", "B0000002");

    	t250011.request.setAccountTime(DateConvertor.getBankTime());
 
		tradeClientProtocolHandler.sendWithoutMashall(t250011);

    	log(t250011.getResponsePacket());
    }
    
    
    public static void t250011(GlobalIdGenerator generator,DefaultTradeClientProtocolHandler tradeClientProtocolHandler){
    	T250011 t250011=(T250011)ProtocolDriverManager.instance("250011", "T0000003", "P0000001");

    	t250011.request.setAccountTime(DateConvertor.getBankTime());
 
		tradeClientProtocolHandler.send(t250011);

    	log(t250011.getResponsePacket());
    }
    
    public static void t250004(GlobalIdGenerator generator,DefaultTradeClientProtocolHandler tradeClientProtocolHandler){
    	for(int i=0;i<1;i++){
        	T250004 t250004=(T250004)ProtocolDriverManager.instance("250004", "T0000002", "B0000001");
        	t250004.request.setCenterNumber(generator.nextSerialId());
        	t250004.request.setUserId("10000400");
        	t250004.request.setUserName("王兰");
        	t250004.request.setPaymentType("01");
        	t250004.request.setPaymentAmount(String.valueOf(100));
        	t250004.request.setAccount("6228671133896465");
        	t250004.request.setTransactionTime(DateConvertor.getTradeTime());
        	t250004.request.setAccountTime(DateConvertor.getBankTime()); 
        	
        	
    			tradeClientProtocolHandler.send(t250004);
    			log(t250004.getResponsePacket());
        	}
        	
    }
    
    
    public static void t240003(GlobalIdGenerator generator,DefaultBankClientProtocolHandler bankclientProtocol){
    	for(int i=0;i<5;i++){
    	P240003 t=(P240003) ProtocolDriverManager.instance("240003", "B0000001", "T0000002");
    	t.request.setAccountTime(DateConvertor.getBankTime());
    	t.request.setPaymentAmount("2000");
    	t.request.setPaymentType("01");
    	t.request.setSerialNumber(generator.nextSerialId());
    	t.request.setTransactionTime(DateConvertor.getTradeTime());
    	t.request.setUserId("10000400");
    	t.request.setUserName("王兰");
    		bankclientProtocol.send(t);
    		
    		log(t.getRequestPacket());
    	
    	}
    	
    }
}
