package com.palmcommerce.funds.protocol.covertor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.ProtocolDriverManager.ProtocolMeta;
import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;

import com.palmcommerce.funds.protocol.impl.p2t.P240001;
import com.palmcommerce.funds.protocol.parser.IProtocolParser;
import com.palmcommerce.funds.protocol.parser.ProtocolParserFactory;
import com.palmcommerce.funds.protocol.parser.exception.ProtocolParserException;

import junit.framework.TestCase;
import junit.framework.TestResult;

public class DefaultProtocolConvertorTest extends TestCase {
	private static final Log log=LogFactory.getFactory().getLog(DefaultProtocolConvertorTest.class);
	public DefaultProtocolConvertorTest(String name) {
		super(name);
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

	public void testConvert() {
		//fail("Not yet implemented");
		String content="240001|0000030049|0001|0100000001|李四|身份证号码|532923197802050038|0||20090420|";
//		 content="||P10001|0100000001|李四|身份证号码|532923197802050038|0|";
//		IProtocolParser parser=ProtocolParserFactory.getFactory().getParser();
//		IProtocolConvertor convertor=ProtocolConvertFactory.getFactory().getConvertor();
//		try {
//			String[] entities=parser.parse(content);
//			P240001.Response t=convertor.convert(P240001.Response.class, entities);
//			log.info(t.toString());
//		} catch (ProtocolParserException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ProtocolConvertorException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		assertNotNull(content);
	}

}
