/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Sep 5, 2013-12:33:24 AM
 *
 */
package com.palmcommerce.funds.connection.mina.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.palmcommerce.funds.connection.mina.ctx.ConnectorSessionContext;
import com.palmcommerce.funds.connection.mina.ctx.MinaContext;
import com.palmcommerce.funds.connection.mina.ctx.impl.NoConnectorSessionContextFoundException;
import com.palmcommerce.funds.packet.Packet;
import com.palmcommerce.funds.packet.PacketFactory;

/**
 * @author sparrow
 *
 */
public class ConnectorIoHandler extends IoHandlerAdapter {
	private static Log logger=LogFactory.getLog(ConnectorIoHandler.class);

	MinaContext minaContext;
	

	public ConnectorIoHandler(MinaContext minaContext) {
		super();
		this.minaContext = minaContext;
	}

	public MinaContext getMinaContext() {
		return minaContext;
	}

	public void setMinaContext(MinaContext minaContext) {
		this.minaContext = minaContext;
	}

	/**
	 * 
	 */
	public ConnectorIoHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionCreated(session);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionOpened(session);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionClosed(session);
		
		//unbind(session);
		
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		// TODO Auto-generated method stub
		super.sessionIdle(session, status);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(session, cause);
		unbind(session);
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		minaContext.getMetrics().mark("ConnectorIoHandler.messageReceived");
		// TODO Auto-generated method stub
		super.messageReceived(session, message);
		//MinaContext ctx=MinaContext.getInstance();
		//using the primary parse the header.
		Packet packet=PacketFactory.getFactory().getPacket();
		IoBuffer buffer=(IoBuffer)message;
		packet.readPacket(session, buffer);
		
		logger.info("connector:---"+packet.toString());
	
		//ConnectorSessionContext connectorSession=ConnectorSessionContext.getConnectorSessionContext(session);//minaContext.getConnectorSessionContext(packet.toCode, packet.fromCode);
		//connectorSession.bindSession(session);
		// after the binding 
//		connectorSession.acceptorResume();
//		connectorSession.connectorSuspend();
		//connectorSession.acceptorResume();
		// should invoke the RPC by as ice client.
		// may using the Async-style invoke.
		// all the below thing must be execute in the executor never waste the IO Processor time.
		minaContext.dispatch(session, packet);
		
		
		minaContext.getMetrics().mark("ConnectorIoHandler.messageReceived");
		
		//session.close(true);
	}
	
	private void unbind(IoSession session) throws NoConnectorSessionContextFoundException{
		ConnectorSessionContext connectorSessionCtx=ConnectorSessionContext.getConnectorSessionContext(session);
		if(connectorSessionCtx!=null){
			
			connectorSessionCtx.unbindSession(connectorSessionCtx.getAcceptorSession());
		}
	}
	
	


	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		super.messageSent(session, message);
		//session.setAttribute("ts", Long.valueOf(System.currentTimeMillis()));
	}
	
	

}
