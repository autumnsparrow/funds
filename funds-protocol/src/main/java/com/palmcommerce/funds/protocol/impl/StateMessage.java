/**
 * 
 */
package com.palmcommerce.funds.protocol.impl;

import com.palmcommerce.funds.protocol.ProtocolElementMetaType;
import com.palmcommerce.funds.protocol.ProtocolElementMetaType.ProtocolElementType;

/**
 * @author sparrow
 *
 */
public class StateMessage {
	
	@ProtocolElementMetaType(sequenceId=0,elementType=ProtocolElementType.STRING,isStateMessage=true,minLenght=4,maxLength=4)
	 String code;
	@ProtocolElementMetaType(sequenceId=1,elementType=ProtocolElementType.STRING,maxLength=30,isStateMessage=true,required=false)
	 String reason;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	@Override
	public String toString() {
		return "StateMessage [code=" + code + ", reason=" + reason + "]";
	}
	
	
	

}
