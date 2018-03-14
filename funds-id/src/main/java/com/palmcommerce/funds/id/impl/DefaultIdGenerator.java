/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 26, 2013
 *
 */
package com.palmcommerce.funds.id.impl;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.j256.simplejmx.common.JmxOperation;
import com.j256.simplejmx.common.JmxResource;
import com.palmcommerce.funds.id.model.LocalAndRemoteSerialRelation;
import com.palmcommerce.funds.id.service.LocalAndRemoteSerialRelationService;


/**
 * @author sparrow
 *
 */
@JmxResource(domainName="com.palmcommerce.funds.id.impl",beanName="DefaultIdGenerator",description=" query id relationship")
public class DefaultIdGenerator implements IIdGenerator {

	@Autowired
	LocalAndRemoteSerialRelationService localAndRemoteSerialRelationService;
	
	@Autowired
	GlobalIdGenerator globalIdGenerator;

	/**
	 * 
	 */
	public DefaultIdGenerator() {
		// TODO Auto-generated constructor stub
		
	}
	@JmxOperation(description="get local serial by remote serial",parameterNames={"serial"},parameterDescriptions={" remote serial"})
	public String findLocalSerialByRemoteSerial(String serial){
		return getLocalSerialForRemoteSerial(serial);
	}
	
	@JmxOperation(description="get remote serial by local serial",parameterNames={"localSerial"},parameterDescriptions={"local serial"})
	public String findRemoteSerialByLocalSerial(String localSerial){
		return getRemoteSerialByLocalSerial(localSerial);
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.trade.id.impl.IIdGenerator#getLocalSerial(java.lang.String)
	 */
	public String getLocalSerialForRemoteSerial(String serial) {
		// TODO Auto-generated method stub
		//String remoteSerial=localAndRemoteSerialRelationService.find
		String localSerial=localAndRemoteSerialRelationService.findLocalSerialByRemoteSerial(serial);
		if(localSerial==null){
			localSerial=globalIdGenerator.nextSerialId();
			LocalAndRemoteSerialRelation relation=new LocalAndRemoteSerialRelation();
			relation.setLocalSerialNumber(localSerial);
			relation.setRemoteSerialNumber(serial);
			relation.setCreateTime(new Date());
			localAndRemoteSerialRelationService.saveLocalAndRemoteSerialRelation(relation);
			
		}
		return localSerial;
		
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.trade.id.impl.IIdGenerator#getSerialByLocalSerial(java.lang.String)
	 */
	public String getRemoteSerialByLocalSerial(String localSerial) {
		// TODO Auto-generated method stub
		String remoteSerial=localAndRemoteSerialRelationService.findRemoteSerialByLocalSerial(localSerial);
		if(remoteSerial==null)
			throw new NullPointerException("Can't get remote serial by local serial:"+localSerial);
		return remoteSerial;
		
	}

}
