/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 15, 2013
 *
 */
package com.palmcommerce.funds.configuration.v2;

import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;

/**
 * @author sparrow
 * 
 * 
	<RouteRule>
		<!--  will retune the xml form result -->
		<Query>http://192.168.1.152/rpc/getRouteRule</Query>
		<FromCode>from code</FromCode>
		
	</RouteRule>
	
	<RouteRuleResult>
		<NodeCode>
			<FromCode/>
			<ToCode/>
			<Ip/>
			<Port/>
			<UserIdLength/>
			<UserIdFlag/>
		<NodeCode>
	</RouteRuleResult>
 *
 */
@ObjectCreate(pattern="ServerRules/RouteRule")
public class RouteRule {

	/**
	 * 
	 */
	public RouteRule() {
		// TODO Auto-generated constructor stub
	}
	@BeanPropertySetter(pattern="ServerRules/RouteRule/Query")
	String query;
	@BeanPropertySetter(pattern="ServerRules/RouteRule/FromCode")
	String fromCode;
	
	/**
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}
	/**
	 * @param query the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}
	/**
	 * @return the toCode
	 */
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RouteRule [query=" + query + ", fromCode=" + fromCode + "]";
	}
	/**
	 * @return the fromCode
	 */
	public String getFromCode() {
		return fromCode;
	}
	/**
	 * @param fromCode the fromCode to set
	 */
	public void setFromCode(String fromCode) {
		this.fromCode = fromCode;
	}
	
	
	

}
