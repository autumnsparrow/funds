/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Nov 3, 2013
 *
 */
package com.palmcommerce.funds.id.impl;

import java.util.Date;

import org.apache.commons.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import com.palmcommerce.funds.id.model.LocalAndRemoteSerialRelation;
import com.palmcommerce.funds.id.service.RedisLocalAndRemoteRelationshipService;
import com.palmcommerce.funds.id.service.RedisLocalAndRemoteRelationshipService.IdMap;


/**
 * @author sparrow
 *
 */
public class RedisIdGenerator implements IIdGenerator {
	
	@Autowired
	GlobalIdGenerator globalIdGenerator;
	
	@Autowired
	RedisLocalAndRemoteRelationshipService redisIdRelation;

	/**
	 * 
	 */
	public RedisIdGenerator() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.id.impl.IIdGenerator#getLocalSerialForRemoteSerial(java.lang.String)
	 */
	@Override
	public String getLocalSerialForRemoteSerial(String serial) {
		// TODO Auto-generated method stub
		//return null;
		String localSerial=redisIdRelation.findLocalByRemote(serial);//localAndRemoteSerialRelationService.findLocalSerialByRemoteSerial(serial);
		if(localSerial==null){
			localSerial=globalIdGenerator.nextSerialId();
			IdMap relation=new IdMap();
			relation.setLocal(localSerial);
			relation.setRemote(serial);
			redisIdRelation.persist(relation);
			
			
		}
		return localSerial;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.id.impl.IIdGenerator#getRemoteSerialByLocalSerial(java.lang.String)
	 */
	@Override
	public String getRemoteSerialByLocalSerial(String localSerial) {
		// TODO Auto-generated method stub
		String remoteSerial=redisIdRelation.findRemoteByLocal(localSerial);
		if(remoteSerial==null)
			throw new NullPointerException("Can't get remote serial by local serial:"+localSerial);
		return remoteSerial;
	}

	
}
