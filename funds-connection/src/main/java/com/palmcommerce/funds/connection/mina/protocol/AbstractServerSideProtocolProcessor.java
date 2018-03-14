/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 27, 2013
 *
 */
package com.palmcommerce.funds.connection.mina.protocol;

import Ice.StringHolder;

import com.palmcommerce.funds.connection.mina.ctx.MinaContext;
import com.palmcommerce.funds.packet.process.IProtocolProcessor;
import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;
import com.palmcommerce.funds.protocol.impl.Header;
import com.palmcommerce.funds.protocol.impl.IProtocol;
import com.palmcommerce.funds.service.ProtocolProcessException;
import com.palmcommerce.funds.service.ProtocolStorageException;
import com.palmcommerce.funds.connection.mina.ctx.MinaContext;
/**
 * @author sparrow
 *
 */
public abstract class AbstractServerSideProtocolProcessor implements
		IProtocolProcessor {
	
	MinaContext minaContext;
	
	

	/**
	 * @return the minaContext
	 */
	public MinaContext getMinaContext() {
		return minaContext;
	}

	/**
	 * @param minaContext the minaContext to set
	 */
	public void setMinaContext(MinaContext minaContext) {
		this.minaContext = minaContext;
	}

	/**
	 * 
	 */
	public AbstractServerSideProtocolProcessor() {
		// TODO Auto-generated constructor stub
	}

	public abstract void handle(IProtocol protocol) throws ProtocolStorageException;

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.packet.process.IProtocolProcessor#doProcess(java.lang.String, java.lang.String, java.lang.String, java.lang.String, Ice.StringHolder)
	 */
	public boolean doProcess(String transCode, String fromCode, String toCode,
			String content, StringHolder response)
			throws ProtocolProcessException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		minaContext.getMetrics().mark("AbstractServerSideProtocolProcessor.doProcess");
		IProtocol protocol = ProtocolDriverManager.instance(transCode,
				fromCode, toCode, content, true);
		
		boolean ret=true;
		try {
			protocol.unmashall(true);
			handle(protocol);
			protocol.mashall(false);
			response.value=protocol.getResponsePacket();
			
		} catch (ProtocolConvertorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			protocol.mashallException(e.getState(),e.getMessage());
			response.value=protocol.getResponsePacket();
			ret=false;
		} catch (ProtocolStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			protocol.mashallException(e.state,e.reason);
			response.value=protocol.getResponsePacket();
			ret=false;
		}

		minaContext.getMetrics().mark("AbstractServerSideProtocolProcessor.doProcess");
		return ret;
		
	}

}
