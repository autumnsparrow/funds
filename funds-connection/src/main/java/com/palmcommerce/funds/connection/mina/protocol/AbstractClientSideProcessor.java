/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Sep 9, 2013-8:14:50 PM
 *
 */
package com.palmcommerce.funds.connection.mina.protocol;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

import Ice.StringHolder;

import com.palmcommerce.funds.connection.mina.ctx.ConnectorSessionContext;
import com.palmcommerce.funds.connection.mina.ctx.MinaContext;
import com.palmcommerce.funds.packet.Packet;
import com.palmcommerce.funds.packet.PacketFactory;
import com.palmcommerce.funds.packet.process.IProtocolProcessor;
import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;
import com.palmcommerce.funds.protocol.impl.IProtocol;
import com.palmcommerce.funds.service.ProtocolProcessException;



/**
 * @author sparrow
 *
 */

public abstract class AbstractClientSideProcessor implements IProtocolProcessor {
	@Autowired
	MinaContext minaContext;
	
	private static final Log logger=LogFactory.getLog(AbstractClientSideProcessor.class);

	public MinaContext getMinaContext() {
		return minaContext;
	}

	public void setMinaContext(MinaContext minaContext) {
		this.minaContext = minaContext;
	}

	/**
	 * 
	 */
	public AbstractClientSideProcessor() {
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.connection.mina.processor.IProtocolProcessor#process(java.lang.String, java.lang.String, java.lang.String, java.lang.String, Ice.StringHolder)
	 */
	public boolean doProcess(String transcode, String fromcode, String tocode,
			String content, StringHolder response)
			throws ProtocolProcessException {
		minaContext.getMetrics().mark("AbstractClientSideProcessor.doProcess");
		// TODO Auto-generated method stub
		//ConnectorSessionContext connectorSession=minaContext.getConnectorSessionContext(fromcode, tocode);
		ConnectorSessionContext connectorSession=new ConnectorSessionContext(minaContext);
		connectorSession.onBindConnectorSession(tocode);
		try {
			Packet packet=PacketFactory.getFactory().getPacket();
			packet.transCode=transcode;
			packet.fromCode=fromcode;
			packet.toCode=tocode;
			packet.content=content;
			Packet resp=connectorSession.sendPacket(packet);
			response.value=resp.content;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			connectorSession.unbindSession(null);
			//minaContext.returnConnecorSessionContext(connectorSession);
		}
		
		minaContext.getMetrics().mark("AbstractClientSideProcessor.doProcess");
		return true;
	}
	
	
	private static class ClientTask implements Runnable{
		
		IProtocol protocol;
		
		AbstractClientSideProcessor processor;

		public ClientTask(IProtocol protocol,AbstractClientSideProcessor processor) {
			super();
			this.protocol = protocol;
			this.processor=processor;
		}
		
		
		public void run(){
			try {
				//protocol.getHeader();
				protocol.mashall(true);
				StringHolder response=new StringHolder();
				boolean ret=processor.doProcess(String.valueOf(protocol.getHeader().transcode), protocol.getHeader().from, protocol.getHeader().to, protocol.getRequestPacket(), response);
				if(ret){
					protocol.setResponsePacket(response.value);
					protocol.unmashall(false);
					processor.handle(protocol);
				}
			} catch (ProtocolConvertorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.info("@@@@@@@@"+e.getState()+" :" +e.getMessage());
				//throw new ProtocolProcessException(e.getState(), e.getMessage());
			}catch (ProtocolProcessException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				logger.info("@@@@@@"+e.state+" :" +e.reason);
			} 
		}
		
		
		 
	}
	
	
	public void send(IProtocol  protocol){
		minaContext.getMetrics().mark("AbstractClientSideProcessor.send");
		try {
			//protocol.getHeader();
			protocol.mashall(true);
			StringHolder response=new StringHolder();
			boolean ret=this.doProcess(String.valueOf(protocol.getHeader().transcode), protocol.getHeader().from, protocol.getHeader().to, protocol.getRequestPacket(), response);
			if(ret){
				protocol.setResponsePacket(response.value);
				protocol.unmashall(false);
				handle(protocol);
			}
		} catch (ProtocolConvertorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("@@@@@@@@"+e.getState()+" :" +e.getMessage());
			//throw new ProtocolProcessException(e.getState(), e.getMessage());
		}catch (ProtocolProcessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.info("@@@@@@"+e.state+" :" +e.reason);
		} 
		minaContext.getMetrics().mark("AbstractClientSideProcessor.send");
		//this.minaContext.execute(new ClientTask(protocol, this));
	}
	
	
	public void sendWithoutMashall(IProtocol  protocol){
		minaContext.getMetrics().mark("AbstractClientSideProcessor.send");
		try {
			//protocol.getHeader();
			protocol.mashall(true);
			StringHolder response=new StringHolder();
			boolean ret=this.doProcess(String.valueOf(protocol.getHeader().transcode), protocol.getHeader().from, protocol.getHeader().to, protocol.getRequestPacket(), response);
			if(ret){
				protocol.setResponsePacket(response.value);
				//protocol.unmashall(false);
				handle(protocol);
			}
		} catch (ProtocolConvertorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("@@@@@@@@"+e.getState()+" :" +e.getMessage());
			//throw new ProtocolProcessException(e.getState(), e.getMessage());
		}catch (ProtocolProcessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.info("@@@@@@"+e.state+" :" +e.reason);
		} 
		minaContext.getMetrics().mark("AbstractClientSideProcessor.send");
		//this.minaContext.execute(new ClientTask(protocol, this));
	}
	public abstract void handle(IProtocol protocol) throws ProtocolProcessException;

	

}
