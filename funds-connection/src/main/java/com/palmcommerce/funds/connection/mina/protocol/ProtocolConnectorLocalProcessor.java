/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Sep 9, 2013-9:03:16 AM
 *
 */
package com.palmcommerce.funds.connection.mina.protocol;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;

import Ice.Current;
import Ice.StringHolder;

import com.palmcommerce.funds.configuration.v2.RouteRuleEntry;
import com.palmcommerce.funds.connection.mina.ctx.ConnectorSessionContext;
import com.palmcommerce.funds.connection.mina.ctx.MinaContext;
import com.palmcommerce.funds.packet.Packet;
import com.palmcommerce.funds.packet.PacketFactory;
import com.palmcommerce.funds.packet.process.IProtocolProcessor;
import com.palmcommerce.funds.packet.security.IPacketSecurity;
import com.palmcommerce.funds.packet.security.exception.DecryptPacketException;
import com.palmcommerce.funds.packet.security.exception.EncryptPacketException;
import com.palmcommerce.funds.packet.security.exception.SignaturePacketException;
import com.palmcommerce.funds.packet.security.exception.ValidatePacketException;
import com.palmcommerce.funds.service.ProtocolProcessException;
import com.palmcommerce.funds.service._ProtocolProcessorDisp;



/**
 * @author sparrow
 *
 */
public class ProtocolConnectorLocalProcessor implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3755726273730099290L;
	IoSession session;
	Packet packet;
	public ProtocolConnectorLocalProcessor(IoSession session, Packet packet,
			MinaContext minaContext) {
		super();
		this.session = session;
		this.packet = packet;
		this.minaContext = minaContext;
	}

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
	public ProtocolConnectorLocalProcessor() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.trade.service._TradeProcessorOperations#process(java.lang.String, java.lang.String, java.lang.String, java.lang.String, Ice.StringHolder, Ice.Current)
	 */
	
//	public boolean doProcess(String transcode, String fromcode, String tocode,
//			String request, StringHolder response, Current __current)
//			throws ProtocolProcessException {
//		// TODO Auto-generated method stub
//		minaContext.getMetrics().mark("ProtocolConnectorIceServerLocalProcessor.doProcess");
//		ConnectorSessionContext connectorSession=minaContext.getConnectorSessionContext(fromcode, tocode);
//		
//		try {
//		//	Packet packet=PacketFactory.getFactory().getPacket();
//			packet.transCode=transcode;
//			packet.fromCode=fromcode;
//			packet.toCode=tocode;
//			packet.content=request;
//			Packet resp=connectorSession.getToContext().sendPacket(packet);
//			response.value=resp.content;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			minaContext.returnConnecorSessionContext(connectorSession);
//		}
//		minaContext.getMetrics().mark("ProtocolConnectorIceServerLocalProcessor.doProcess");
//		
//		return true;
//	}

//	public boolean doProcess(String transCode, String fromCode, String toCode,
//			String content, StringHolder response)
//			throws ProtocolProcessException {
//		// TODO Auto-generated method stub
//		minaContext.getMetrics().mark("ProtocolConnectorIceServerLocalProcessor.doProcess");
//		ConnectorSessionContext connectorSession=minaContext.getConnectorSessionContext(fromCode, toCode);
//		
//		try {
//			//Packet packet=PacketFactory.getFactory().getPacket();
//			packet.transCode=transCode;
//			packet.fromCode=fromCode;
//			packet.toCode=toCode;
//			packet.content=content;
//			Packet resp=connectorSession.getToContext().sendPacket(packet);
//			response.value=resp.content;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			minaContext.returnConnecorSessionContext(connectorSession);
//		}
//		minaContext.getMetrics().mark("ProtocolConnectorIceServerLocalProcessor.doProcess");
//		
//		return true;
//		
//	}

	private void log(String message){
		
	}
	
	private IoSession getForwardConnectorConnection(String to){
		IoSession connectorSession=null;
		RouteRuleEntry rule=minaContext.getConfigurationManager().getRouteRuleResult().getRouteRule(to);
		if(rule==null)
			return connectorSession;
		log("createConnection:"+rule.toString());
		ConnectFuture future= this.minaContext.getForwardConnector().connect(new InetSocketAddress(rule.getIp(), rule.getPort()));
		
			
			future.awaitUninterruptibly();	
			connectorSession = future.getSession();
			log("connected connection:"+connectorSession.getId());
		return connectorSession;
		
	}
	public void run() {
		// TODO Auto-generated method stub
		
		minaContext.getMetrics().mark("ProtocolConnectorLocalProcessor.executeTask");
		IoSession connectorSession=getForwardConnectorConnection(packet.toCode);
		ConnectorSessionContext ctx=new ConnectorSessionContext(connectorSession, session);//minaContext.getConnectorSessionContext(packet.fromCode,packet.toCode);
		
		try {
			
			
			Packet resp=ctx.sendPlainPacket(packet);
//			packet.content=resp.content;
//			resp.writePacket(session);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally{
			//minaContext.returnConnecorSessionContext(connectorSession);
			//session.close(false);
			//ctx.unbindSession(session);
			// when the sending the packet finished should return the 
			ctx.closeConnectorConnection();
			
			
		}
		minaContext.getMetrics().mark("ProtocolConnectorLocalProcessor.executeTask");
		
		//return true;
		
		
	}

}
