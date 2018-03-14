/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Sep 9, 2013-9:03:16 AM
 *
 */
package com.palmcommerce.funds.connection.mina.protocol;

import Ice.Current;
import Ice.StringHolder;

import com.palmcommerce.funds.connection.mina.ctx.ConnectorSessionContext;
import com.palmcommerce.funds.connection.mina.ctx.MinaContext;
import com.palmcommerce.funds.packet.Packet;
import com.palmcommerce.funds.packet.PacketFactory;
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
public class ProtocolConnectorIceServerLocalProcessor extends _ProtocolProcessorDisp {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3755726273730099290L;
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
	public ProtocolConnectorIceServerLocalProcessor() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.trade.service._TradeProcessorOperations#process(java.lang.String, java.lang.String, java.lang.String, java.lang.String, Ice.StringHolder, Ice.Current)
	 */
	
	public boolean doProcess(String transcode, String fromcode, String tocode,
			String request, StringHolder response, Current __current)
			throws ProtocolProcessException {
		// TODO Auto-generated method stub
		minaContext.getMetrics().mark("ProtocolConnectorIceServerLocalProcessor.doProcess");
		ConnectorSessionContext connectorSession=minaContext.getConnectorSessionContext(fromcode, tocode);
		
		try {
			Packet packet=PacketFactory.getFactory().getPacket();
			packet.transCode=transcode;
			packet.fromCode=fromcode;
			packet.toCode=tocode;
			packet.content=request;
			Packet resp=connectorSession.sendPacket(packet);
			response.value=resp.content;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			minaContext.returnConnecorSessionContext(connectorSession);
		}
		minaContext.getMetrics().mark("ProtocolConnectorIceServerLocalProcessor.doProcess");
		
		return true;
	}

}
