/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Sep 9, 2013-7:29:18 PM
 *
 */
package com.palmcommerce.funds.connection.mina.protocol;

import org.apache.mina.core.session.IoSession;

import Ice.StringHolder;


import com.palmcommerce.funds.connection.mina.ctx.MinaContext;
import com.palmcommerce.funds.packet.Packet;
import com.palmcommerce.funds.packet.process.IProtocolProcessor;
import com.palmcommerce.funds.packet.security.IPacketSecurity;
import com.palmcommerce.funds.packet.security.exception.DecryptPacketException;
import com.palmcommerce.funds.packet.security.exception.EncryptPacketException;
import com.palmcommerce.funds.packet.security.exception.SignaturePacketException;
import com.palmcommerce.funds.packet.security.exception.ValidatePacketException;
import com.palmcommerce.funds.service.ProtocolProcessException;


/**
 * @author sparrow
 *
 */
public class ProtocolAcceptorLocalProcessorTask implements Runnable {
	IoSession session;
	Packet packet;
	MinaContext minaContext;
	
	
	public ProtocolAcceptorLocalProcessorTask(IoSession session, Packet packet,
			MinaContext minaContext) {
		super();
		this.session = session;
		this.packet = packet;
		this.minaContext = minaContext;
	}

	/**
	 * 
	 */
	public ProtocolAcceptorLocalProcessorTask() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	
	public void run() {
		// TODO Auto-generated method stub
		minaContext.getMetrics().mark("ProtocolAcceptorLocalProcessorTask.run");
		try {
			//TradeProcessorPrx processor=this.minaContext.getIceProxyManager().getTradeProcessorPrx();
			IProtocolProcessor processor=this.minaContext.getServerSideProtocolProcessor();
			IPacketSecurity packetSecurity=this.minaContext.getPacketSecurity();
			
			//packet.toCode=fromCode;
			if (packetSecurity.unMashall(packet)) {
				StringHolder response=new StringHolder();
				
				processor.doProcess(packet.transCode, packet.fromCode, packet.toCode, packet.content, response);
				//
				String fromCode=packet.fromCode;//packet.fromCode;
				packet.fromCode=packet.toCode;
				packet.toCode=fromCode;
				packet.content=response.value;
				if (packetSecurity.mashall(packet)) {
					
					packet.writePacket(session);
				}
			}
		} catch (DecryptPacketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncryptPacketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidatePacketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SignaturePacketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		minaContext.getMetrics().mark("ProtocolAcceptorLocalProcessorTask.run");

	}

}
