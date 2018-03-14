/**
 * 
 */
package com.palmcommerce.funds.ice.trade.ice.impl;

import org.springframework.beans.factory.annotation.Autowired;

import Ice.Current;
import Ice.StringHolder;

import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;
import com.palmcommerce.funds.protocol.impl.IProtocol;
import com.palmcommerce.funds.service.ProtocolProcessException;
import com.palmcommerce.funds.service._ProtocolProcessorDisp;
import com.palmcommerce.funds.trade.impl.DefaultBankClientProtocolHandler;
import com.palmcommerce.funds.trade.impl.DefaultTradeClientProtocolHandler;

/**
 * @author sparrow
 *
 */
@SuppressWarnings("serial")
public class TradeServerProcessI extends _ProtocolProcessorDisp {
	
	@Autowired 
	DefaultTradeClientProtocolHandler tradeClientProtocolHandler; 

	/**
	 * 
	 */
	public TradeServerProcessI() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.service._ProtocolProcessorOperations#doProcess(java.lang.String, java.lang.String, java.lang.String, java.lang.String, Ice.StringHolder, Ice.Current)
	 */
	public boolean doProcess(String transcode, String fromcode, String tocode,
			String request, StringHolder response, Current __current)
			throws ProtocolProcessException {
		// TODO Auto-generated method stub
		IProtocol protocol = ProtocolDriverManager.instance(transcode,
				fromcode, tocode, request, true);

		boolean ret = true;
		try {
			protocol.unmashall(true);
			tradeClientProtocolHandler.send(protocol);
			//handle(protocol);
			protocol.mashall(false);
			response.value = protocol.getResponsePacket();

		} catch (ProtocolConvertorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			protocol.mashallException(e.getState(), e.getMessage());
			response.value = protocol.getResponsePacket();
			ret = false;
		}

		return ret;
	}

}
