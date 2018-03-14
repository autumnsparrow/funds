/**
 * 
 */
package com.palmcommerce.funds.protocol.covertor;

/**
 * @author sparrow
 *
 */
public class ProtocolConvertFactory {
	
	private static final ProtocolConvertFactory instance=new ProtocolConvertFactory();

	/**
	 * 
	 */
	public ProtocolConvertFactory() {
		// TODO Auto-generated constructor stub
	}
	
	private static final DefaultProtocolConvertor convertor=new DefaultProtocolConvertor();
	public IProtocolConvertor getConvertor(){
		return convertor;
	}
	
	public static ProtocolConvertFactory getFactory(){
		return instance;
	}
}
