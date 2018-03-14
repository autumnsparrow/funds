/**
 * 
 */
package com.palmcommerce.funds.protocol.parser;

/**
 * @author sparrow
 *
 */
public class ProtocolParserFactory {

	/**
	 * 
	 */
	public ProtocolParserFactory() {
		// TODO Auto-generated constructor stub
	}

	private static final ProtocolParserFactory instance=new ProtocolParserFactory();
	
	public static ProtocolParserFactory getFactory(){
		return instance;
	}
	
	private static final DefaultProtocolParser parser=new DefaultProtocolParser();
	public IProtocolParser getParser(){
		return parser;
	}
}
