/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 15, 2013
 *
 */
package com.palmcommerce.funds.configuration.v2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.Security;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.annotations.FromAnnotationsRuleModule;
import org.apache.commons.digester3.binder.DigesterLoader;
import org.apache.commons.digester3.binder.RuleProvider;
import org.xml.sax.SAXException;

/**
 * @author sparrow
 *
 */
public class RulesParser {
	
	

	final  DigesterLoader  loader=DigesterLoader.newLoader(new FromAnnotationsRuleModule() {
		
		@Override
		protected void configureRules() {
			// TODO Auto-generated method stub
			this.bindRulesFrom(ServerRules.class);
			this.bindRulesFrom(RouteRuleResult.class);
			
			
		}
	});
	
	Digester digest;
	/**
	 * 
	 */
	public RulesParser() {
		// TODO Auto-generated constructor stub
		digest=loader.newDigester();
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	public ServerRules getServerRules(File file){
	  ServerRules listeners=null;
	  try {
		Object obj=	digest.parse(file);
		if(obj!=null)
			listeners=(ServerRules)obj;
	  } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  	}
	  return listeners;
	}
	
	/**
	 * 
	 * 
	 * @param url
	 * @return
	 * @throws SAXException 
	 * @throws IOException 
	 */
	public RouteRuleResult getRouteRuleResult(URL url ) throws IOException, SAXException{
		RouteRuleResult rules=null;
		
		
			Object obj=digest.parse(url);
			rules=(RouteRuleResult)obj;
		
		
		return rules;
	}
	
	
	public RouteRuleResult getRouteRuleResultByLocalFile(String url ){
		RouteRuleResult rules=null;
		
		
		try {
			String file=RulesParser.class.getResource(url).getFile();
			Object obj=digest.parse(new File(file));
			rules=(RouteRuleResult)obj;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rules;
	}
	
	

}
