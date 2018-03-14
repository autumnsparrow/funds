/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Sep 8, 2013-5:53:38 PM
 *
 */
package com.palmcommerce.funds.connection.mina.protocol;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.palmcommerce.funds.connection.mina.ctx.MinaContext;

import com.palmcommerce.funds.packet.Packet;
import com.palmcommerce.funds.packet.PacketFactory;
import com.palmcommerce.funds.protocol.ProtocolDriverManager;

/**
 * @author sparrow
 *
 */
public class ProtocolForwardAcceptorHandler extends IoHandlerAdapter {
	
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
	public ProtocolForwardAcceptorHandler(MinaContext minaContext) {
		// TODO Auto-generated constructor stub
		super();
		this.minaContext=minaContext;
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
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		// TODO Auto-generated method stub
		super.messageReceived(session, message);
		Packet packet=PacketFactory.getFactory().getPacket();
		IoBuffer buffer=(IoBuffer)message;
		packet.readPacket(session, buffer);
		
		if(ProtocolDriverManager.isTransferModeDirect(packet.transCode))
		{
			this.minaContext.execute(new ProtocolAcceptorLocalProcessorTask(session, packet, minaContext));
		
		}else{
			// if the request is not direct. so forward the request.
			
			this.minaContext.execute(new ProtocolConnectorLocalProcessor(session, packet, minaContext));

		}
		
		
		
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		super.messageSent(session, message);
		if(session.isConnected()){
			session.close(false);
		}
	}

}
