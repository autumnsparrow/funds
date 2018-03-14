/**
 * 
 */
package com.palmcommerce.funds.protocol.impl;

import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;

/**
 * @author sparrow
 *
 */
public interface IProtocol {
	
	public static final String DEFAULT_FROM_CODE="00000000";
	
	
	/*
	 * 
	 * 
	 */
	public void setGlobalSerial(String globalSerial);
/**
 * 
 * 
 * @return
 */
	public String getGlobalSerial();
	
	/**
	 * 
	 * @return
	 */
	public Header getHeader();
	/**
	 * 
	 * @param isRequest
	 * @throws ProtocolConvertorException
	 */
	public void unmashall(boolean isRequest) throws ProtocolConvertorException;
	/**
	 * 
	 * @param isRequest
	 * @throws ProtocolConvertorException
	 */
	public void mashall(boolean isRequest) throws ProtocolConvertorException;
	
	
	/**
	 * 
	 * 
	 * @return
	 */
	public String getRequestPacket();
	/**
	 * 
	 * @return
	 */
	public String getResponsePacket();
	
	
	/**
	 * 
	 * @param ex
	 */
	public void mashallException(String state,String message);
	
	
	public void setResponsePacket(String response);
	public void setRequestPacket(String request);
	
	
	
	
	
	public  Class<?> getRequestClazz();

	public  Class<?> getResponseClazz();
	
	public  <T> void setRequestObject(T obj);
	public  <T> void setResponseObject(T obj);
	public  <T> T getRequestObject();
	public  <T> T getResponseObject();
	
}
