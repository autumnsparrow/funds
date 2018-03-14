/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Sep 5, 2013-11:39:04 AM
 *
 */
package com.palmcommerce.funds.connection.mina.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.session.IoSession;

import com.palmcommerce.funds.connection.mina.ctx.ConnectorSessionContext;
import com.palmcommerce.funds.connection.mina.ctx.MinaContext;
import com.palmcommerce.funds.connection.mina.ctx.impl.NoConnectorSessionContextFoundException;
import com.palmcommerce.funds.connection.mina.storage.ILocalStorage;
import com.palmcommerce.funds.connection.mina.storage.StorageException;
import com.palmcommerce.funds.packet.Packet;
import com.palmcommerce.funds.packet.process.IProtocolFilter;
import com.palmcommerce.funds.packet.security.IPacketSecurity;
import com.palmcommerce.funds.packet.security.exception.DecryptPacketException;
import com.palmcommerce.funds.packet.security.exception.EncryptPacketException;
import com.palmcommerce.funds.packet.security.exception.SignaturePacketException;
import com.palmcommerce.funds.packet.security.exception.ValidatePacketException;
import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.impl.IProtocol;
import com.palmcommerce.funds.service.ProtocolProcessException;

/**
 * @author sparrow
 * 
 */
public class DispatchTask implements Runnable, ILocalStorage {

	private static Log logger = LogFactory.getLog(DispatchTask.class);
	Packet packet;
	IoSession session;

	ConnectorSessionContext connectorSession;
	boolean isAcceptorSession;
	IPacketSecurity packetSecurity; 

	// MinaContext minaContext;

	public DispatchTask(Packet packet, IoSession session)
			throws NoConnectorSessionContextFoundException {
		super();
		this.packet = packet;

		// this.packet.transactionSerial=connectorSession.getTransactionSerial();
		this.session = session;

	}

	/*
	 * 
	 * Must carefully to tread difference conditions? write down the state
	 * change diagram is required.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		// TODO Auto-generated method stub
		// logger.info("server sessiion :"+isAcceptorSession);
		//logger.info("---->DispatchTask");
		try {
			connectorSession = ConnectorSessionContext
					.getConnectorSessionContext(this.session);
		} catch (NoConnectorSessionContextFoundException e2) {
			// TODO Auto-generated catch block
			// e2.printStackTrace();
			// can't find the exception.
			logger.info(" No Such Connection:"+this.session.toString());
		}
		

		if (session == connectorSession.getAcceptorSession()) {
			isAcceptorSession = true;
		} else {
			isAcceptorSession = false;
		}

		packetSecurity= this.connectorSession.getMinaContext()
				.getPacketSecurity();

		boolean ret = false;

		try {
			ret = packetSecurity.unMashall(packet);
			ret = isAcceptorSession ? request(packet) : response(packet);
			
			
			if(ret) {
				// need reverse the from

				// write the packet into the ice server.
				// connect the server
				IoSession writeSession = isAcceptorSession ? connectorSession
						.getConnectorSession() : connectorSession
						.getAcceptorSession();

				if ((isAcceptorSession)) {
					// connectorSession.createConnection(packet.toCode);
					writeSession = connectorSession
							.onBindConnectorSession(packet.toCode);
				}

				ret = packetSecurity.mashall(packet);

				if (ret) {

					if (connectorSession.getMinaContext().isEnableDebug()) {
						if (writeSession != null)
							logger.info("<-----------|[proxy]"
									+ writeSession.toString());
					}
					packet.writePacket(writeSession);
				}

			}else{
				// packetSecurity.mashall(packet);
				if (connectorSession.getMinaContext().isEnableDebug()) {
					if (connectorSession.getAcceptorSession() != null)
						logger.info("|--------->[proxy] -- " + session.toString()
								);
				}
				if(isAcceptorSession){
						String tocode=packet.toCode;
						packet.toCode=packet.fromCode;
						packet.fromCode=tocode;
				}
				ret = packetSecurity.mashall(packet);
				packet.writePacket(connectorSession.getAcceptorSession());
				
			}
		} catch (DecryptPacketException e) {
			// TODO Auto-generated catch block
			mashallException("9999",e);
		} catch (ValidatePacketException e) {
			// TODO Auto-generated catch block
			mashallException("9999",e);
		} catch (EncryptPacketException e) {
			// TODO Auto-generated catch block
			mashallException("9999",e);
		} catch (SignaturePacketException e) {
			// TODO Auto-generated catch block
			mashallException("9999",e);
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			mashallException(e.state,e);
		}catch (Exception e){
			mashallException("9999",e);
		}

	}
	
	
	private void log(String message,MinaContext ctx){
		if(ctx.isEnableDebug()){
			logger.info(message);
		}
	}

	private void mashallException(String state,Exception e) {
		
		IProtocol protocol = ProtocolDriverManager.instance(packet.transCode,
				packet.fromCode, packet.toCode);

		// e.printStackTrace();
		protocol.mashallException(state, e.getMessage());
		packet.content=protocol.getResponsePacket();
		try {
			packetSecurity.mashall(packet);
		} catch (EncryptPacketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SignaturePacketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (connectorSession.getMinaContext().isEnableDebug()) {
			if (connectorSession.getAcceptorSession() != null)
				logger.info("|--------->[proxy] -- " + session.toString()
						+ ", mashallException" + e.getMessage());
		}
		packet.writePacket(connectorSession.getAcceptorSession());
	}

	/*
	 * invoke the ice service (non-Javadoc)
	 * 
	 * @see
	 * com.palmcommerce.funds.connection.mina.handler.IRemoteStorage#request
	 * (com.palmcommerce.funds.packet.Packet)
	 */
	public boolean request(Packet packet) throws StorageException {
		// TODO Auto-generated method stub
		boolean ret = true;
		if (connectorSession.getMinaContext().isFilter()) {
			// parse the packet and change the packet content.
			IProtocolFilter filter = this.connectorSession.getMinaContext()
					.getProtocolFilter();

			try {
				ret = filter.requestFilte(packet);
			} catch (ProtocolProcessException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				ret = false;
				throw new StorageException(e.state, e.reason);
				
			}catch(Exception e){
				ret=false;
				throw new StorageException("9999", e.getMessage());
			}
		}
		session.setAttribute(ConnectorSessionContext.PACKET, packet);
		return ret;
	}

	/*
	 * invoke the ice service (non-Javadoc)
	 * 
	 * @see
	 * com.palmcommerce.funds.connection.mina.handler.IRemoteStorage#response
	 * (com.palmcommerce.funds.packet.Packet)
	 */
	public boolean response(Packet packet) throws StorageException {
		// TODO Auto-generated method stub

		// parse the packet and change the packet content.
		boolean ret = true;
		if (connectorSession.getMinaContext().isFilter()) {
			IProtocolFilter filter = this.connectorSession.getMinaContext()
					.getProtocolFilter();
			try {
				ret = filter.responsetFilte(packet);
			} catch (ProtocolProcessException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				ret = false;
				throw new StorageException(e.state, e.reason);
			}catch(Exception e){
				ret=false;
				throw new StorageException("9999", e.getMessage());
			}
		}

		session.setAttribute(ConnectorSessionContext.PACKET, packet);
		return ret;
	}
}
