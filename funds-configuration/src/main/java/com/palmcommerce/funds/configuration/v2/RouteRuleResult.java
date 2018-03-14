/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 15, 2013
 *
 */
package com.palmcommerce.funds.configuration.v2;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.commons.digester3.annotations.rules.ObjectCreate;
import org.apache.commons.digester3.annotations.rules.SetNext;

/**
 * @author sparrow
 * 
 * 
 <RouteRuleResult>
		<RouteRuleEntry>
			
			<ToCode/>
			<Ip/>
			<Port/>
			<Bank/>
		<RouteRuleEntry>
	</RouteRuleEntry>
 *
 *
 *
 *
 */

@ObjectCreate(pattern="RouteRuleResult")
public class RouteRuleResult {

	private HashMap<String,RouteRuleEntry> mapOfRouteRuleEntries=new HashMap<String,RouteRuleEntry>();
	/**
	 * 
	 */
	public RouteRuleResult() {
		// TODO Auto-generated constructor stub
	}
	
	@SetNext
	public void addRouteRuleEntry(RouteRuleEntry entry){
		this.mapOfRouteRuleEntries.put(entry.getToCode(), entry);
		
	}
	
	public RouteRuleEntry getRouteRule(String toCode){
		if(this.mapOfRouteRuleEntries.containsKey("*")){
			return this.mapOfRouteRuleEntries.get("*");
		}else{
			return this.mapOfRouteRuleEntries.get(toCode);
		}
	}
	
	public void updateForwardFlag(){
		if(this.mapOfRouteRuleEntries.containsKey("*"))
			return;
		RouteRuleEntry forwardEntry=null;
		for(Entry<String,RouteRuleEntry> entry:mapOfRouteRuleEntries.entrySet()){
			if(entry.getValue().isForwardFlag()){
				forwardEntry=entry.getValue();
				break;
			}
		}
		mapOfRouteRuleEntries.put("*", forwardEntry);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		
		return "RouteRuleResult []";
	}
	
	
	public Collection<RouteRuleEntry> getRouteRuleEntries(){
		return this.mapOfRouteRuleEntries.values();
	}

	
	
}
