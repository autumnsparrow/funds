package com.palmcommerce.funds.trade.server;



import com.palmcommerce.funds.connection.mina.ctx.MinaContext;
import com.palmcommerce.funds.id.impl.GlobalIdGenerator;
import com.palmcommerce.funds.packet.process.IProtocolProcessor;

import com.palmcommerce.funds.util.SpringHelper;

/**
 * Hello world!
 * 
 */
public class MainTradeServer {
	public static void log(String message) {
		System.out.println(message);
	}

	public static void main(String[] args) {

		SpringHelper.init(new String[] {
						//"classpath:META-INF/spring/applicationContext-funds-ca-rest-client.xml",
						"classpath:META-INF/spring/applicationContext-funds-configuration-local.xml",
						"classpath:META-INF/spring/applicationContext-funds-id.xml",
						"classpath:META-INF/spring/applicationContext-funds-packet.xml",
						"classpath:META-INF/spring/applicationContext-funds-ice-metrics.xml",
						"classpath:META-INF/spring/applicationContext-funds-connection-without-filter.xml",
						"classpath:META-INF/spring/applicationContext-funds-trade-ibatis.xml",
						
						//"classpath:META-INF/spring/applicationContext-funds-bank.xml",
						});

		// // Here can get the caRestClient
		// // copy the funds-ca-rest-client.properties into /META-INF/funds
		// CaRestClient caRestClient=SpringHelper.getBean("caRestClient");
		// log("caRestBaseUrl ---> "+caRestClient.getBaseUrl());
		//
		// // copy funds-configuraiton.properties,listeners.xml
		// //
		// ConfigurationManager
		// configurationManager=(ConfigurationManager)SpringHelper.getBean("configurationManager");
		//
		// log("configuration:config  ---> "+configurationManager.getConfig());
		//

		//
		// log(" global id generator:"+generator.getDelayTimeSeconds());
		//
		// IPacketSecurity
		// packetSecurity=SpringHelper.getBean("packetSecurity");
		//
		// log("packet :"+packetSecurity.toString());
		//

		// IProtocolFilter
		// idGeneratorProtocolFilter=SpringHelper.getBean("idGeneratorProtocolFilterHandler");
		// // if using the i
		final GlobalIdGenerator generator = SpringHelper
				.getBean("globalIdGenerator");
		generator.start();

		// T250004 p250004;

		IProtocolProcessor tradeServerProtocolHandler = SpringHelper
				.getBean("oldTradeProtocolHandler");
//		final DefaultTradeClientProtocolHandler tradeClientProtocolHandler = SpringHelper
//				.getBean("tradeClientProtocolHandler");

		MinaContext minaContext = SpringHelper.getBean("minaContext");

		minaContext.setServerSideProtocolProcessor(tradeServerProtocolHandler);
		//minaContext.setClientSideProtocolProcessor(tradeClientProtocolHandler);
		// only the proxy model need that part.
		// server or client don't need that id generator .
		// minaContext.setProtocolFilter(idGeneratorProtocolFilter);

		minaContext.startWithAcceptorOrConnectorMode();
		
		
		//资金管理平台|银行流水号1|用户编号1|用户姓名1|金额1|账户1| 交易时间1(YYYY-MM-DD HH:MM:SS) |8银行代码(00000001)| 账务日期(YYYYMMDD )|类型(
		
//		List<String> lines=new LinkedList<String>();
//		
//		for(int i=0;i<1000000;i++){
//			String global=generator.nextSerialId();
//			String paymentserial=generator.nextSerialId().substring(5);
//			String userId=SerialGenerator.generateUserId();
//			String userName="王兰";//SerialGenerator.generateUserName();
//			String amount=String.valueOf((int)(Math.random()*10000));
//			String account="52929383839499449";
//			String trade=SerialGenerator.getTransactionDatetime();
//			
//			String bankCode=String.format("B%07d", (int)(Math.random()*10+1));
//			String bank=SerialGenerator.getBankDatetime();
//			String line=String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s|0|"
//					,global,paymentserial,userId,userName,amount,account,trade,bankCode,bank );
//			lines.add(line);
//		}
//		
//		try {
//			FileUtils.writeLines(new File(String.format("ChargeData.%s.P0000001.txt",SerialGenerator.getBankDatetime())), lines, "\r\n");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		final T250004 t250004 = (T250004) ProtocolDriverManager.instance(
//				"250004", "T0000002", "B0000001");
//
//		t250004.request.setAccount("5201521327808293");
//		t250004.request.setAccountTime(DateConvertor.getBankTime());
//
//		t250004.request.setPaymentAmount(String.valueOf(100));
//		t250004.request.setPaymentType("0" + String.valueOf(2));
//		t250004.request.setTransactionTime(DateConvertor.getTradeTime());
//		t250004.request.setUserId("10000400");
//		t250004.request.setUserName("王兰");
//
//		//minaContext.schedule(task)
//		for (int i = 0; i < 1; i++)
//			minaContext.execute(new Task(t250004, tradeClientProtocolHandler,generator));

		/*
		 * long t=System.currentTimeMillis(); for(int i=0;i<1000;i++){
		 * 
		 * t250004.request.setCenterNumber(generator.nextSerialId());
		 * tradeClientProtocolHandler.send(t250004);
		 * 
		 * 
		 * } log("100 packet using:"+(System.currentTimeMillis()-t));
		 */

	}

//	static class Task implements Runnable {
//
//		T250004 t250004;
//		DefaultTradeClientProtocolHandler tradeClientProtocolHandler;
//		GlobalIdGenerator generator;
//
//		public Task(T250004 t250004,
//				DefaultTradeClientProtocolHandler tradeClientProtocolHandler,GlobalIdGenerator generator) {
//			super();
//			this.t250004 = t250004;
//			this.tradeClientProtocolHandler = tradeClientProtocolHandler;
//			this.generator = generator;
//
//		}
//
//		public void run() {
//			// TODO Auto-generated method stub
//			long t = System.currentTimeMillis();
//			for (int i = 0; i < 1; i++) {
//
//				t250004.request.setCenterNumber(generator.nextSerialId());
//				long tt = System.currentTimeMillis();
//				tradeClientProtocolHandler.send(t250004);
//				log("  =========== :" + (System.currentTimeMillis() - tt));
//
//			}
//			log("100 packet using:" + (System.currentTimeMillis() - t));
//		}
	//}

}
