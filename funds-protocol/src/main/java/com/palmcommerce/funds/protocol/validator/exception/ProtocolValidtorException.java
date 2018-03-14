/**
 * 
 */
package com.palmcommerce.funds.protocol.validator.exception;

import com.palmcommerce.funds.protocol.trade.IStatus;

/**
 * @author sparrow
 *
 */
public class ProtocolValidtorException extends Exception implements IStatus{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1326528674488187956L;
	
	String state;
	
	
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public ProtocolValidtorException(String state,String message) {
		super(message);
		this.state = state;
	}

	/**
	 * 
	 */
	public ProtocolValidtorException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public ProtocolValidtorException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ProtocolValidtorException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ProtocolValidtorException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
