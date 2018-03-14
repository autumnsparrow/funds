/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Sep 5, 2013-12:29:54 AM
 *
 */
package com.palmcommerce.funds.connection.mina.ctx;

import java.lang.management.ManagementFactory;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import org.apache.commons.io.filefilter.AbstractFileFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.service.IoProcessor;
import org.apache.mina.core.service.SimpleIoProcessorPool;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.integration.jmx.IoServiceMBean;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioProcessor;
import org.apache.mina.transport.socket.nio.NioSession;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.j256.simplejmx.server.JmxServer;
import com.palmcommerce.funds.configuration.v2.ConfigurationManager;
import com.palmcommerce.funds.configuration.v2.ForwardListener;
import com.palmcommerce.funds.configuration.v2.Listener;
import com.palmcommerce.funds.connection.mina.ctx.impl.NoConnectorSessionContextFoundException;
import com.palmcommerce.funds.connection.mina.handler.AcceptorIoHandler;
import com.palmcommerce.funds.connection.mina.handler.ConnectorIoHandler;
import com.palmcommerce.funds.connection.mina.handler.DispatchTask;
import com.palmcommerce.funds.connection.mina.protocol.AbstractClientSideProcessor;
import com.palmcommerce.funds.connection.mina.protocol.AbstractProtocolFilter;
import com.palmcommerce.funds.connection.mina.protocol.AbstractServerSideProtocolProcessor;
import com.palmcommerce.funds.connection.mina.protocol.ProtocolAcceptorHandler;
import com.palmcommerce.funds.connection.mina.protocol.ProtocolConnectorHandler;
import com.palmcommerce.funds.connection.mina.protocol.ProtocolForwardAcceptorHandler;
import com.palmcommerce.funds.connection.mina.util.M;
import com.palmcommerce.funds.packet.Packet;
import com.palmcommerce.funds.packet.process.IProtocolFilter;
import com.palmcommerce.funds.packet.process.IProtocolProcessor;
import com.palmcommerce.funds.packet.security.IPacketSecurity;
import com.palmcommerce.funds.util.IceProxyManager;
import com.palmcommerce.funds.util.Metrics;

/**
 * @author sparrow
 * 
 */
public class MinaContext {

	/**
	 * 
	 * 
	 * Spring
	 * 
	 * 
	 */
	IceProxyManager iceProxyManager;

	public IceProxyManager getIceProxyManager() {
		return iceProxyManager;
	}

	public void setIceProxyManager(IceProxyManager iceProxyManager) {
		this.iceProxyManager = iceProxyManager;
	}

	Metrics metrics;
	
	
	
	/**
	 * @return the metrics
	 */
	public Metrics getMetrics() {
		return metrics;
	}

	/**
	 * @param metrics the metrics to set
	 */
	public void setMetrics(Metrics metrics) {
		this.metrics = metrics;
	}
	
	
	boolean filter;
	

	/**
	 * @return the filter
	 */
	public boolean isFilter() {
		return filter;
	}

	/**
	 * @param filter the filter to set
	 */
	public void setFilter(boolean filter) {
		this.filter = filter;
	}

	ConfigurationManager configurationManager;

	/**
	 * @return the configurationManager
	 */
	public ConfigurationManager getConfigurationManager() {
		return configurationManager;
	}

	/**
	 * @param configurationManager
	 *            the configurationManager to set
	 */
	public void setConfigurationManager(
			ConfigurationManager configurationManager) {
		this.configurationManager = configurationManager;
	}

	IProtocolFilter protocolFilter;

	/**
	 * @return the protocolFilter
	 */
	public IProtocolFilter getProtocolFilter() {
		return protocolFilter;
	}

	/**
	 * @param protocolFilter
	 *            the protocolFilter to set
	 */
	public void setProtocolFilter(IProtocolFilter protocolFilter) {
		this.protocolFilter = protocolFilter;
		if(protocolFilter instanceof AbstractProtocolFilter){
			AbstractProtocolFilter filter=(AbstractProtocolFilter)protocolFilter;
			filter.setMinaContext(this);
		}
	}

	IProtocolProcessor serverSideProtocolProcessor;

	/**
	 * @return the serverSideProtocolProcessor
	 */
	public IProtocolProcessor getServerSideProtocolProcessor() {
		return serverSideProtocolProcessor;
	}

	/**
	 * @param serverSideProtocolProcessor
	 *            the serverSideProtocolProcessor to set
	 */
	public void setServerSideProtocolProcessor(
			IProtocolProcessor serverSideProtocolProcessor) {
		this.serverSideProtocolProcessor = serverSideProtocolProcessor;
		if(serverSideProtocolProcessor instanceof AbstractServerSideProtocolProcessor){
			AbstractServerSideProtocolProcessor processor=(AbstractServerSideProtocolProcessor)serverSideProtocolProcessor;
			processor.setMinaContext(this);
		}
	}

	/**
	 * @return the clientSideProtocolProcessor
	 */
	public IProtocolProcessor getClientSideProtocolProcessor() {
		return clientSideProtocolProcessor;
	}

	/**
	 * @param clientSideProtocolProcessor
	 *            the clientSideProtocolProcessor to set
	 */
	public void setClientSideProtocolProcessor(
			IProtocolProcessor clientSideProtocolProcessor) {
		this.clientSideProtocolProcessor = clientSideProtocolProcessor;
		if(clientSideProtocolProcessor instanceof AbstractClientSideProcessor){
			AbstractClientSideProcessor processor=(AbstractClientSideProcessor)clientSideProtocolProcessor;
			processor.setMinaContext(this);
		}
	}

	IProtocolProcessor clientSideProtocolProcessor;

	/**
	 * 
	 * 
	 * Connector or Acceptor Processor
	 * 
	 * 
	 */

	IProtocolProcessor protocolProcessor;

	public IProtocolProcessor getProtocolProcessor() {
		return protocolProcessor;
	}

	public void setProtocolProcessor(IProtocolProcessor protocolProcessor) {
		this.protocolProcessor = protocolProcessor;
		if(protocolProcessor instanceof AbstractClientSideProcessor){
			AbstractClientSideProcessor processor=(AbstractClientSideProcessor)protocolProcessor;
			processor.setMinaContext(this);
		}
		if(protocolProcessor instanceof AbstractServerSideProtocolProcessor){
			AbstractServerSideProtocolProcessor processor=(AbstractServerSideProtocolProcessor)protocolProcessor;
			processor.setMinaContext(this);
		}
	}

	IPacketSecurity packetSecurity;

	boolean enableDebug;

	// private static final MinaContext instance=new MinaContext();
	//
	// public static MinaContext getInstance(){
	// return instance;
	// }
	//

	public boolean isEnableDebug() {
		return enableDebug;
	}

	public void setEnableDebug(boolean enableDebug) {
		this.enableDebug = enableDebug;
	}

	public IPacketSecurity getPacketSecurity() {
		return packetSecurity;
	}

	public void setPacketSecurity(IPacketSecurity packetSecurity) {
		this.packetSecurity = packetSecurity;
	}

	private static final Log logger = LogFactory.getLog(MinaContext.class);

	private void log(String message) {
		if (enableDebug) {
			logger.info(message);
		}
	}

	public ConnectorSessionContext getConnectorSessionContext(String from,
			String to) {
		String key = getConnectorSessionContextKey(from, to);
		ToContext toContext = null;
		ConnectorSessionContext ctx = null;
		
		
		if (routeMap.containsKey(key)) {
			toContext = routeMap.get(key);
		} else {
			toContext = new ToContext(this, from, to);
			routeMap.put(key, toContext);
		}
		
		ctx = toContext.getSessionContext();
		
		return ctx;
	}

	public void returnConnecorSessionContext(ConnectorSessionContext ctx) {
		if(ctx!=null)
			ctx.getToContext().returnSessionContext(ctx);
	}

	public NioSocketConnector getConnector() {
		return connector;
	}
	
	

	private synchronized String getConnectorSessionContextKey(String from,
			String to) {
		return from + "_" + to;
	}

	public static final String CONNECTOR_SESSION_CTX = "ConnectionSessionContext";

	IoProcessor<NioSession> processor;
	IoHandlerAdapter acceptorHandler;
	IoHandlerAdapter connectorHandler;
	IoHandlerAdapter forwardHandler;
	NioSocketConnector connector;
	NioSocketConnector forwardConnector;
	

	/**
	 * @return the forwardConnector
	 */
	public NioSocketConnector getForwardConnector() {
		return forwardConnector;
	}

	ExecutorService executors;
	ExecutorService threadPoolExecutors;
	ScheduledExecutorService schedulers;
	final private static ConcurrentHashMap<String, ToContext> routeMap = new ConcurrentHashMap<String, ToContext>();

	/**
	 * 
	 * 
	 * should we enable the JMX
	 * 
	 */
	boolean enableJMX;

	/**
	 * @return the enableJMX
	 */
	public boolean isEnableJMX() {
		return enableJMX;
	}

	/**
	 * @param enableJMX
	 *            the enableJMX to set
	 */
	public void setEnableJMX(boolean enableJMX) {
		this.enableJMX = enableJMX;
	}

	MBeanServer mBeanServer;

	/**
	 * MinaContext fits the network diagram like this:
	 * 
	 * 
	 * initializeWithProcessor +----------+ +---------------+ +---------------+
	 * |bank | -----> | proxy server | ----->| trade | +----------+
	 * +---------------+ +---------------+
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public MinaContext() {
		// TODO Auto-generated constructor stub

		mBeanServer = ManagementFactory.getPlatformMBeanServer();
		taskWarn=200;
	}

	JmxServer jmxServer;

	/**
	 * @return the jmxServer
	 */
	public JmxServer getJmxServer() {
		return jmxServer;
	}

	/**
	 * @param jmxServer
	 *            the jmxServer to set
	 */
	public void setJmxServer(JmxServer jmxServer) {
		this.jmxServer = jmxServer;
		this.mBeanServer = this.jmxServer.getMBeanServer();
	}

	/**
	 * 
	 * start entry point for proxy mode.
	 * 
	 */

	public void startWithProxyMode() {
		this.init();
	}

	/**
	 * start entry point for acceptor or connector mode
	 * 
	 * 
	 */
	public void startWithAcceptorOrConnectorMode() {
		this.initializedWithProcessor();
	}

	public void refreshAcceptor() {
		this.initializeAcceptor();
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public IoProcessor<NioSession> getProcessor() {
		return processor;
	}

	/**
	 * 
	 * 
	 * 
	 * @return
	 */

	public IoHandlerAdapter getAcceptorHandler() {
		return acceptorHandler;
	}

	public IoHandlerAdapter getConnectorHandler() {
		return connectorHandler;
	}

	public NioSocketConnector getSocketConnector() {
		return this.connector;
	}

	public NioSocketAcceptor getAcceptor(int port) {
		NioSocketAcceptor acceptor = null;
		if (acceptorMap.contains(Integer.valueOf(port))) {
			acceptor = acceptorMap.get(Integer.valueOf(port));
		}
		return acceptor;
	}

	/**
	 * 
	 * task executor
	 * 
	 * 
	 * @param task
	 */
	int taskWarn;
	public synchronized void execute(Runnable task) {
		//queue.add(task);
		this.executors.execute(task);
	}
	
	
	private void startTaskDaemon(){
		Runnable run=new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				try {
				     for (;;) {
				    	 //queue.t
				    	 Runnable r=queue.take();
				    	// Future f=pool.submit(r);
				    	
				    	 executors.execute(r);
					   
				      }
				       
				    
				   } catch (Exception ex) {
					   ex.printStackTrace();
					   executors.shutdown();
				   }
				
			}
		};
		Thread t=new Thread(run);
		t.start();
	}
	
	
	
	private static final BlockingQueue<Runnable> queue=new LinkedBlockingQueue<Runnable>(20000);
	

	public void schedule(Runnable task) {
		this.schedulers.execute(task);
	}
	
	public void shutdownAndAwaitTermination() {
		   executors.shutdown(); // Disable new tasks from being submitted
		   try {
		     // Wait a while for existing tasks to terminate
		     if (!executors.awaitTermination(60, TimeUnit.SECONDS)) {
		    	 executors.shutdownNow(); // Cancel currently executing tasks
		       // Wait a while for tasks to respond to being cancelled
		       if (!executors.awaitTermination(60, TimeUnit.SECONDS))
		          log("Pool did not terminate");
		     }
		   } catch (InterruptedException ie) {
		     // (Re-)Cancel if current thread also interrupted
			   executors.shutdownNow();
		     // Preserve interrupt status
		     Thread.currentThread().interrupt();
		   }
		 }
		 
	

	/**
	 * 
	 * protocol handle
	 * 
	 * 
	 * @param session
	 * @param packet
	 * @throws NoConnectorSessionContextFoundException
	 */

	public void dispatch(IoSession session, Packet packet)
			throws NoConnectorSessionContextFoundException {
		DispatchTask task = new DispatchTask(packet, session);
		execute(task);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();

	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * initialize
	 * 
	 * 
	 * 
	 * 
	 */

	/**
	 * 
	 * 
	 * proxy server initialize method.
	 * 
	 * 
	 */
	private void init() {
		// initialize the mina resources and handlers.

		logger.info("-----------------------------------------------------------------------------------");
		logger.info("[proxy mode]- initialize task executor daemon.....");
		this.startTaskDaemon();
		
		logger.info("-----------------------------------------------------------------------------------");
		logger.info("[proxy mode]- initialize the mina IoProcessor,AcceptorHandler,ConnectorHandler.....");
		processor = new SimpleIoProcessorPool<NioSession>(NioProcessor.class,
				configurationManager.getServerRules().getSharedProcessNumber());
		acceptorHandler = new AcceptorIoHandler(this);
		connectorHandler = new ConnectorIoHandler(this);
		// adding the forward connector handler
		forwardHandler=new ProtocolForwardAcceptorHandler(this);
		logger.info("[proxy mode]- done!\r\n");

		// loading the route table.
		logger.info("-----------------------------------------------------------------------------------");
		logger.info("[proxy mode]- loading the route table .....");

		try {
			this.registerRouteConfigurationMBean(this.configurationManager);
		} catch (MalformedObjectNameException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstanceAlreadyExistsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MBeanRegistrationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotCompliantMBeanException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NullPointerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("[proxy mode]- done!\r\n");

		// initialize the connector.
		logger.info("-----------------------------------------------------------------------------------");
		logger.info("[proxy mode]- initialize the Connector .....");
		this.connector = new NioSocketConnector(getProcessor());
		this.connector.setHandler(getConnectorHandler());
		try {
			this.registerConnectorMBean(this.connector);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		//this.connector.getSessionConfig().setUseReadOperation(true);
		logger.info("[proxy mode]- done!\r\n");

		// initialize the acceptor resoruces.
		logger.info("-----------------------------------------------------------------------------------");
		logger.info("[proxy mode]- initialize the Acceptor resources .....");
		this.executors = Executors.newFixedThreadPool(configurationManager
				.getServerRules().getTaskExecutorNumber());
		this.schedulers = Executors.newSingleThreadScheduledExecutor();
		this.threadPoolExecutors = Executors
				.newFixedThreadPool(configurationManager.getServerRules()
						.getProxyThreadPoolExcutorNumber());
		logger.info("[proxy mode]- done!\r\n");

		// initialize the acceptor.
		logger.info("-----------------------------------------------------------------------------------");
		logger.info("[proxy mode]- initialize the Acceptor  .....");
		this.initializeAcceptor();
		logger.info("[proxy mode]- initialize the Forward Acceptor  .....");
		
		initializeForwardAcceptor();
		logger.info("[proxy mode]- done!\r\n");

	}

	/**
	 * 
	 * acceptor or connector initialize method
	 * 
	 */
	private void initializedWithProcessor() {
		
		logger.info("-----------------------------------------------------------------------------------");
		logger.info("[single mode]- initialize task executor daemon.....");
		this.startTaskDaemon();
		
		logger.info("-----------------------------------------------------------------------------------");
		logger.info("[single mode]- initialize the mina IoProcessor,AcceptorHandler,ConnectorHandler.....");

		processor = new SimpleIoProcessorPool<NioSession>(NioProcessor.class,
				configurationManager.getServerRules().getSharedProcessNumber());
		acceptorHandler = new ProtocolAcceptorHandler(this);
		connectorHandler = new ProtocolConnectorHandler(this);
		logger.info("[single mode]- done!\r\n");

		logger.info("-----------------------------------------------------------------------------------");
		logger.info("[single mode]- loading the route table .....");

		logger.info("[single mode]- done!\r\n");

		// initialize the connector.
		logger.info("-----------------------------------------------------------------------------------");
		logger.info("[single mode]- initialize the Connector .....");

		this.connector = new NioSocketConnector(getProcessor());
		this.connector.getSessionConfig().setUseReadOperation(true);
		this.connector.setHandler(getConnectorHandler());
		try {
			this.registerConnectorMBean(this.connector);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		logger.info("[single mode]- done!\r\n");

		// initialize the acceptor resoruces.
		logger.info("-----------------------------------------------------------------------------------");
		logger.info("[single mode]- initialize the Acceptor resources .....");

		this.executors = Executors.newFixedThreadPool(configurationManager
				.getServerRules().getTaskExecutorNumber());
		this.schedulers = Executors.newSingleThreadScheduledExecutor();
		this.threadPoolExecutors = Executors
				.newFixedThreadPool(configurationManager.getServerRules()
						.getProxyThreadPoolExcutorNumber());
		logger.info("[single mode]- done!\r\n");

		logger.info("-----------------------------------------------------------------------------------");
		logger.info("[single mode]- initialize the Acceptor  .....");
		this.initializeAcceptor();
		
		
		logger.info("[single mode]- done!\r\n");
	}

	/**
	 * 
	 * 
	 * 
	 */
	private static final ConcurrentHashMap<Integer, NioSocketAcceptor> acceptorMap = new ConcurrentHashMap<Integer, NioSocketAcceptor>();

	private void createAcceptorPort(Listener route) {
		NioSocketAcceptor nioSocketAcceptor;
		nioSocketAcceptor = new NioSocketAcceptor(new SimpleIoProcessorPool<NioSession>(NioProcessor.class,
				configurationManager.getServerRules().getSharedProcessNumber()));
		nioSocketAcceptor.setHandler(getAcceptorHandler());
		try {
			SocketSessionConfig scfg = nioSocketAcceptor.getSessionConfig();
			// scfg.setWriteTimeout(writeTimeout)

			scfg.setReuseAddress(true);
			Integer port = route.getPort();
			if (!acceptorMap.containsKey(port)) {
				nioSocketAcceptor.bind(new InetSocketAddress(route.getIp(),
						route.getPort()));
				this.registerAcceptorMBean(nioSocketAcceptor, route.getPort());
				acceptorMap.put(port, nioSocketAcceptor);
				logger.info("binding acceptor [ip=" + route.getIp() + ",port="
						+ route.getPort() + " ]");
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.info("acceptor binding failed" + e.getMessage());
		}
	}
	
	
	private static final ConcurrentHashMap<Integer, NioSocketAcceptor> forwardAcceptorMap = new ConcurrentHashMap<Integer, NioSocketAcceptor>();

	private void createForwardAcceptorPort(ForwardListener route) {
		NioSocketAcceptor nioSocketAcceptor;
		nioSocketAcceptor = new NioSocketAcceptor(new SimpleIoProcessorPool<NioSession>(NioProcessor.class,
				configurationManager.getServerRules().getSharedProcessNumber()));
		nioSocketAcceptor.setHandler(forwardHandler);
		try {
			SocketSessionConfig scfg = nioSocketAcceptor.getSessionConfig();
			// scfg.setWriteTimeout(writeTimeout)

			scfg.setReuseAddress(true);
			Integer port = route.getPort();
			if (!forwardAcceptorMap.containsKey(port)) {
				nioSocketAcceptor.bind(new InetSocketAddress(route.getIp(),
						route.getPort()));
				this.registerAcceptorMBean(nioSocketAcceptor, route.getPort());
				forwardAcceptorMap.put(port, nioSocketAcceptor);
				logger.info("binding acceptor [ip=" + route.getIp() + ",port="
						+ route.getPort() + " ]");
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.info("acceptor binding failed" + e.getMessage());
		}
	}

	private void disableAcceptorPort(Listener route) {
		Integer port = route.getPort();
		if (acceptorMap.containsKey(port)) {
			NioSocketAcceptor nioSocketAcceptor = acceptorMap.get(port);
			try {
				this.removeAcceptorMBean(port.intValue());
			} catch (MBeanRegistrationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstanceNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			nioSocketAcceptor.dispose(true);
		}
	}

	private void initializeAcceptor() {
		// RouteTable routeTable=getRouteTable();
		// HashMap<String,Route> routes=routeTable.getRoutes();
		Collection<Listener> listeners = configurationManager.getServerRules()
				.getListeners();
		for (Listener listener : listeners) {
			createAcceptorPort(listener);

		}

	}
	
	private void initializeForwardAcceptor() {
		// RouteTable routeTable=getRouteTable();
		// HashMap<String,Route> routes=routeTable.getRoutes();
		this.forwardConnector = new NioSocketConnector(getProcessor());
		this.forwardConnector.getSessionConfig().setUseReadOperation(true);
		Collection<ForwardListener> listeners = configurationManager.getServerRules().getForwardListeners();
		for (ForwardListener listener : listeners) {
			createForwardAcceptorPort(listener);

		}

	}

	/**
	 * 
	 * 
	 * JMX
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	final private static ConcurrentHashMap<Integer, ObjectName> mbeanMaps = new ConcurrentHashMap<Integer, ObjectName>();

	private void registerAcceptorMBean(NioSocketAcceptor acceptor,
			int listenerPort) throws MalformedObjectNameException,
			NullPointerException, InstanceAlreadyExistsException,
			MBeanRegistrationException, NotCompliantMBeanException {
		if (enableJMX) {
			IoServiceMBean serviceMBean = new IoServiceMBean(acceptor);
			// create a JMX ObjectName. This has to be in a specific format.
			ObjectName acceptorName = new ObjectName(acceptor.getClass()
					.getPackage().getName()
					+ ":type=acceptor,name="
					+ acceptor.getClass().getSimpleName() + "@" + listenerPort);

			// register the bean on the MBeanServer. Without this line, no JMX
			// will happen for
			// this acceptor.
			mbeanMaps.put(Integer.valueOf(listenerPort), acceptorName);
			mBeanServer.registerMBean(serviceMBean, acceptorName);
			logger.info("[JMX-acceptor] - bind." + acceptorName.toString());
		}
	}

	private void removeAcceptorMBean(int listenerPort)
			throws MBeanRegistrationException, InstanceNotFoundException {
		// mBeanServer.unregisterMBean(name)
		if (enableJMX) {
			if (mbeanMaps.contains(Integer.valueOf(listenerPort))) {
				ObjectName objName = mbeanMaps.get(Integer
						.valueOf(listenerPort));
				mBeanServer.unregisterMBean(objName);
				logger.info("[JMX-acceptor] - unbind." + objName.toString());
			}
		}
	}

	ObjectName connectorObjectName;

	private void registerConnectorMBean(NioSocketConnector connector)
			throws MalformedObjectNameException, NullPointerException,
			InstanceAlreadyExistsException, MBeanRegistrationException,
			NotCompliantMBeanException {
		if (enableJMX) {
			IoServiceMBean serviceMBean = new IoServiceMBean(connector);
			// create a JMX ObjectName. This has to be in a specific format.
			connectorObjectName = new ObjectName(connector.getClass()
					.getPackage().getName()
					+ ":type=connector,name="
					+ connector.getClass().getSimpleName());

			// register the bean on the MBeanServer. Without this line, no JMX
			// will happen for
			// this acceptor.

			mBeanServer.registerMBean(serviceMBean, connectorObjectName);
			logger.info("[JMX-connector] - bind."
					+ connectorObjectName.toString());
		}
	}

	private void removeConnectorMBean() throws MBeanRegistrationException,
			InstanceNotFoundException {
		// mBeanServer.unregisterMBean(name)
		if (enableJMX) {
			mBeanServer.unregisterMBean(connectorObjectName);
		}
	}

	ObjectName routeConfigurationObjectName;

	private void registerRouteConfigurationMBean(
			ConfigurationManager routeConfiguration)
			throws MalformedObjectNameException, NullPointerException,
			InstanceAlreadyExistsException, MBeanRegistrationException,
			NotCompliantMBeanException, IntrospectionException,
			SecurityException {
		// if(enableJMX)
		// {
		//
		// // create a JMX ObjectName. This has to be in a specific format.
		// routeConfigurationObjectName = new ObjectName(
		// routeConfiguration.getClass().getPackage().getName() +
		// ":type=routeConfiguration,name=" +
		// routeConfiguration.getClass().getSimpleName());
		//
		// // register the bean on the MBeanServer. Without this line, no JMX
		// will happen for
		// // this acceptor.
		// JMXBeanWrapper wrapper=new JMXBeanWrapper(routeConfiguration);
		// mBeanServer.registerMBean( wrapper, routeConfigurationObjectName );
		// logger.info("[JMX-connector] - bind."
		// +routeConfigurationObjectName.toString());
		// }
	}

	private void removeRouteMBean() throws MBeanRegistrationException,
			InstanceNotFoundException {
		// mBeanServer.unregisterMBean(name)
		// if(enableJMX){
		// mBeanServer.unregisterMBean(routeConfigurationObjectName);
		// }
	}

}
