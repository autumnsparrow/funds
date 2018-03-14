package com.palmcommerce.funds.roo.tasklet.schedule;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.palmcommerce.funds.configuration.v2.ConfigurationManager;
import com.palmcommerce.funds.configuration.v2.RouteRuleEntry;
import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.impl.t2p.T250010;
import com.palmcommerce.funds.roo.protocol.impl.ScheduledTradeClientProtocolHandler;

public class OperationT250010 {
	
	@Autowired
	private ConfigurationManager configurationManager;
	@Autowired
	ScheduledTradeClientProtocolHandler scheduledTradeClientProtocolHandler;
	//
	private List<String> bankCodes;
	private List<String> tradeCodes;
	
	
	public void sendRequest(){
		//完成加载路由表
		loadRuteTable();
		//按照银行端的数目发送请求协议
		for(String bankCode:bankCodes){
			T250010 protocol = (T250010)ProtocolDriverManager.instance("250010", configurationManager
					.getServerRules().getProxy()
					.getNodeCode(), bankCode);
			scheduledTradeClientProtocolHandler.send(protocol);
		}
	}
	
	
	
	
	/**
	 * 加载路由表
	 * 
	 * @return
	 */
	private boolean loadRuteTable(){
		this.bankCodes = new LinkedList<String>();
		this.tradeCodes = new LinkedList<String>();
		//
		try{
			configurationManager.updateRouteRules();
			if(configurationManager.getRouteRuleResult()!=null){
				for(RouteRuleEntry entry : configurationManager
						.getRouteRuleResult().getRouteRuleEntries()){
					if(entry.isBank()){
						bankCodes.add(entry.getToCode());
					}else{
						if(entry!=null&&entry.getToCode()!=null&&!entry.getToCode().startsWith("P"))
							this.tradeCodes.add(entry.getToCode());
					}
					
				}
			}
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	
	

}
