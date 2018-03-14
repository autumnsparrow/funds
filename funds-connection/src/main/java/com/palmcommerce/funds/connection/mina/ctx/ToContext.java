/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Sep 5, 2013-12:47:45 AM
 *
 */
package com.palmcommerce.funds.connection.mina.ctx;

import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;


import com.palmcommerce.funds.configuration.v2.Listener;
import com.palmcommerce.funds.configuration.v2.RouteRule;
import com.palmcommerce.funds.configuration.v2.RouteRuleEntry;
import com.palmcommerce.funds.connection.mina.util.M;
import com.palmcommerce.funds.packet.Packet;
import com.palmcommerce.funds.packet.PacketFactory;
import com.palmcommerce.funds.packet.security.exception.DecryptPacketException;
import com.palmcommerce.funds.packet.security.exception.EncryptPacketException;
import com.palmcommerce.funds.packet.security.exception.SignaturePacketException;
import com.palmcommerce.funds.packet.security.exception.ValidatePacketException;

/**
 * @author sparrow
 *
 */
public class ToContext  {
	
	private static final Log logger =LogFactory.getLog(ToContext.class);
	
	MinaContext minaContext;
	
	
	
	
	String to;
	
	Listener route;
	String from;
	Packet ping;
	
	public MinaContext getMinaContext() {
		return minaContext;
	}

	private final    ConcurrentLinkedQueue<ConnectorSessionContext> sessionCtxs=new ConcurrentLinkedQueue<ConnectorSessionContext>();

	private final    ConcurrentLinkedQueue<ConnectorSessionContext> usedSessionContexts=new ConcurrentLinkedQueue<ConnectorSessionContext>();
	/**
	 * 
	 */
	public ToContext(MinaContext ctx,String from,String to) {
		// TODO Auto-generated constructor stub
		super();
		this.minaContext=ctx;
		this.from=from;
		this.to=to;
		
		
		// schedule
		this.ping=new Packet();
		this.ping.fromCode=this.from;
		this.ping.toCode=this.to;
		this.ping.signatureLength=0;
		//this.ping.transCode=toWhere.getPing().getClazz();
		// schedule the connector
	//	init();
		//this.schedule();
	}
	
	
	public void init(){
		//this.schedule();
		createConnection(false);
	}
	
	private void log(String message){
		if(getMinaContext().isEnableDebug())
			logger.info(message);
	}
	
	
	public  ConnectorSessionContext getSessionContext(){
		
	
		if(sessionCtxs.size()==0)
		{
			for(int i=0;i<1;i++)
				createConnection(true);
			
			
		}
		
		
		
		ConnectorSessionContext ctx=sessionCtxs.poll();
		if(ctx!=null){
			usedSessionContexts.add(ctx);
			log("-------GetConnectorSesion:"+ctx.toString()+" pooled :[ used-"+usedSessionContexts.size()+" ,freed-"+sessionCtxs.size());
		}
		return ctx;
	}
	
	public  void returnSessionContext(ConnectorSessionContext ctx){
		usedSessionContexts.remove(ctx);
		ctx.closeAcceptorConnection();
		ctx.closeConnectorConnection();
				//this.sessionCtxs.add(ctx);
		log("------ReturnConnectorSession:"+ctx.toString()+" pooled :[ used-"+usedSessionContexts.size()+" ,freed-"+sessionCtxs.size());
		
		
	}
	
	Runnable pingPacket=new Runnable() {
		
		public void run() {
			// TODO Auto-generated method stub
			// track the connections and schedule task.
			//String clazz=toWhere.getPing().getClazz();
			
			log("schedle task manager the connections and  ping task... "+ToContext.this.toString());
			// 1.schedule the ping task
//			if(!sessionCtxs.isEmpty()){
//				
//				
//				for(Iterator<ConnectorSessionContext> sessionCtx=sessionCtxs.iterator();sessionCtx.hasNext();){
//					
//					ConnectorSessionContext ctx=sessionCtx.next();
//					try {
//						ping.writePacket(ctx.getConnectorSession());
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//						returnSessionContext(ctx);
//						
//					}
//					//.write(message);
//					logger.info("ping ..."+ping.toString());
//				}
//			}
//			
			
			int totalSession=sessionCtxs.size()+usedSessionContexts.size();
			
			//2. checking the connections.
//			if(totalSession<toWhere.getConnection().getMin()){
//				// needing create some connections.
//				int len=toWhere.getConnection().getMin()-totalSession;
//				for(int i=0;i<len;i++){
//					createConnection(false);
//				}
//				
//			}
//			
//			// 3. close the sessions that overflow
//			 if(totalSession>toWhere.getConnection().getMax()){
//				int overflow=sessionCtxs.size()-toWhere.getConnection().getMax();
//				if(sessionCtxs.size()<overflow){
//					overflow=sessionCtxs.size();
//				}
//				
//				// close the connections that exceed.
//				for(Iterator<ConnectorSessionContext> sessionCtx=sessionCtxs.iterator();sessionCtx.hasNext();overflow--){
//					final ConnectorSessionContext ctx=	sessionCtx.next();
//					
//					if(overflow<0)
//						break;
//					closeConnection(ctx);
//					
//				}
//			}
//			 
		}
	};
	
	private  void closeConnection(final ConnectorSessionContext ctx){
		CloseFuture future=ctx.getConnectorSession().close(true);
		future.addListener(new IoFutureListener<IoFuture>() {

			public void operationComplete(IoFuture future) {
				// TODO Auto-generated method stub
				log("closed connection:"+future.getSession().getId());
				sessionCtxs.remove(ctx);
			}
		});
	}
	
	
	
	
	
	public synchronized void createConnection(boolean blocking){
		
		// ConnectFuture connFuture = connector.connect(new InetSocketAddress(ip,port));

		RouteRuleEntry rule=minaContext.getConfigurationManager().getRouteRuleResult().getRouteRule(this.to);
		if(rule==null){
			//sessionCtxs.add(new ConnectorSessionContext(this));
			return;
		}
		log("createConnection:"+rule.toString());
		ConnectFuture future= this.minaContext.getConnector().connect(new InetSocketAddress(rule.getIp(), rule.getPort()));
		if(blocking){
			IoSession session=null;
			future.awaitUninterruptibly();	
			session = future.getSession();
			log("connected connection:"+session.getId());
			
			sessionCtxs.add(new ConnectorSessionContext(this,from,to,session));
		}else{
				future.addListener(new IoFutureListener<IoFuture>() {

			public void operationComplete(IoFuture future) {
				// TODO Auto-generated method stub
				log("connected connection:"+future.getSession().getId());
				sessionCtxs.add(new ConnectorSessionContext(ToContext.this,from,to,future.getSession()));
				
			}
		});
		
		}
	      // return session;
	}
	
	
	public void schedule(){
		
		
		//Class<?>  
		//this.minaContext.schedulers.scheduleAtFixedRate(pingPacket, 0,  toWhere.getPing().getInterval(),TimeUnit.SECONDS);
	}
	
	
	


	@Override
	public String toString() {
		return "ToContext [ping=" + ping + ", sessionCtxs=" + sessionCtxs.size()
				+ ", usedSessionContexts=" + usedSessionContexts.size() + "]";
	}
    
	

}
