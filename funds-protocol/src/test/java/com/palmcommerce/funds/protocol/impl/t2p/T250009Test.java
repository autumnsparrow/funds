package com.palmcommerce.funds.protocol.impl.t2p;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;

import junit.framework.TestCase;

public class T250009Test extends TestCase {
	private static final Log logger = LogFactory.getFactory().getLog(T250009.class);
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testT250009() {
		String transcode = "250009";
		String from = "B0001";
		String to = "T00002";
		
		String packet="2013-09-04|";
		T250009 p = new T250009(transcode, from, to, packet, true);
		logger.info(p.toString());
		
		
		from = "T00002";
		to="B0001";
		packet="0000||100|";
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
