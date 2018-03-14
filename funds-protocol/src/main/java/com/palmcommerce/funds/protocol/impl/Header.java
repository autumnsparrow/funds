/**
 * 
 */
package com.palmcommerce.funds.protocol.impl;

import com.palmcommerce.funds.protocol.covertor.IProtocolConvertor;
import com.palmcommerce.funds.protocol.covertor.ProtocolConvertFactory;
import com.palmcommerce.funds.protocol.parser.IProtocolParser;
import com.palmcommerce.funds.protocol.parser.ProtocolParserFactory;

/**
 * @author sparrow
 *
 */
public class Header {
	
	public int transcode;
	public String from;
	public String to;
	public boolean isRequest;
	
	public IProtocolConvertor convetor;
	public IProtocolParser parser;
	
	public Header(String transcode, String from, String to,boolean isRequest) {
		super();
		
		this.convetor=ProtocolConvertFactory.getFactory().getConvertor();
		this.parser=ProtocolParserFactory.getFactory().getParser();
		header(transcode,from,to,isRequest);
		
	}
 
	
	public void header(String transcode, String from, String to,boolean isRequest){
	
		this.transcode = Integer.parseInt(transcode);
		this.from = from;
		this.to = to;
		this.isRequest=isRequest;
		
	}
	

}
