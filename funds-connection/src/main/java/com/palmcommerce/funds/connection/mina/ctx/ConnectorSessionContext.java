/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Sep 5, 2013-1:05:41 AM
 *
 */
package com.palmcommerce.funds.connection.mina.ctx;

import java.net.InetSocketAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.palmcommerce.funds.configuration.v2.RouteRuleEntry;
import com.palmcommerce.funds.connection.mina.ctx.impl.NoConnectorSessionContextFoundException;
import com.palmcommerce.funds.connection.mina.handler.RemoteStorageTask;
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
public class ConnectorSessionContext {

	private static final Log logger = LogFactory
			.getLog(ConnectorSessionContext.class);
	private IoSession session;
	private IoSession acceptorSession;
	private String transactionSerial;

	private MinaContext minaContext;

	private ToContext toContext;

	public ToContext getToContext() {
		return toContext;
	}

	public void setToContext(ToContext toContext) {
		this.toContext = toContext;
	}

	public static final String PACKET = "Session_Packet";

	public String getTransactionSerial() {
		return transactionSerial;
	}

	private void log(String message) {
		// if(getMinaContext())
		if (minaContext.isEnableDebug())
			logger.info(message);
	}

	/**
	 * 
	 * 
	 * The process of the connector session Context.
	 * 
	 * acceptor(IoSession acceptorSession) --- onMessageReceived
	 * 
	 * onBindAcceptorSession(minContext,acceptorSession)
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param minaContext
	 * @param session
	 */

	public static ConnectorSessionContext onBindAcceptorSession(
			MinaContext minaContext, IoSession acceptorSession) {
		return new ConnectorSessionContext(minaContext, acceptorSession);
	}

	public MinaContext getMinaContext() {
		return minaContext;
	}

	public IoSession onBindConnectorSession(String toCode) {

		this.createConnection(toCode);
		log("------onBindConnectorSession--->:" + toCode + " ["
				+ this.session.toString() + "]");

		return this.session;
	}
	
	

	public ConnectorSessionContext(MinaContext minaContext) {
		super();
		this.minaContext = minaContext;
	}

	public ConnectorSessionContext(MinaContext minaContext, IoSession session) {
		super();
		// this.toContext=ctx;
		this.minaContext = minaContext;
		this.bindSession(session);
	}

	public ConnectorSessionContext(IoSession session, IoSession acceptorSession) {
		super();
		this.session = session;
		this.acceptorSession = acceptorSession;
	}

	/**
	 * 
	 */
	public ConnectorSessionContext(ToContext ctx, String from, String to,
			IoSession session) {
		// TODO Auto-generated constructor stub
		// super(ctx,from,to);
		this.toContext = ctx;
		this.session = session;
		this.session.setAttribute(MinaContext.CONNECTOR_SESSION_CTX, this);

	}

	public void bindSession(IoSession acceptorSession) {
		// this.transactionSerial=generator.nextStringIdentifier();
		this.acceptorSession = acceptorSession;
		acceptorSession.setAttribute(MinaContext.CONNECTOR_SESSION_CTX, this);
		log("------onBindAcceptorSession--->:" + acceptorSession.toString());

		// acceptorSuspend();
		// connectorResume();

	}

	public void unbindSession(IoSession acceptorSession) {
		if (acceptorSession == null)
			return;
		log("unbindSession: acceptor:"
				+ acceptorSession.toString());
	
		if(this.session!=null)
			log("unbindSession: connector" + this.session.toString());
		// acceptorResume();
		// connectorResume();
		// get the request packet and response packet.
		// Packet request=(Packet) acceptorSession.getAttribute(PACKET);
		// Packet response=(Packet) session.getAttribute(PACKET);
		this.persist();
		if (this.acceptorSession != null) {
			this.acceptorSession
					.removeAttribute(MinaContext.CONNECTOR_SESSION_CTX);
			this.acceptorSession.removeAttribute(PACKET);
			// this.acceptorSession.close(false);
		}
		if (this.session != null) {
			this.session.removeAttribute(MinaContext.CONNECTOR_SESSION_CTX);
			this.session.removeAttribute(PACKET);
			// this.session.close(false);
		}

		// and return the Connector SessionContext into the queue
		this.transactionSerial = null;
		
		closeAcceptorConnection();
		closeConnectorConnection();

	//	minaContext.returnConnecorSessionContext(this);
		// toContext.returnSessionContext(this);
	}

	public void acceptorSuspend() {
		if (this.acceptorSession != null) {
			this.acceptorSession.suspendRead();
			this.acceptorSession.suspendWrite();
		}
	}

	public void acceptorResume() {
		if (this.acceptorSession != null) {
			this.acceptorSession.resumeRead();
			this.acceptorSession.resumeWrite();
		}
	}

	public void connectorSuspend() {
		if (this.session != null) {
			this.session.suspendRead();
			this.session.suspendWrite();
		}
	}

	public void connectorResume() {
		if (this.session != null) {
			this.session.resumeRead();
			this.session.resumeWrite();
		}
	}

	public IoSession getAcceptorSession() {
		return this.acceptorSession;
	}

	public IoSession getConnectorSession() {
		return this.session;
	}

	public void closeConnectorConnection() {
		if (this.session != null && this.session.isConnected()) {
			log("close: connector" + this.acceptorSession.toString());
			this.session.close(true);
			this.session = null;
		}
	}

	public void closeAcceptorConnection() {
		if (this.acceptorSession != null && this.acceptorSession.isConnected()) {
			log("close: acceptorSession" + this.acceptorSession.toString());
			this.acceptorSession.close(true);
			this.acceptorSession = null;
		}
	}

	public static ConnectorSessionContext getConnectorSessionContext(
			IoSession session) throws NoConnectorSessionContextFoundException {
		Object obj = session.getAttribute(MinaContext.CONNECTOR_SESSION_CTX);
		ConnectorSessionContext ctx = null;

		if (obj != null) {
			ctx = (ConnectorSessionContext) obj;
		} else {

			// logger.info("NoConnectionSessionContext");
			throw new NoConnectorSessionContextFoundException();
		}

		return ctx;
	}

	public void persist() {
		// ConnectorSessionContext csCtx=null;
		// try {
		// csCtx = ConnectorSessionContext.getConnectorSessionContext(session);
		RemoteStorageTask task = new RemoteStorageTask(this);
		getMinaContext().execute(task);

	}

	@Override
	public String toString() {
		return "ConnectorSessionContext [session="
				+ (session != null ? session.toString() : "")
				+ ", acceptorSession="
				+ (acceptorSession != null ? acceptorSession.toString() : "")
				+ ", transactionSerial=" + transactionSerial + ", toContext="
				+ toContext.toString() + "]";
	}

	/**
	 * The main method : instanciates a client, and send N messages. We sleep
	 * between each K messages sent, to avoid the server saturation. Just for
	 * the ClientSideProcessHanalder using ,not for proxy or forward.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public Packet sendPacket(Packet packet) {

		// encrypt
		Packet response = null;

		try {
			// checking should the packet using the security.

			if (getMinaContext().getPacketSecurity()
					.mashall(packet)) {
				// session.write(packet);
				WriteFuture future = packet.writePacket(session);
				future.awaitUninterruptibly();
				ReadFuture f = session.read();
				f.awaitUninterruptibly();
				response = PacketFactory.getFactory().getPacket();
				IoBuffer buffer = (IoBuffer) f.getMessage();
				response.readPacket(session, buffer);

			}

			if (response != null) {
				getMinaContext().getPacketSecurity()
						.unMashall(response);
			}
		} catch (EncryptPacketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignaturePacketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DecryptPacketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidatePacketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//returnSessionContext(this);
			
		}

		return response;

	}

	/**
	 * The main method : instanciates a client, and send N messages. We sleep
	 * between each K messages sent, to avoid the server saturation.
	 * 
	 * Just using for proxy.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public Packet sendPlainPacket(Packet packet) {

		// encrypt
		Packet response = null;

		try {
			// checking should the packet using the security.

			// session.write(packet);
			if (session.isConnected()) {
				WriteFuture future = packet.writePacket(session);
				future.awaitUninterruptibly();
				ReadFuture f = session.read();
				f.awaitUninterruptibly();
				// response=PacketFactory.getFactory().getPacket();
				IoBuffer buffer = (IoBuffer) f.getMessage();
				if (acceptorSession.isConnected())
					acceptorSession.write(buffer);
			}
			// response.readPacket(session, buffer);

			// don't process anything just push the message back
			// packet.writePacket(acceptorSession)
			// response.writePacket(acceptorSession);

		} finally {
			// this.toContext.returnSessionContext(this);
		}

		return response;

	}

	public synchronized void createConnection(String to) {

		// ConnectFuture connFuture = connector.connect(new
		// InetSocketAddress(ip,port));

		RouteRuleEntry rule = this.minaContext.getConfigurationManager()
				.getRouteRuleResult().getRouteRule(to);
		if (rule == null) {

			return;
		}
		
		ConnectFuture future = this.minaContext.getConnector().connect(
				new InetSocketAddress(rule.getIp(), rule.getPort()));

		//IoSession session = null;
		future.awaitUninterruptibly();
		this.session = future.getSession();

		// this.session=session;
		this.session.setAttribute(MinaContext.CONNECTOR_SESSION_CTX, this);
		log("createConnection:" + rule.toString());
		log("connected connection:" + this.session.toString());

		// sessionCtxs.add(new ConnectorSessionContext(this,from,to,session));

		// return session;
	}
	
	
	

}
