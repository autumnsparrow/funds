/**
 * 
 */
package com.palmcommerce.funds.protocol.parser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.palmcommerce.funds.protocol.parser.exception.ProtocolParserException;

import junit.framework.TestCase;
import junit.framework.TestResult;

/**
 * @author sparrow
 *
 */
public class DefaultProtocolParserTest extends TestCase {
	private static final Log logger=LogFactory.getLog(DefaultProtocolParserTest.class);
	IProtocolParser parser;
	/**
	 * @param name
	 */
	public DefaultProtocolParserTest(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		parser=ProtocolParserFactory.getFactory().getParser();//new DefaultProtocolParser();
		
	}
	
	public void testParser() throws ProtocolParserException{
		
		String content="240001|0000030049|0001|0100000001|lisi|1|532923197802050038|0||20090420|";

		String[] result=parser.parse(content);
		
		logger.info("Size:"+result.length);
		for(String element:result){
			logger.info("----"+element);
		}
		
		assertTrue(result.length==10);
	}

	@Override
	protected TestResult createResult() {
		// TODO Auto-generated method stub
		return super.createResult();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
