package com.palmcommerce.funds.roo;


import com.palmcommerce.funds.connection.mina.ctx.MinaContext;
import com.palmcommerce.funds.packet.process.IProtocolFilter;
import com.palmcommerce.funds.packet.process.IProtocolProcessor;
import com.palmcommerce.funds.packet.security.IPacketSecurity;
import com.palmcommerce.funds.packet.security.PacketSecurityHandler;
import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.impl.t2p.T250004;
import com.palmcommerce.funds.protocol.impl.t2p.T250005;
import com.palmcommerce.funds.protocol.impl.t2p.T250006;
import com.palmcommerce.funds.protocol.impl.t2p.T250007;
import com.palmcommerce.funds.protocol.impl.t2p.T250008;
import com.palmcommerce.funds.protocol.impl.t2p.T250009;
import com.palmcommerce.funds.protocol.impl.t2p.T250010;
import com.palmcommerce.funds.protocol.impl.t2p.T250011;
import com.palmcommerce.funds.roo.protocol.impl.ScheduledTradeClientProtocolHandler;
import com.palmcommerce.funds.service.ProtocolProcessException;
import com.palmcommerce.funds.util.DateConvertor;
import com.palmcommerce.funds.util.SpringHelper;

/**
 * Hello world!
 *
 */
public class AppTest 
{
	
	public static void log(String message){
		System.out.println(message);
	}
	
	
    public static void main( String[] args )
    {
       	
    	SpringHelper.init(new String[]{
    			"classpath:META-INF/spring/applicationContext-funds-ca-rest-client.xml",
    			"classpath:META-INF/spring/applicationContext-funds-ice-metrics.xml",
    			"classpath:META-INF/spring/applicationContext-funds-configuration.xml",
    			
    			"classpath:META-INF/spring/applicationContext-funds-packet.xml",
    			//"classpath:META-INF/spring/applicationContext-funds-protocol.xml",
    			//"classpath:META-INF/spring/applicationContext-funds-id.xml",
    			"classpath:META-INF/spring/applicationContext-funds-connection-without-filter.xml",
    			
    			"classpath:META-INF/spring/applicationContext-funds-roo*.xml",
    			"classpath:META-INF/spring/applicationContext-funds-sftp-*.xml"
    	});
    	
//    	GlobalIdGenerator generator=SpringHelper.getBean("globalIdGenerator");
//    	generator.start();
    	
    	
    	ScheduledTradeClientProtocolHandler scheduledTradeClientProtocolHandler = SpringHelper.getBean("scheduledTradeClientProtocolHandler");
    	MinaContext minaContext=SpringHelper.getBean("minaContext");
//    	
//    	 	
//    	minaContext.setServerSideProtocolProcessor(tradeServerProtocolHandler);
    	
//    	// only the proxy model need that part.
//    	// server or client don't need that id generator .
//    	//minaContext.setProtocolFilter(idGeneratorProtocolFilter);
//    	
//    	
//    	
    	minaContext.startWithAcceptorOrConnectorMode();
    	
    	
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

    	T250004 t250004=(T250004)ProtocolDriverManager.instance("250004", "T0000003", "BT000001");
//    	t250004.request.setCenterNumber(generator.nextSerialId());
    	t250004.request.setCenterNumber("201311235684123");
    	t250004.request.setUserId("40018009");
    	t250004.request.setUserName("王兰");
    	t250004.request.setPaymentType("01");
    	t250004.request.setPaymentAmount(String.valueOf(100));
    	t250004.request.setAccount("6228671133896465");
    	t250004.request.setTransactionTime(DateConvertor.getTradeTime());
    	t250004.request.setAccountTime(DateConvertor.getBankTime()); 
  	
    	scheduledTradeClientProtocolHandler.send(t250004);
    	log(t250004.getResponsePacket());
    	
    	
    	/*
    	 * 中心流水号	字符	最长20位	Y	用于中心标识每一笔请求交易（必须唯一）
实时缴款流水号	字符	最长20位	Y	上一笔未成功缴款交易的流水号
用户编号	字符	最少8位，最长15位	Y	用户ID，该值对每个用户是唯一的
用户姓名	字符	最长10位	Y	用户的姓名
缴费金额	字符	最长20位	Y	单位为分
交易时间	字符	时间格式为：YYYY-MM-DD HH:MM :SS	Y	格式为：YYYY-MM-DD HH:MM:SS
账务日期	字符	格式为：YYYYMMDD	Y	格式为：YYYYMMDD
    	 * */   
    	
//		T250005 t250005=(T250005)ProtocolDriverManager.instance("250005", "T0000003", "BT000001");
////    	t250005.request.setCenterNumber(generator.nextSerialId());
////    	t250005.request.setRealTimePaymentNumber(generator.nextSerialId());
//    	t250005.request.setCenterNumber("201311235684123");
//    	t250005.request.setRealTimePaymentNumber("20131123568412321");
//    	t250005.request.setUserId("10000400");
//    	t250005.request.setUserName("王兰");
//    	t250005.request.setPaymentAmount(String.valueOf(100));
//    	t250005.request.setTransactionTime(DateConvertor.getTradeTime());
//    	t250005.request.setAccountTime(DateConvertor.getBankTime());    	
//
//    	scheduledTradeClientProtocolHandler.send(t250005);
//    	
//    	log(t250005.getResponsePacket());
    	
    	
    	/*
    	 * 中心流水号	字符	最长20位	Y	用于中心标识每一笔请求交易（必须唯一）
用户编号	字符	最少8位，最长15位	Y	用户ID，该值对每个用户是唯一的
用户姓名	字符	最长10位	Y	用户的姓名
交易时间	字符	时间格式为：YYYY-MM-DD HH:MM:SS	Y	格式为：YYYY-MM-DD HH:MM:SS
账务日期	字符	格式为：YYYYMMDD	N	格式为：YYYYMMDD
    	 * */
//    	T250006 t250006=(T250006)ProtocolDriverManager.instance("250006", "T0000003", "BT000001");
////    	t250006.request.setCenterNumber(generator.nextSerialId());
//    	t250006.request.setCenterNumber("201311235684123");
//    	t250006.request.setUserId("10000400");
//    	t250006.request.setUserName("王兰");
//    	t250006.request.setTransactionTime(DateConvertor.getTradeTime());
//    	t250006.request.setAccountTime(DateConvertor.getBankTime());    	
//
//    	scheduledTradeClientProtocolHandler.send(t250006);
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
//    	T250007 t250007=(T250007)ProtocolDriverManager.instance("250007", "T0000003", "BT000001");
////    	t250007.request.setCenterNumber(generator.nextSerialId());
//    	t250007.request.setCenterNumber("201311235684123");
//    	t250007.request.setUserId("10000400");
//    	t250007.request.setUserName("王兰");
//    	t250007.request.setRemittanceAmount(String.valueOf(100));
//    	t250007.request.setAccount("5201521327808293");
//    	t250007.request.setTransactionTime(DateConvertor.getTradeTime());
//    	t250007.request.setAccountTime(DateConvertor.getBankTime());    	
//
//    	scheduledTradeClientProtocolHandler.send(t250007);
//
//    	log(t250007.getResponsePacket());
    	
    	
    	/*
    	 * 文件名称	字符		Y	文件名
文件大小	字符		Y	字节数
账务日期	字符	格式为：YYYYMMDD	Y	格式为：YYYYMMDD

    	 * */
//    	T250008 t250008=(T250008)ProtocolDriverManager.instance("250008", "T0000003", "BT000001");
//
//    	t250008.request.setFileName("trade");
//    	t250008.request.setFileSize(String.valueOf(100));
//    	t250008.request.setAccountTime(DateConvertor.getBankTime());    	
//    	
//    	scheduledTradeClientProtocolHandler.send(t250008);
//    	
//    	log(t250008.getResponsePacket());
    	
    	/*
    	 * 账务日期	字符	格式为：YYYYMMDD	Y	格式为：YYYYMMDD
    	 * */
//    	T250009 t250009=(T250009)ProtocolDriverManager.instance("250009", "T0000003", "BT000001");
//
//    	t250009.request.setAccountTime(DateConvertor.getBankTime());    	
//
//    	scheduledTradeClientProtocolHandler.send(t250009);
//
//    	log(t250009.getResponsePacket());
    	
    	
    	/*
    	 * 绑定日期	字符		Y	格式为：YYYYMMDD(如果为空，表示查询所有的绑定信息)
    	 * */
//    	T250010 t250010=(T250010)ProtocolDriverManager.instance("250010", "T0000003", "BT000001");
//
//    	t250010.request.setBindingTime(DateConvertor.getBankTime());
//
//    	scheduledTradeClientProtocolHandler.send(t250010);
//
//    	log(t250010.getResponsePacket());
    	
    	
    	/*
    	 * 账务日期	字符		Y	格式为：YYYYMMDD
    	 * */
//    	T250011 t250011=(T250011)ProtocolDriverManager.instance("250011", "T0000003", "BT000001");
//
//    	t250011.request.setAccountTime("20131120");
// 
//    	scheduledTradeClientProtocolHandler.send(t250011);
//
//    	log(t250011.getResponsePacket());
    }
}
