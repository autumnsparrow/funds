/**
 * 
 */
package com.palmcommerce.funds.protocol.covertor;


import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;

/**
 * @author sparrow
 *
 */
public interface IProtocolConvertor {
	
	public <T> T convert(Class<?> identifier,String[] entities) throws ProtocolConvertorException;
	public <T> String convert(T t) throws ProtocolConvertorException;
	
	public <T> T convertException(Class<?> identifier,String state,String reason) ;
}
