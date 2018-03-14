package com.palmcommerce.funds.protocol.impl.t2p;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;

import junit.framework.TestCase;

public class T250005Test extends TestCase {
	private static final Log logger = LogFactory.getFactory().getLog(T250005.class);
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testT250005() {
		String transcode = "250005";
		String from = "B0001";
		String to = "T00002";
		
		String packet="TS10001|0003240023|01000001|lisi|100|2013-09-04 17:13:35|2013-09-04|";
		T250005 p = new T250005(transcode, from, to, packet, true);
		logger.info(p.toString());
		
		
		from = "T00002";
		to="B0001";
		packet="0000||TS10001|05340340|04303404|01000001|lisi|";
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
