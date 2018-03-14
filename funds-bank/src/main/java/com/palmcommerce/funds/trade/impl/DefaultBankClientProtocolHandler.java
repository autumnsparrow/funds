/**
 * 
 */
package com.palmcommerce.funds.trade.impl;

import Ice.StringHolder;


import com.palmcommerce.funds.connection.mina.protocol.AbstractClientSideProcessor;

import com.palmcommerce.funds.packet.process.IProtocolProcessor;
import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;

import com.palmcommerce.funds.protocol.impl.IProtocol;
import com.palmcommerce.funds.protocol.impl.p2t.P240001;
import com.palmcommerce.funds.protocol.impl.p2t.P240002;
import com.palmcommerce.funds.protocol.impl.p2t.P240003;
import com.palmcommerce.funds.protocol.impl.p2t.P240004;
import com.palmcommerce.funds.protocol.impl.p2t.P240005;
import com.palmcommerce.funds.protocol.impl.p2t.P240006;
import com.palmcommerce.funds.protocol.trade.ITradeProtocolHandler;
import com.palmcommerce.funds.service.ProtocolProcessException;
import com.palmcommerce.funds.service.ProtocolStorageException;


/**
 * @author sparrow
 *
 */
public class DefaultBankClientProtocolHandler extends AbstractClientSideProcessor implements ITradeProtocolHandler {

	

	/**
	 * 
	 */
	public DefaultBankClientProtocolHandler() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.trade.ITradeProtocolHandler#p240001(com.palmcommerce.funds.protocol.impl.p2t.P240001)
	 */
	public boolean p240001(P240001 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		boolean ret=false;
		// doing the jobs that the protocol alread returned.
		
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.trade.ITradeProtocolHandler#p240002(com.palmcommerce.funds.protocol.impl.p2t.P240002)
	 */
	public boolean p240002(P240002 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		boolean ret=false;
	
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.trade.ITradeProtocolHandler#p240003(com.palmcommerce.funds.protocol.impl.p2t.P240003)
	 */
	public boolean p240003(P240003 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		boolean ret=false;
		
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.trade.ITradeProtocolHandler#p240004(com.palmcommerce.funds.protocol.impl.p2t.P240004)
	 */
	public boolean p240004(P240004 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		boolean ret=false;
	
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.trade.ITradeProtocolHandler#p240005(com.palmcommerce.funds.protocol.impl.p2t.P240005)
	 */
	public boolean p240005(P240005 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		boolean ret=false;
		
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.trade.ITradeProtocolHandler#p240006(com.palmcommerce.funds.protocol.impl.p2t.P240006)
	 */
	public boolean p240006(P240006 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.connection.mina.protocol.AbstractClientSideProcessor#handle(com.palmcommerce.funds.connection.mina.protocol.IProtocol)
	 */
	@Override
	public void handle(IProtocol protocol) throws ProtocolProcessException {
		// TODO Auto-generated method stub
		try {
			switch (protocol.getHeader().transcode) {

			case 240001: {
				P240001 p=(P240001)protocol;
				
				p240001(p);
					
			}
				// paymentService.p240001((P240001)header);
				break;

			case 240002: {

				P240002 p=(P240002)protocol;
				p240002(p);
					
				
			}
			
				break;

			case 240003: {

				P240003 p=(P240003)protocol;
				
				
				p240003(p);
					
			}
				// paymentService.p240001((P240001)header);
				break;

			case 240004: {

				P240004 p=(P240004)protocol;
				
				
				p240004(p);
					
				
			}
				
				break;

			case 240005: {

				P240005 p=(P240005)protocol;
				
				p240005(p);
					
				
			}
				// paymentService.p240001((P240001)header);
				break;

			default:{
				// non handler.
				
				
			}
				break;
			}
		} catch (ProtocolStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ProtocolProcessException(e.state, e.reason);
		}
		
		
	}

	

}
