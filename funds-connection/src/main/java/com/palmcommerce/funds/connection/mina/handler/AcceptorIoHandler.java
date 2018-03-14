/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Sep 5, 2013-12:32:43 AM
 *
 */
package com.palmcommerce.funds.connection.mina.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.palmcommerce.funds.configuration.v2.RouteRule;
import com.palmcommerce.funds.configuration.v2.RouteRuleEntry;
import com.palmcommerce.funds.connection.mina.ctx.ConnectorSessionContext;
import com.palmcommerce.funds.connection.mina.ctx.MinaContext;
import com.palmcommerce.funds.connection.mina.ctx.impl.NoConnectorSessionContextFoundException;
import com.palmcommerce.funds.connection.mina.protocol.ProtocolAcceptorIceServerRemoteProcessorTask;
import com.palmcommerce.funds.connection.mina.protocol.ProtocolAcceptorLocalProcessorTask;
import com.palmcommerce.funds.packet.Packet;
import com.palmcommerce.funds.packet.PacketFactory;
import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.util.Metrics;


/**
 * @author sparrow
 *
 */
public class AcceptorIoHandler extends IoHandlerAdapter {
	private static Log logger=LogFactory.getLog(AcceptorIoHandler.class);
	
	MinaContext minaContext;

	

	public MinaContext getMinaContext() {
		return minaContext;
	}

	public void setMinaContext(MinaContext minaContext) {
		this.minaContext = minaContext;
	}

	/**
	 * 
	 */
	public AcceptorIoHandler() {
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
		unbind(session);
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
		cause.printStackTrace();
		
		unbind(session);
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		// TODO Auto-generated method stub
		//super.messageReceived(session, message);
		//session.setAttribute("ts",Long.valueOf(System.currentTimeMillis()));
		//using the primary parse the header.
		minaContext.getMetrics().mark("AcceptorIoHandler.messageReceived");
		Packet packet=PacketFactory.getFactory().getPacket();
		IoBuffer buffer=(IoBuffer)message;
		packet.readPacket(session, buffer);
		logger.info("messageReceived ----"+packet.toString());
		
		//
		// according the packet transcode to switch the packet
		// deliver to the remote server or processed by local server.
		//
		// should validate the from to
		RouteRuleEntry fromRule=this.minaContext.getConfigurationManager().getRouteRuleResult().getRouteRule(packet.fromCode);
		//RouteRuleEntry toRule=this.minaContext.getConfigurationManager().getRouteRuleResult().getRouteRule(packet.toCode);
		if(fromRule==null){
			// should write the error
			//session.write(message);
			logger.info("Can't get RouteRule fromCode:"+packet.fromCode);
			
			
			session.close(false);
		}else{
		if(ProtocolDriverManager.isTransferModeDirect(packet.transCode))
		{
		
			
			// after the binding
//			ConnectorSessionContext connectorSession=minaContext.getConnectorSessionContext(packet.fromCode, packet.toCode);
//			if(connectorSession!=null)
//				connectorSession.bindSession(session);
			
			//ConnectorSessionContext connetorSession=new ConnectorSessionContext(minaContext, session);
			// should invoke the RPC by as ice client.
			// may using the Async-style invoke.
			ConnectorSessionContext.onBindAcceptorSession(minaContext, session);
			minaContext.dispatch(session, packet);
			
		}
		else
		
		{
			
			this.minaContext.execute(new ProtocolAcceptorIceServerRemoteProcessorTask(session, packet, minaContext));

		}
		
		}
//		
		
		minaContext.getMetrics().mark("AcceptorIoHandler.messageReceived");
		
		//connectorSession.getConnectorSession().write(message);
				
	}
	
	public AcceptorIoHandler(MinaContext minaContext) {
		super();
		this.minaContext = minaContext;
	}

	@SuppressWarnings("unused")
	private void unbind(IoSession session) {
		ConnectorSessionContext connectorSessionCtx=null;
		try {
			connectorSessionCtx=ConnectorSessionContext.getConnectorSessionContext(session);
			if(connectorSessionCtx!=null){
				connectorSessionCtx.unbindSession(session);
			}
		} catch (NoConnectorSessionContextFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			if(connectorSessionCtx!=null)
				logger.error("unbind ---->  No connection exist!:"+connectorSessionCtx.toString());
			
		}
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		super.messageSent(session, message);
		// unbind the session
		

		
		unbind(session);
		
		//session.close(false);
		
		
	}
	
	

}
