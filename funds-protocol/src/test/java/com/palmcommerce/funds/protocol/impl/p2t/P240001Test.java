package com.palmcommerce.funds.protocol.impl.p2t;

import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;

import junit.framework.TestCase;

public class P240001Test extends TestCase {
	private static final Log logger=LogFactory.getFactory().getLog(P240001Test.class);
//	public P240001Test(String name) {
//		super(name);
//	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	
	
	public void testP240001() {
		

		String transcode="240001";
		String from="B0001";
		String to="T00002";
		//boolean isRequest=false;
		String request="BS10001|0100000001|2013-09-04 10:42:35|";
		
		P240001 p=new P240001(transcode, from, to, request, true);
		try {
			p.unmashall(true);
		} catch (ProtocolConvertorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			p.mashallException(e.getState(), e.getMessage());
		}
		
		
		logger.info(p.toString());
		// succeed response.
		
		
//		
//		from="T00002";
//		to="B0001";
//		//packet="0000||BS10001|0100000001|李四|身份证号码|532923197802050038|0|";
//		String response="0000||BS100010000|0100000001|lisi|0|532923197802050038|0|";
//		//response="0000|ss|||||||";
//		//response="0000||||||||";
//		logger.info(response);
//		//isRequest=false;
//		try {
//			p.update(transcode, from, to, response, false);
//		} catch (ProtocolConvertorException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//P240001 response=new P240001(transcode, from, to, packet, false);
//		
//		logger.info(p.toString());
//		P240001 pp=new P240001(transcode, from, to, request, response);
//		logger.info(pp.toString());		
//		
//		try {
//			pp.convert(true);
//		} catch (ProtocolConvertorException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		logger.info(pp.getRequestPacket());
//		try {
//			pp.convert(false);
//		} catch (ProtocolConvertorException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		logger.info(pp.getResponsePacket());
		assertNotNull(p);
		//fail("Not yet implemented");
	}

}
