/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author sparrow
 *
 * @date Sep 4, 2013-10:59:00 PM
 *
 */
package com.palmcommerce.funds.connection.config;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashMap;

import org.apache.commons.digester3.Digester;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import org.xml.sax.SAXException;




/**
 * @author sparrow
 * 
 * Route Table
 * 
 * 
 * Route table should be re-implements as the data base
 * 
 * 
 * Change 
 * 
 *  <Listeners>
 *  	<!--litener for the bank to trade-->
 *  	<Listener port="80" ip="192.168.1.86" maxConnections="100" minConnections="10" pingInterval="5" class="com.palmcommerce.protocol.impl.p2t.P240006"/>
 *	      
 *       <!-- listner for the trade to bank -->
		 <Listener port="90" ip="192.168.1.86" maxConnections="50" minConnection="10" pingInterval="5" class="com.palmcommerce.protocol.impl.p2t.P240006"/>
 *  
 *  </Listeners>
 *  
 *  
 *   // what about the route functions.
 *   
 *   
 *   <from="" to=""/>
 *   request{
 *   	.from
 *   	.to
 *   } 
 *   
 *   response{
 *   	.ip
 *      .port
 *   }
 *   
 *   
 *   
 *   
 * 
 * 
 *  <RouteTable>
 *  	<Route fromWhere="B0001" port="80" ip="192.168.1.86">
 *  		<To where="T0001">
 *  			<Address ip="" port=""/>
 *  			<Connection max="100" min="10"/>
 *  			<Ping interval="5" clazz="com.palmcommerce.protocol.impl.p2t.P240006"/>
 *  		</To> 
 * 			 <To where="T0002">
 *  			<Connection max="100" min="10"/>
 *  			<Ping interval="5" clazz="com.palmcommerce.protocol.impl.p2t.P240006"/>
 *  		</To> 
 *  	</Route>
 *  
 * 	 <Route fromWhere="B0002">
 *  		<To where="T0001">
 *  			<Connection max="100" min="10"/>
 *  			<Ping interval="5" clazz="com.palmcommerce.protocol.impl.p2t.P240006"/>
 *  		</To> 
 * 			 <To where="T0002">
 *  			<Connection max="100" min="10"/>
 *  			<Ping interval="5" clazz="com.palmcommerce.protocol.impl.p2t.P240006"/>
 *  		</To> 
 *  	</Route>
 *  
 * 	 <Route fromWhere="T0001">
 *  		<To where="B0001">
 *  			<Connection max="100" min="10"/>
 *  			<Ping interval="5" clazz="com.palmcommerce.protocol.impl.p2t.P240006"/>
 *  		</To> 
 * 			 <To where="B0002">
 *  			<Connection max="100" min="10"/>
 *  			<Ping interval="5" clazz="com.palmcommerce.protocol.impl.p2t.P240006"/>
 *  		</To> 
 *  	</Route>
 *  
 *  
 *  
 *  	
 *  	
 *  
 *  </RouteTable>
 *  
 *  
 *  									  +---------+
 *  									> | toWhere1|
 *  +-----------+					  /   +---------+
 *  | fromWhere |      ----->  System - >  
 *  +-----------+                    \    +---------+
 *  									> | toWhere2|
 *										  +---------+	
 */

public class RouteConfiguration {
	
	
	
	FundsConfiguration  minaConfiguration;
	
	
	

	public FundsConfiguration getMinaConfiguration() {
		return minaConfiguration;
	}

	public void setMinaConfiguration(FundsConfiguration minaConfiguration) {
		this.minaConfiguration = minaConfiguration;
	}

	private static Log logger=LogFactory.getFactory().getLog(RouteConfiguration.class);

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	RouteTable routeTable;
	
	public RouteTable getRouteTable(){
		return this.routeTable;
	}
	
	public Route getRoute(String xml)  {
		Route route=null;
		Digester digester=new Digester();
		
		
		digester.addObjectCreate("Route", Route.class);
		digester.addObjectCreate("Route/To", ToWhere.class);
		digester.addObjectCreate("Route/To/Connection", Connection.class);
		digester.addObjectCreate("Route/To/Ping", Ping.class);
		digester.addObjectCreate("Route/To/Address", Address.class);
		
		
		digester.addSetProperties("Route", "fromWhere", "fromWhere");
		digester.addSetProperties("Route/To", "where", "where");
		
		digester.addSetProperties("Route/To/Address", "ip", "ip");
		digester.addSetProperties("Route/To/Address", "port", "port");
		digester.addSetProperties("Route/To/Connection", "min", "min");
		digester.addSetProperties("Route/To/Connection", "max", "max");
		
		digester.addSetProperties("Route/To/Ping", "clazz", "clazz");
		digester.addSetProperties("Route/To/Ping", "interval", "interval");
		
		digester.addSetNext("Route/To/Address","setAddress");
		digester.addSetNext("Route/To/Connection","setConnection");
		digester.addSetNext("Route/To/Ping","setPing");
		digester.addSetNext("Route/To","addToWhere");
		
		//digester.addSetNext("RouteTable/Route","addRoute");
		
		
		//InputStream in=new InputStream(bis);
		InputStream bis=null;
		try {
			 bis=new ByteArrayInputStream(xml.getBytes("utf8"));
			route=(Route)digester.parse(bis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(bis!=null)
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	 
		
		
		return route;
	}

	
	public RouteTable parse() throws IOException, SAXException{
		RouteTable routeTable=null;
		Digester digester=new Digester();
		
		
		digester.addObjectCreate("RouteTable", RouteTable.class);
		digester.addObjectCreate("RouteTable/Route", Route.class);
		digester.addObjectCreate("RouteTable/Route/To", ToWhere.class);
		digester.addObjectCreate("RouteTable/Route/To/Connection", Connection.class);
		digester.addObjectCreate("RouteTable/Route/To/Ping", Ping.class);
		digester.addObjectCreate("RouteTable/Route/To/Address", Address.class);
		
		
		digester.addSetProperties("RouteTable/Route", "fromWhere", "fromWhere");
		digester.addSetProperties("RouteTable/Route/To", "where", "where");
		
		digester.addSetProperties("RouteTable/Route/To/Address", "ip", "ip");
		digester.addSetProperties("RouteTable/Route/To/Address", "port", "port");
		digester.addSetProperties("RouteTable/Route/To/Connection", "min", "min");
		digester.addSetProperties("RouteTable/Route/To/Connection", "max", "max");
		
		digester.addSetProperties("RouteTable/Route/To/Ping", "clazz", "clazz");
		digester.addSetProperties("RouteTable/Route/To/Ping", "interval", "interval");
		
		digester.addSetNext("RouteTable/Route/To/Address","setAddress");
		digester.addSetNext("RouteTable/Route/To/Connection","setConnection");
		digester.addSetNext("RouteTable/Route/To/Ping","setPing");
		digester.addSetNext("RouteTable/Route/To","addToWhere");
		
		digester.addSetNext("RouteTable/Route","addRoute");
		
		if(minaConfiguration.getRouteTableConfig()!=null){
			boolean enableHttpsResources=minaConfiguration.getRouteTableConfig().startsWith("http://");
			if(!enableHttpsResources){
				String config=RouteConfiguration.class.getResource(minaConfiguration.getRouteTableConfig()).getFile();
				File f=new File(config);
				logger.info("config file="+f.getAbsolutePath());
				routeTable=(RouteTable)digester.parse(f);
				logger.info(routeTable.toString());
			}else{
				String url=minaConfiguration.getRouteTableConfig();
				URL httpUrl=new URL(url);
				logger.info("config file="+httpUrl.toString());
				routeTable=(RouteTable)digester.parse(httpUrl);
				logger.info(routeTable.toString());
			}
		}
		return routeTable;
	}

	
	/**
	 * 
	 */
	public RouteConfiguration() {
		// TODO Auto-generated constructor stub
		super();
		

	}
	
	public void init(){
		try {
		this.routeTable=parse();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		logger.info("route table parse failed:"+e.getMessage());
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		logger.info("route table parse failed:"+e.getMessage());
	}
		
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.connection.config.IRouteConfigurationMXBean#getToWhere(java.lang.String, java.lang.String)
	 */
	
	public ToWhere getToWhere(
			
			String fromWhere,
			
			String toWhere) {
		// TODO Auto-generated method stub
		Route r=routeTable.getRoute(fromWhere);
		ToWhere to=r.getToWhere(toWhere);
		return to;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.connection.config.IRouteConfigurationMXBean#setToWhere(java.lang.String, java.lang.String, com.palmcommerce.funds.connection.config.ToWhere)
	 */
	
	public void setToWhere(
			String fromWhere,
			String toWhere,
			int connectionMax, 
			int connectionMin,
			int pingInterval, 
			String pingClazz,
			String addressIp,
			int addressPort) {
		// TODO Auto-generated method stub
		
		Route r=routeTable.getRoute(fromWhere);
		ToWhere t=r.getToWhere(toWhere);
		//t.setClone(to);
		t.setAddressIp(addressIp);
		t.setAddressPort(addressPort);
		t.setPingClazz(pingClazz);
		t.setPingInterval(pingInterval);
		t.setConnectionMax(connectionMax);
		t.setConnectionMin(connectionMin);
		
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.connection.config.IRouteConfigurationMXBean#setToWhere(java.lang.String, java.lang.String, int, int)
	 */
	public void setToWhereConnection(
			String fromWhere,
			String toWhere,
			int connectionMax, 
			int connectionMin
		
			) {
		// TODO Auto-generated method stub
		Route r=routeTable.getRoute(fromWhere);
		ToWhere t=r.getToWhere(toWhere);
		//t.setClone(to);
		
		t.setConnectionMax(connectionMax);
		t.setConnectionMin(connectionMin);
		
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.connection.config.IRouteConfigurationMXBean#setToWhere(java.lang.String, java.lang.String, int, java.lang.String)
	 */
	
	public void setToWherePing(
			String fromWhere,
			String toWhere,
				int pingInterval, 
				String pingClazz) {
		// TODO Auto-generated method stub
		Route r=routeTable.getRoute(fromWhere);
		ToWhere t=r.getToWhere(toWhere);
		//t.setClone(to);
	
		t.setPingClazz(pingClazz);
		t.setPingInterval(pingInterval);
		
		
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.connection.config.IRouteConfigurationMXBean#setToWhere(java.lang.String, java.lang.String, java.lang.String, int)
	 */
	public void setToWhereAddress(
			String fromWhere,
			String toWhere,
			String addressIp,
			int addressPort) {
		// TODO Auto-generated method stub
		Route r=routeTable.getRoute(fromWhere);
		ToWhere t=r.getToWhere(toWhere);
		//t.setClone(to);
		t.setAddressIp(addressIp);
		t.setAddressPort(addressPort);
		
		
	}

	
	
	

}
