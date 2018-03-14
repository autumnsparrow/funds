package com.palmcommerce.funds.protocol.impl.p2t;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;

import junit.framework.TestCase;

public class P240004Test extends TestCase {
	private static final Log logger = LogFactory.getFactory().getLog(P240004.class);
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testP240004() {
		String transcode = "240004";
		String from = "B0001";
		String to = "T00002";
		
		String packet="BS10001|0000210|0100000001|wangwu|10|2013-09-04 17:13:35|2013-09-04|";
		P240004 p = new P240004(transcode, from, to, packet, true);
		logger.info(p.toString());
		
		
		from = "T00002";
		to="B0001";
		packet="0000||BS10001|0000210|";
		logger.info(packet);
		try {
			p.unmashall(true);
		} catch (ProtocolConvertorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info(p.toString());
		assertNotNull(p);
	}

}
