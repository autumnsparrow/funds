/**
 * 
 */
package com.palmcommerce.funds.protocol.parser;

import com.palmcommerce.funds.protocol.parser.exception.ProtocolParserException;

/**
 * @author sparrow
 *
 */
public interface IProtocolParser {
	
	
	/**
	 * 
	 * parse the protocol.
	 * 
	 * @param packet
	 * @return
	 * @throws ProtocolParserException
	 */
	public String[]  parse(String packet) throws ProtocolParserException;

}
