package com.palmcommerce.funds.protocol.impl.t2p;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;

import junit.framework.TestCase;

public class T250004Test extends TestCase {
	private static final Log logger = LogFactory.getFactory().getLog(T250004.class);
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testT250004() {
		String transcode = "250004";
		String from = "B0001";
		String to = "T00002";
		
		String packet="TS10001|0100000001|lisi|01|200|00045244534|2013-09-04 17:13:35|2013-09-04|";
		T250004 p = new T250004(transcode, from, to, packet, true);
		logger.info(p.toString());
		
		
		from = "T00002";
		to="B0001";
		packet="0000||TS10001|BS10001|0100000001|lisi|00045244534|";
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
