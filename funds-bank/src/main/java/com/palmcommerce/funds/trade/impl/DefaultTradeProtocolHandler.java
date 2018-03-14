/**
 * 
 */
package com.palmcommerce.funds.trade.impl;


import org.apache.commons.id.serial.PrefixedAlphanumericGenerator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.palmcommerce.funds.connection.mina.protocol.AbstractServerSideProtocolProcessor;
import com.palmcommerce.funds.protocol.impl.IProtocol;
import com.palmcommerce.funds.protocol.impl.p2t.P240001;
import com.palmcommerce.funds.protocol.impl.p2t.P240002;
import com.palmcommerce.funds.protocol.impl.p2t.P240003;
import com.palmcommerce.funds.protocol.impl.p2t.P240004;
import com.palmcommerce.funds.protocol.impl.p2t.P240005;
import com.palmcommerce.funds.protocol.impl.p2t.P240006;
import com.palmcommerce.funds.protocol.trade.IStatus;
import com.palmcommerce.funds.protocol.trade.ITradeProtocolHandler;
import com.palmcommerce.funds.protocol.util.SerialGenerator;
import com.palmcommerce.funds.service.ProtocolStorageException;


/**
 * @author sparrow
 *
 */
public class DefaultTradeProtocolHandler extends AbstractServerSideProtocolProcessor implements ITradeProtocolHandler,IStatus{
	private static final Log logger=LogFactory.getLog(DefaultTradeProtocolHandler.class);
	PrefixedAlphanumericGenerator  generator;

	/**
	 * 
	 */
	public DefaultTradeProtocolHandler() {
		// TODO Auto-generated constructor stub
		generator=new PrefixedAlphanumericGenerator(SerialGenerator.getSerialPrefix(), true, 18);
	}

	public boolean p240001(P240001 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		//return this.service;
		p.request.getSerialNumber();
		p.request.getTransactionTime();
		p.request.getUserId();
		
		//TODO
		
		p.response=new P240001.Response();
		p.response.setAccountBalance("89000");
		p.response.setCode(STATE_OK);
		p.response.setReason("");
		p.response.setIdNumber("210292191802930392");
		p.response.setIdType("1");
		p.response.setSerialNumber(p.request.getSerialNumber());
		p.response.setUserId(SerialGenerator.generateUserId());
		p.response.setUserName(SerialGenerator.generateUserName());
		// need to convert
		
		logger.info(p.toString());
		return true;
	}

	public boolean p240002(P240002 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		p.request.getAccount();
		p.request.getAccountTime();
		p.request.getBindType();
		p.request.getIdNumber();
		p.request.getIdType();
		p.request.getSerialNumber();
		p.request.getTransactionTime();
		p.request.getUserId();
		p.request.getUserName();
		
		
		//TODO
		
		p.response=new P240002.Response();
		p.response.setCode(STATE_OK);
		p.response.setReason("");
		p.response.setSerialNumber(p.request.getSerialNumber());
		
		
		logger.info(p.toString());
		return true;
	}

	public boolean p240003(P240003 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		p.request.getAccountTime();
		p.request.getPaymentAmount();
		p.request.getPaymentType();
		p.request.getSerialNumber();
		p.request.getTransactionTime();
		p.request.getUserId();
		p.request.getUserName();
		
		//TODO
		
		
		p.response=new P240003.Response();
		p.response.setCode(STATE_OK);
		p.response.setReason("");
		p.response.setSerialNumber(p.request.getSerialNumber());
		logger.info(p.toString());
		return true;
	}

	public boolean p240004(P240004 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		
		p.request.getAccountTime();
		p.request.getPaymentSerialNumber();
		p.request.getReverseAmount();
		p.request.getSerialNumber();
		p.request.getTransactionTime();
		p.request.getUserId();
		p.request.getUserName();
		
		//TODO
		
		p.response=new P240004.Response();
		p.response.setCode(STATE_OK);
		p.response.setReason("");
		p.response.setSerialNumber(p.request.getSerialNumber());
		p.response.setSeverseSerialNumber(p.request.getPaymentSerialNumber());
		
		
		logger.info(p.toString());
		
		return true;
	}

	public boolean p240005(P240005 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		
		p.request.getAccount();
		p.request.getAccountTime();
		p.request.getIdNumber();
		p.request.getIdType();
		p.request.getPhone();
		p.request.getSerialNumber();
		p.request.getTransactionTime();
		p.request.getUserName();
		
		
		//TODO
		
		p.response=new P240005.Response();
		p.response.setCode(STATE_OK);
		
		p.response.setSerialNumber(p.request.getSerialNumber());
		
		p.response.setUserId(SerialGenerator.generateUserId());
		p.response.setUserName(SerialGenerator.generateUserName());
		
		
		
		
		
		logger.info(p.toString());
		
		
		return true;
	}

	public boolean p240006(P240006 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.trade.impl.AbstractServerSideProtocolProcessor#handle(com.palmcommerce.funds.protocol.impl.IProtocol)
	 */
	@Override
	public void handle(IProtocol protocol) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		switch (protocol.getHeader().transcode) {

		case 240001: {
			P240001 p=(P240001)protocol;
			
			p240001(p);
				
		}
			// paymentService.p240001((P240001)header);
			break;

		case 240002: {

			P240002 p=(P240002)protocol;
			p240002(p);
				
			
		}
		
			break;

		case 240003: {

			P240003 p=(P240003)protocol;
			
			
			p240003(p);
				
		}
			// paymentService.p240001((P240001)header);
			break;

		case 240004: {

			P240004 p=(P240004)protocol;
			
			
			p240004(p);
				
			
		}
			// paymentService.p240001((P240001)header);
			break;

		case 240005: {

			P240005 p=(P240005)protocol;
			
			p240005(p);
				
			
		}
			// paymentService.p240001((P240001)header);
			break;

		default:{
			// non handler.
			
			
		}
			break;
		}
		
		
	}

}
