package com.palmcommerce.funds.protocol.covertor.exception;

import com.palmcommerce.funds.protocol.trade.IStatus;

public class ProtocolConvertorException extends Exception implements IStatus{

	public ProtocolConvertorException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8885602953868137156L;
	
	String state;

	
	
	public String getState() {
		return state;
	}

	public ProtocolConvertorException(String state,String message) {
		super(message);
		this.state = state;
	}

	public ProtocolConvertorException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProtocolConvertorException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}



	public ProtocolConvertorException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
