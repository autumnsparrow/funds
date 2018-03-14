package com.palmcommerce.funds.ice.trade.ice.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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
import com.palmcommerce.funds.service.ProtocolStorageException;
import com.palmcommerce.server.v29.db.UserAccount;
import com.palmcommerce.server.v29.db.UserIdentify;
import com.palmcommerce.server.v29.service.FundPaymentPlatformService;
import com.palmcommerce.server.v29.service.UserAccountService;
import com.palmcommerce.server.v29.service.UserIdentifyService;
import com.palmcommerce.server.v29.service.UserIdentifyServiceImpl;


/**
 * 
 */

/**
 * @author sparrow
 *
 */
public class TradeServerServerSideProcess extends
		AbstractServerSideProtocolProcessor implements ITradeProtocolHandler,IStatus{

	
	@Autowired
	FundPaymentPlatformService fundPaymentPlatformService;
	
	@Autowired
	UserIdentifyServiceImpl  userIdentifyService;
	
	@Autowired
	UserAccountService  userAccountService;
	/**
	 * 
	 */
	public TradeServerServerSideProcess() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.connection.mina.protocol.AbstractServerSideProtocolProcessor#handle(com.palmcommerce.funds.protocol.impl.IProtocol)
	 */
	@Override
	public void handle(IProtocol protocol) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		switch (protocol.getHeader().transcode) {

		case 240001: {
			P240001 p=(P240001)protocol;
			
			p240001(p);
				
		}
			
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

	public boolean p240001(P240001 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		
		
		String serialNumber=p.request.getSerialNumber();
		String transactionTime=p.request.getTransactionTime();
		String userId=p.request.getUserId();
		
		List<UserAccount> userAccounts=userAccountService.findUserAccountByUserId(userId);
		UserAccount userAccount=null;
		if(userAccounts.size()>0){
			userAccount=userAccounts.get(0);
			
		}
		String accountBalance="";
		String idNumber="";
		String idType="";
		String userName="";
		String code=userNotExistException.state;
		
		if(userAccount!=null){
			 UserIdentify  userIdentify= userIdentifyService.findUserIdentify(userId);
			 accountBalance=String.valueOf( userAccount.getMoney().doubleValue()*100);
			 idNumber=userIdentify.getCardType();
			 idType=userIdentify.getCardNo();
			 userName=userIdentify.getRealName();
			 code=STATE_OK;
		}
		p.response=new P240001.Response();
		p.response.setAccountBalance(accountBalance);
		p.response.setSerialNumber(serialNumber);
		p.response.setCode(code);
		p.response.setIdNumber(idNumber);
		p.response.setIdType(idType);
		p.response.setSerialNumber(serialNumber);
		p.response.setUserId(userId);
		p.response.setUserName(userName);
		
	
		

		return true;
	}

	public boolean p240002(P240002 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		
		return false;
	}

	public boolean p240003(P240003 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		String bankDate=p.request.getAccountTime();
		String bankMoney=p.request.getPaymentAmount();
		String paymentTerminalType=p.request.getPaymentType();
		String orderId=p.request.getSerialNumber();
		String transactionTime=p.request.getTransactionTime();
		String userId=p.request.getUserId();
		String userName=p.request.getUserName();
		
		boolean ret=false;
		double payMoney=Integer.parseInt(bankMoney)*0.01;
		
		
		try {
			fundPaymentPlatformService.chargeByBank(p.getHeader().from, bankDate, transactionTime, paymentTerminalType, orderId, userId, userName, payMoney);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			p.response.setCode("0001");
			p.response.setSerialNumber(orderId);
			p.response.setReason("FAILED");
		}
		
		p.response.setCode(STATE_OK);
		p.response.setSerialNumber(orderId);
		p.response.setReason("SUCCEED");
		ret=true;
		
		return ret;
	}

	public boolean p240004(P240004 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean p240005(P240005 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean p240006(P240006 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		return false;
	}

}
