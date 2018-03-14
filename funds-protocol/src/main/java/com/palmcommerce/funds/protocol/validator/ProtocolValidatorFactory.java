/**
 * 
 */
package com.palmcommerce.funds.protocol.validator;

/**
 * @author sparrow
 *
 */
public class ProtocolValidatorFactory {

	/**
	 * 
	 */
	public ProtocolValidatorFactory() {
		// TODO Auto-generated constructor stub
	}
	
	private static final ProtocolValidatorFactory instance=new ProtocolValidatorFactory();
	
	public static ProtocolValidatorFactory getFactory(){
		return instance;
	}
	
	private static final DefaultProtocolValidator validator=new DefaultProtocolValidator();
	public IProtocolValidator getValidator(){
		return validator;
	}

}
