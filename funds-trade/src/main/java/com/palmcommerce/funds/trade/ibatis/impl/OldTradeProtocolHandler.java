package com.palmcommerce.funds.trade.ibatis.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.palmcommerce.funds.trade.ibatis.bean.BankBindBean;
import com.palmcommerce.funds.trade.ibatis.bean.PayMoneyBean;

import com.palmcommerce.funds.trade.ibatis.bean.UserInfo;
import com.palmcommerce.funds.trade.ibatis.services.PaymentService;
import com.palmcommerce.funds.trade.ibatis.services.UserInfoService;



/**
 * @author sparrow
 *
 */
public class OldTradeProtocolHandler extends AbstractServerSideProtocolProcessor implements ITradeProtocolHandler,IStatus {
	private static final Log logger=LogFactory.getLog(OldTradeProtocolHandler.class);
	@Autowired
	PaymentService  paymentService;
	@Autowired
	UserInfoService userInfoService;
	
	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
		
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	/**
	 * Just saving the data into the database.
	 */
	public OldTradeProtocolHandler() {
		// TODO Auto-generated constructor stub
	}
	
	private void checkPaymentSerialNumber(String bankCode,String paymentSerialNumber,String datetime)throws ProtocolStorageException{
		int row=paymentService.insertPaymentSerialNumber(bankCode, paymentSerialNumber,datetime);
		if(row==-1)
			throw paymentSerialRepeated;
		
	}

	/**
	 * 1.1.1  查询用户信息交易（240001）
	 * 
	 * validate the payment serial is repeated.
	 * (non-Javadoc)
	 * @see com.palmcommerce.funds.trade.ITradeProtocolHandler#p240001(com.palmcommerce.funds.protocol.impl.p2t.P240001)
	 */
	public boolean p240001(P240001 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		String serial=p.request.getSerialNumber();
		String transactionTime=p.request.getTransactionTime();
		String userId=p.request.getUserId();
		
		checkPaymentSerialNumber(p.getHeader().from,serial,transactionTime);
		
		
		UserInfo info=null;
		try {
			info = userInfoService.getUser(userId);
			if(info==null){
				throw userNotExistException;
			}
		} catch (ProtocolStorageException ex){
			
			p.response=new P240001.Response();
			p.response.setAccountBalance("");
			p.response.setCode(ex.state);
			p.response.setReason(ex.reason);
			p.response.setIdNumber("");
			p.response.setIdType("");
			p.response.setSerialNumber(serial);
			p.response.setUserId("");
			p.response.setUserName("");
			// need to convert
			
			logger.info(p.toString());
			return false;
			
		}
		
		//TODO
		
		p.response=new P240001.Response();
		// Yuan to Fen
		double amount=info.getAccountMoney();
		int centAmount=(int)(amount*100);
		p.response.setAccountBalance(String.valueOf(centAmount));
		p.response.setCode(STATE_OK);
		p.response.setReason("");
		
		p.response.setIdNumber(info.getIdNo());
		if(info.getIdType()!=null)
			p.response.setIdType(String.valueOf(info.getIdType().intValue()));
		else
			p.response.setIdType("1");
		p.response.setSerialNumber(serial);
		p.response.setUserId(info.getUserId());
		p.response.setUserName(info.getRealName());
		// need to convert
		
		logger.info(p.toString());
		
		return true;
	}

	/*
	 *  (non-Javadoc)
	 *  用户ID和账户绑定交易
	 * @see com.palmcommerce.funds.trade.ITradeProtocolHandler#p240002(com.palmcommerce.funds.protocol.impl.p2t.P240002)
	 */
	public boolean p240002(P240002 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		
		String account=p.request.getAccount();
		String accountTime=p.request.getAccountTime();
		String bindType=p.request.getBindType();
		String idNumber=p.request.getIdNumber();
		String idType=p.request.getIdType();
		String serial=p.request.getSerialNumber();
		String transactionTime=p.request.getTransactionTime();
		String userId=p.request.getUserId();
		String userName=p.request.getUserName();
		

		
		
		String compareTransactionTime=transactionTime.split(" ")[0];
		String datetime=compareTransactionTime.replace("-", "");
		if(!accountTime.equals(datetime)){
			throw transactionTimeNotEqualsAccountTimeException;
		}
			
		
		try {
			BankBindBean bankBindBean=paymentService.getBankBindBean(serial, p.getHeader().from);
			if(bankBindBean!=null){
				throw userAlreadBindingException;
			}
			UserInfo user=userInfoService.getUser(userId);		
			if(user==null){
				throw userNotExistException;
			}
			
			BankBindBean alreadyBind=paymentService.getBankBindByUserId(userId, p.getHeader().from);
			
			int currentBindType=Integer.parseInt(bindType);
			
			if(alreadyBind==null&&currentBindType==1){
				throw canotUnbindWithoutBindingException;
			}
			
			if(alreadyBind!=null){
				int alreadBindType=alreadyBind.getBindType();
				if(currentBindType==0&&alreadBindType==0){
					throw canotbindWithBindingException;
				}
				if(currentBindType==1&&alreadBindType==1){
					throw canotUnbindWithunBindingException;
				}
				
			}
			
			
			
			
			
			//BindBean bindBean=new Bind
			BankBindBean bind=new BankBindBean();
			bind.setBankCode(p.getHeader().from);
			bind.setBindType(Integer.parseInt(bindType));
			bind.setSeriesNo(serial);
			bind.setUserId(userId);
			bind.setUserName(userName);
			bind.setBankNumber(account);
			bind.setModifyTime(transactionTime);
			int currentIdType=Integer.parseInt(idType);
			int userIdType=user.getIdType()==null?0:user.getIdType().intValue();
			boolean condition=userId.equals(user.getUserId())
					&&userName.equals(user.getRealName())
					&&currentIdType==userIdType
					&&idNumber.equals(user.getIdNo());
			if(!condition){
				throw bindingFailedBindingByInformationMisMatchException;
			}
			
			if(!account.equals(user.getBankAccount())&&bind.getBindType()==1){
				throw unbindAccountMismatchException;
			}
			
			
			
			try {
				if(alreadyBind!=null){
					paymentService.updateBankBindBean(bind);
				}else{
					int rowAffected=paymentService.insertBankBindBean(bind);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw bindingFailedBindingException;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ProtocolStorageException ex){
			p.response=new P240002.Response();
			p.response.setCode(ex.state);
			p.response.setReason(ex.reason);
			p.response.setSerialNumber(serial);
			
			
			logger.info(p.toString());
			return false;
		}
		
		
		//TODO
		
		p.response=new P240002.Response();
		p.response.setCode(STATE_OK);
		p.response.setReason("");
		p.response.setSerialNumber(serial);
		
		
		logger.info(p.toString());
		return true;
	}

	/* (non-Javadoc)
	 * 
	 * @see com.palmcommerce.funds.trade.ITradeProtocolHandler#p240003(com.palmcommerce.funds.protocol.impl.p2t.P240003)
	 */
	public boolean p240003(P240003 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		
		String accountTime=p.request.getAccountTime();
		String amount=p.request.getPaymentAmount();
		String paymentType=p.request.getPaymentType();
		String serial=p.request.getSerialNumber();
		String transactionTime=p.request.getTransactionTime();
		String userId=p.request.getUserId();
		String userName=p.request.getUserName();
		String bankCode=p.getHeader().from;
		//String partnerId = p.request.getPartnerId();

//		String compareTransactionTime=transactionTime.split(" ")[0];
//		String datetime=compareTransactionTime.replace("-", "");
//		if(!accountTime.equals(datetime)){
//			throw transactionTimeNotEqualsAccountTimeException;
//		}
			
		boolean amountCheck=StringUtils.isNumeric(amount)&&(amount.indexOf(".")==-1);
		if(!amountCheck){
			throw paymentAmountFormatWrongRepeated;
		}
		
		checkPaymentSerialNumber(p.getHeader().from,serial,transactionTime);
		
		//String transactionTime=p.request.getTransactionTime();
		// first checking the paymean exists
		PayMoneyBean bean=paymentService.getPayMoneyBySeriesNo(serial, p.getHeader().from, accountTime);
		try {
			if(bean!=null){
				throw chargeSerialRepeatedException;
			}
			UserInfo info=userInfoService.getUser(userId);
			String partnerId=info.getPartnerId();
			if(info==null){
				throw userNotExistException;
			}
			boolean condition=userId.equals(info.getUserId())
					
					;
			if(!condition){
				throw chargeFailedByInformationMisMatchException;
			}
			
//			int accountType=0;
//			if(paymentType!=null)
//				accountType=Integer.parseInt( paymentType.replace("0", ""));
			
			PayMoneyBean payMoneyBean=new PayMoneyBean();
			payMoneyBean.setAccountTime(accountTime);
			payMoneyBean.setMoney(Double.parseDouble(amount)/100);
			payMoneyBean.setBankCode(bankCode);
			payMoneyBean.setSeriesNo(serial);
			payMoneyBean.setUserId(userId);
			payMoneyBean.setUserName(userName);
			
			payMoneyBean.setAccountType(UserBankAccountState.UNBALANCE.getState());
			//payMoneyBean.setUserId(userId);
			payMoneyBean.setCreateTime(new java.util.Date());
			payMoneyBean.setPartnerId(partnerId);
			try {
				int rowAffected=paymentService.insertPayMoneyBean(payMoneyBean);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw chargeFailedException;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw paymentAmountFormatWrongRepeated;
		} catch(ProtocolStorageException ex){
			p.response=new P240003.Response();
			p.response.setCode(ex.state);
			p.response.setReason(ex.reason);
			p.response.setSerialNumber(p.request.getSerialNumber());
			logger.info(p.toString());
			return false;
		}
		
		
		
		p.response=new P240003.Response();
		p.response.setCode(STATE_OK);
		p.response.setReason("");
		p.response.setSerialNumber(p.request.getSerialNumber());
		
		logger.info(p.toString());
		return true;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.trade.ITradeProtocolHandler#p240004(com.palmcommerce.funds.protocol.impl.p2t.P240004)
	 */
	public boolean p240004(P240004 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		

		String accountTime=p.request.getAccountTime();
		String serial=p.request.getSerialNumber();
		String amount=p.request.getReverseAmount();
		String cancelSerial=p.request.getPaymentSerialNumber();
		String transactionTime=p.request.getTransactionTime();
		String userId=p.request.getUserId();
		String userName=p.request.getUserName();
		String bankCode=p.getHeader().from;
		
		
		
		
//		String compareTransactionTime=transactionTime.split(" ")[0];
//		String datetime=compareTransactionTime.replace("-", "");
//		if(!accountTime.equals(datetime)){
//			throw transactionTimeNotEqualsAccountTimeException;
//		}
		checkPaymentSerialNumber(p.getHeader().from,serial,transactionTime);
		
		boolean amountCheck=StringUtils.isNumeric(amount)&&(amount.indexOf(".")==-1);
		if(!amountCheck){
			throw paymentAmountFormatWrongRepeated;
		}
		
				PayMoneyBean bean=paymentService.getPayMoneyBySeriesNo(serial, p.getHeader().from, accountTime);

		try {
			if(bean!=null){
				throw chargeSerialRepeatedException;
			}
			UserInfo info=userInfoService.getUser(userId);
			String partnerId=info.getPartnerId();
			if(info==null){
				throw userNotExistException;
			}
			
			boolean condition=userId.equals(info.getUserId())
					&&userName.equals(info.getRealName())
					;
			if(!condition){
				throw cancelChargeFailedByInformationMisMatchException;
			}
			
			PayMoneyBean alreadyCanceled=paymentService.getPayMoneyCancelBySeriesNo(cancelSerial, p.getHeader().from, accountTime);
			if(alreadyCanceled!=null){
				throw cancelChargeAlreadyDonedException	;
			}
			
			PayMoneyBean cancelBean=paymentService.getPayMoneyBySeriesNo(cancelSerial, p.getHeader().from, accountTime);
			if(cancelBean==null)
				throw cancelChargeFailedCancelSerialNumberNotExistException	;
			
			PayMoneyBean payMoneyBean=new PayMoneyBean();
			payMoneyBean.setSeriesNo(serial);
			payMoneyBean.setAccountTime(accountTime);
			payMoneyBean.setMoney(Double.parseDouble(amount)*0.01);
			payMoneyBean.setBankCode(bankCode);
			payMoneyBean.setSeriesNo(serial);
			payMoneyBean.setUserId(userId);
			payMoneyBean.setUserName(userName);
			payMoneyBean.setUserId(userId);
			payMoneyBean.setCancelSerialNo(cancelSerial);
			payMoneyBean.setCreateTime(new java.util.Date());
			payMoneyBean.setPartnerId(partnerId);
			payMoneyBean.setAccountType(UserBankAccountState.CALLBACK.getState());
			
			if(!cancelBean.getUserId().equals(payMoneyBean.getUserId())){
				throw cancelChargeAccountIdNotEqualException;
			}
			
			if(!cancelBean.getAccountTime().equals(payMoneyBean.getAccountTime())){
				throw cancelChargeAccountTimeNotEqualException;
			}
			
			if(cancelBean.getMoney()!=payMoneyBean.getMoney()){
				throw cancelChargeAmountNotEqualException;
			}
			try {
				int rowAffected=paymentService.updateCallbackPayMoney(payMoneyBean);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw cancelChargeFailedException;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(ProtocolStorageException ex){
			p.response=new P240004.Response();
			p.response.setCode(ex.state);
			p.response.setReason(ex.reason);
			p.response.setSerialNumber(serial);
			p.response.setSeverseSerialNumber(cancelSerial);
			logger.info(p.toString());
			return false;
		}
		
		
		//TODO
		
		p.response=new P240004.Response();
		p.response.setCode(STATE_OK);
		p.response.setReason("");
		p.response.setSerialNumber(serial);
		p.response.setSeverseSerialNumber(cancelSerial);
		
		
		logger.info(p.toString());
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.trade.ITradeProtocolHandler#p240005(com.palmcommerce.funds.protocol.impl.p2t.P240005)
	 */
	public boolean p240005(P240005 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		
		String account=p.request.getAccount();
		String accountTime=p.request.getAccountTime();
		String idNumber=p.request.getIdNumber();
		String idType=p.request.getIdType();
		String phone=p.request.getPhone();
		String serial=p.request.getSerialNumber();
		String transactionTime=p.request.getTransactionTime();
		String userName=p.request.getUserName();
		
		

		String compareTransactionTime=transactionTime.split(" ")[0];
		String datetime=compareTransactionTime.replace("-", "");
		if(!accountTime.equals(datetime)){
			throw transactionTimeNotEqualsAccountTimeException;
		}
			
		
		checkPaymentSerialNumber(p.getHeader().from,serial,transactionTime);
		
		
		UserInfo userInfo=new UserInfo();
		userInfo.setBankAccount(account);
		userInfo.setIdNo(idNumber);
		userInfo.setIdType(Integer.parseInt(idType));
		userInfo.setPhoneNo(phone);
		userInfo.setRealName(userName);
		userInfo.setCreateTime(new java.util.Date());
		userInfo.setStatus(0L);
		
		try {
			int rowAffected=userInfoService.insertUser(userInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			p.response=new P240005.Response();
			p.response.setCode(insertuserFailedException.state);
			
			p.response.setSerialNumber(insertuserFailedException.reason);
			
			p.response.setUserId("");
			p.response.setUserName("");
			p.response.setReason(e.getMessage());
			return false;
		}
		
		
		//TODO
		
		p.response=new P240005.Response();
		p.response.setCode(STATE_OK);
		p.response.setReason("");
		p.response.setSerialNumber(serial);
		
		p.response.setUserId(userInfo.getUserId());
		p.response.setUserName(userInfo.getRealName());
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.trade.ITradeProtocolHandler#p240006(com.palmcommerce.funds.protocol.impl.p2t.P240006)
	 */
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
