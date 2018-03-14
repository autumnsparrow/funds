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
 */
@ObjectCreate(pattern="ServerRules/Proxy")
public class Proxy {

	/**
	 * 
	 */
	public Proxy() {
		// TODO Auto-generated constructor stub
	}
	@BeanPropertySetter(pattern="ServerRules/Proxy/NodeCode")
	String nodeCode;
	@BeanPropertySetter(pattern="ServerRules/Proxy/RootCaCode")
	String rootCaNodecode;
	/**
	 * @return the rootCaNodecode
	 */
	public String getRootCaNodecode() {
		return rootCaNodecode;
	}
	/**
	 * @param rootCaNodecode the rootCaNodecode to set
	 */
	public void setRootCaNodecode(String rootCaNodecode) {
		this.rootCaNodecode = rootCaNodecode;
	}
	/**
	 * @return the nodeCode
	 */
	public String getNodeCode() {
		return nodeCode;
	}
	/**
	 * @param nodeCode the nodeCode to set
	 */
	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}
	
	

}
