/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 26, 2013
 *
 */
package com.palmcommerce.funds.connection.mina.protocol;

import com.palmcommerce.funds.connection.mina.ctx.MinaContext;
import com.palmcommerce.funds.packet.Packet;
import com.palmcommerce.funds.packet.process.IProtocolFilter;
import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;
import com.palmcommerce.funds.protocol.impl.IProtocol;
import com.palmcommerce.funds.service.ProtocolProcessException;
import com.palmcommerce.funds.service.ProtocolStorageException;
import com.palmcommerce.funds.connection.mina.ctx.MinaContext;

/**
 * @author sparrow
 *
 */
public abstract class AbstractProtocolFilter implements IProtocolFilter {
	
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
	public AbstractProtocolFilter() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.connection.mina.processor.IProtocolFilter#requestFilte(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean requestFilte(Packet packet) throws ProtocolProcessException {
		// TODO Auto-generated method stub
		minaContext.getMetrics().mark("AbstractProtocolFilter.requestFilte");
		IProtocol protocol=ProtocolDriverManager.instance(packet.transCode, packet.fromCode,packet. toCode, packet.content, true);
		boolean ret=true;
		try {
			protocol.unmashall(true);
			handleRequest(protocol);
			protocol.mashall(true);
			packet.transactionSerial=protocol.getGlobalSerial();
			packet.content=protocol.getRequestPacket();
			packet.toCode=protocol.getHeader().to;
		} catch (ProtocolConvertorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//protocol.mashallException(e.getState(), e.getMessage());
			//packet.content=protocol.getResponsePacket();
			ret=false;
		} catch (ProtocolStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//protocol.mashallException(e.state, e.reason);
			//packet.content=protocol.getResponsePacket();
			ret=false;
		}
		minaContext.getMetrics().mark("AbstractProtocolFilter.requestFilte");
		
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.connection.mina.processor.IProtocolFilter#responsetFilte(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean responsetFilte(Packet packet) throws ProtocolProcessException {
		// TODO Auto-generated method stub
		minaContext.getMetrics().mark("AbstractProtocolFilter.responsetFilte");
		IProtocol protocol=ProtocolDriverManager.instance(packet.transCode, packet.fromCode, packet.toCode, packet.content, false);
		boolean ret=true;
		try {
			protocol.unmashall(false);
			handleResponse(protocol);
			protocol.mashall(false);
			packet.transactionSerial=protocol.getGlobalSerial();
			//packet.transactionSerial=protocol.
			packet.content=protocol.getResponsePacket();
		} catch (ProtocolConvertorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//protocol.mashallException(e.getState(), e.getMessage());
			//packet.content=protocol.getResponsePacket();
			ret=false;
		} catch (ProtocolStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//protocol.mashallException(e.state, e.reason);
			//packet.content=protocol.getResponsePacket();
			ret=false;
		}
		minaContext.getMetrics().mark("AbstractProtocolFilter.responsetFilte");
		
		return ret;
	}
	
	public abstract boolean handleRequest(IProtocol protocol)throws ProtocolStorageException;
	
	public abstract boolean handleResponse(IProtocol protocol)throws ProtocolStorageException;

}
