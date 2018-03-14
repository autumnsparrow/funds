package com.palmcommerce.funds.id.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.palmcommerce.funds.id.model.LocalAndRemoteSerialRelation;


public class LocalAndRemoteSerialRelationServiceImpl implements LocalAndRemoteSerialRelationService {
	private static final Log logger=LogFactory.getLog(LocalAndRemoteSerialRelationServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.id.service.LocalAndRemoteSerialRelationService#findLocalSerialByRemoteSerial(java.lang.String)
	 */
	@Override
	public String findLocalSerialByRemoteSerial(String remotSerial) {
		// TODO Auto-generated method stub
		LocalAndRemoteSerialRelation relation=null;
		try {
			relation = LocalAndRemoteSerialRelation.findLocalAndRemoteSerialRelationsByRemoteSerialNumberEquals(remotSerial).getSingleResult();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e.getMessage());
			
		}
		String localSerial=null;
		if(relation!=null){
			localSerial=relation.getLocalSerialNumber();
			logger.info("FR|"+relation.toString());
		}
		return localSerial;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.id.service.LocalAndRemoteSerialRelationService#findRemoteSerialByLocalSerial(java.lang.String)
	 */
	@Override
	public String findRemoteSerialByLocalSerial(String localSerial) {
		// TODO Auto-generated method stub
		LocalAndRemoteSerialRelation relation=null;
		try {
			relation = LocalAndRemoteSerialRelation.findLocalAndRemoteSerialRelationsByLocalSerialNumberEquals(localSerial).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e.getMessage());
		}
		String remoteSerial=null;
		if(relation!=null){
			remoteSerial=relation.getRemoteSerialNumber();
			logger.info("FL|"+relation.toString());
		}
		return remoteSerial;
	}
}
