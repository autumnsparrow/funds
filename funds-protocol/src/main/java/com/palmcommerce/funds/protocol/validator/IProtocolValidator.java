/**
 * 
 */
package com.palmcommerce.funds.protocol.validator;

import com.palmcommerce.funds.protocol.validator.exception.ProtocolValidtorException;

/**
 * @author sparrow
 *
 */
public interface IProtocolValidator {
	
	/**
	 * 
	 * validate the protocol 
	 * 
	 * @param entities
	 * @return
	 * @throws ProtocolValidtorException
	 */
	public boolean validate(Class<?> identifier,String[] entities)throws ProtocolValidtorException;

}
