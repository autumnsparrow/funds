//package com.palmcommerce.funds.trade.ibatis.impl;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//
//import com.palmcommerce.funds.protocol.impl.p2t.P240001;
//import com.palmcommerce.funds.protocol.impl.p2t.P240002;
//import com.palmcommerce.funds.protocol.impl.p2t.P240003;
//import com.palmcommerce.funds.protocol.impl.p2t.P240004;
//import com.palmcommerce.funds.protocol.impl.p2t.P240005;
//import com.palmcommerce.funds.protocol.trade.ITradeProtocolHandler;
//import com.palmcommerce.funds.util.SpringHelper;
//import com.palmcommerce.trade.service.ProtocolStorageException;
//
//import junit.framework.TestCase;
//
//public class OldTradeProtocolHandlerTest extends TestCase {
//	
//	private static final Log logger=LogFactory.getFactory().getLog(OldTradeProtocolHandlerTest.class);
//	ITradeProtocolHandler handler;
//
//	protected void setUp() throws Exception {
//		SpringHelper.init("classpath:META-INF/spring/applicationContext_trade_node_ibatis.xml");
//		handler=SpringHelper.getBean("protoclTradeHandler");
//	}
//
//	protected void tearDown() throws Exception {
//		super.tearDown();
//	}
//
//	public void testOldTradeProtocolHandler() {
//		
//	}
//
//	public void testP240001() {
//		String transcode="240001";
//		String from="B0001";
//		String to="T00002";
//		//boolean isRequest=false;
//		String request="BS10001|0100000001|2013-09-04 10:42:35|";
//		P240001 p=new P240001(transcode, from, to, request, true);
//		try {
//			handler.p240001(p);
//			logger.info(p.toString());
//		} catch (ProtocolStorageException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void testP240002() {
//		String transcode = "240002";
//		String from = "B0001";
//		String to = "T00002";
//		
//		String packet="BS10001|0100000001|wangwu|1|532923197802050038|0||2013-09-04 17:13:35|2013-09-04|";
//		P240002 p = new P240002(transcode, from, to, packet, true);
//		logger.info(p.toString());
//		try {
//			handler.p240002(p);
//		} catch (ProtocolStorageException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void testP240003() {
//		String transcode = "240003";
//		String from = "B0001";
//		String to = "T00002";
//		String packet = "0000030056|0100000001|wangwu|1|100|2013-09-04 17:13:35|2013-09-04|";
//		
//		P240003 p = new P240003(transcode, from, to, packet, true);
//		logger.info(p.toString());
//		try {
//			handler.p240003(p);
//		} catch (ProtocolStorageException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void testP240004() {
//		String transcode = "240004";
//		String from = "B0001";
//		String to = "T00002";
//		
//		String packet="BS10001|0000210|0100000001|wangwu|10|2013-09-04 17:13:35|2013-09-04|";
//		P240004 p = new P240004(transcode, from, to, packet, true);
//		logger.info(p.toString());
//		try {
//			handler.p240004(p);
//		} catch (ProtocolStorageException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();	
//		}
//	}
//
//	public void testP240005() {
//		String transcode = "240005";
//		String from = "B0001";
//		String to = "T00002";
//		
//		String packet="BS10001|wangliu|1|532923197802050038|15901336391||2013-09-04 17:13:35|2013-09-04|";
//		P240005 p = new P240005(transcode, from, to, packet, true);
//		logger.info(p.toString());
//		try {
//			handler.p240005(p);
//		} catch (ProtocolStorageException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
