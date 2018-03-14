/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Sep 30, 2013
 *
 */
package com.palmcommerce.funds.connection.config;

import java.beans.ConstructorProperties;
import java.util.HashMap;

/**
 * @author sparrow
 *
 */
public  class RouteTable{
	
	HashMap<String,Route> routes;
	
	
	

	public RouteTable() {
		super();
		// TODO Auto-generated constructor stub
		routes=new HashMap<String, Route>();
	}
	
	public void addRoute(Route route){
		this.routes.put(route.fromWhere, route);
	}
	
	public Route getRoute(String fromWhere){
		return this.routes.get(fromWhere);
	}
	
	public HashMap<String, Route> getRoutes(){
		return routes;
	}

	@Override
	public String toString() {
		
		StringBuffer buffer=new StringBuffer();
		
		buffer.append( "RouteTable [routes=");
		
		for(Route route:routes.values()){
			buffer.append(route.toString()).append(",\n");
		}
		buffer.append("]");
		
		return buffer.toString();
	}
	
	
	

}

