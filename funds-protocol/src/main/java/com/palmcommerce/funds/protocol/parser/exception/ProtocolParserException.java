/**
 * 
 */
package com.palmcommerce.funds.protocol.parser.exception;

/**
 * @author sparrow
 *
 */
public class ProtocolParserException extends Exception {

	/**
	 * 
	 */
	public ProtocolParserException() {
		// TODO Auto-generated constructor stub
	}
	
	String state;
	

	public String getState() {
		return state;
	}

	/**
	 * @param arg0
	 */
	public ProtocolParserException(String state,String message) {
		super(message);
		this.state=state;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public ProtocolParserException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ProtocolParserException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
