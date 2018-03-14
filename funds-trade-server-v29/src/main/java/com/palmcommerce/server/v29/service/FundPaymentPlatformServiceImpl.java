package com.palmcommerce.server.v29.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.palmcommerce.funds.protocol.trade.IStatus;
import com.palmcommerce.funds.service.ProtocolStorageException;
import com.palmcommerce.funds.util.DateConvertor;
import com.palmcommerce.server.v29.db.UserAccount;
import com.palmcommerce.server.v29.db.UserAccountLog100;
import com.palmcommerce.server.v29.db.UserAccountLog101;
import com.palmcommerce.server.v29.db.UserAccountLog102;
import com.palmcommerce.server.v29.db.UserAccountLog103;
import com.palmcommerce.server.v29.db.UserAccountLog104;
import com.palmcommerce.server.v29.db.UserAccountLog105;
import com.palmcommerce.server.v29.db.UserAccountLog106;
import com.palmcommerce.server.v29.db.UserAccountLog107;
import com.palmcommerce.server.v29.db.UserAccountLog108;
import com.palmcommerce.server.v29.db.UserAccountLog109;
import com.palmcommerce.server.v29.db.UserCharge;


public class FundPaymentPlatformServiceImpl implements FundPaymentPlatformService,IStatus {

	@Autowired
	UserChargeService userChargeService;
	@Autowired
	UserAccountLogService userAccountLogService;
	@Autowired
	UserAccountService userAccountService;
	
	
	@Override
	public boolean chargeByBank(String bankCode, String bankDate,
			String transactionTime, String paymentTerminalType, String orderId,
			String userId, String userName, double payMoney)
			throws ProtocolStorageException {
		// TODO Auto-generated method stub
		boolean ret=false;
		
		List<UserAccount> accounts=userAccountService.findUserAccountByUserId(userId);
		
		if(accounts.size()>0){
			UserAccount account=accounts.get(0);
			//insert into the usercharge
			UserCharge userCharge=new UserCharge();
			
			userCharge.setBatchId(bankCode);
			userCharge.setChargeId(orderId);
			userCharge.setChargeType(new BigDecimal(9));
			userCharge.setCreateTime(DateConvertor.parserTradeTime(transactionTime));
			userCharge.setIsSend(new BigDecimal(1));
			userCharge.setMoney(new BigDecimal(payMoney));
			userCharge.setOperatorStatus(new BigDecimal(1));
			userCharge.setOrderId(orderId);
			userCharge.setPartnerId(account.getPartnerId());
			userCharge.setPayOrderId("");
			userCharge.setReturnCode(bankCode);
			userCharge.setReturnMsg(bankDate);
			userCharge.setStatus(new BigDecimal(1));
			userCharge.setUpdateTime(userCharge.getCreateTime());
			userCharge.setType(new BigDecimal(1));
			userCharge.setUserId(userId);
			
			
			
			
			
		
			
			//userCharge.persist();
			userChargeService.saveUserCharge(userCharge);
			
			String optCode="add";
			BigDecimal cash=
					
				new BigDecimal(	account.getMoney().doubleValue()+userCharge.getMoney().doubleValue());
			account.setMoney(cash);
			account.setOptTime(userCharge.getUpdateTime());
			
			Object t=userAccountLogService.newUserAccount(userId);
			
			
			
			if(t instanceof UserAccountLog100){
				UserAccountLog100 t100=((UserAccountLog100)t);
				t100.setCreateTime(userCharge.getCreateTime());
				t100.setOptMoney(userCharge.getMoney());
				t100.setFrezzMoney(new BigDecimal(0.0));
			
				t100.setCash(cash);
				t100.setForigenId(orderId);
				t100.setOptCode(optCode);
				t100.setUserId(userId);
				t100.setPartnerId(account.getPartnerId());
				t100.persist();
			}else if (t instanceof UserAccountLog101){
				UserAccountLog101 t101=((UserAccountLog101)t);
				t101.setCreateTime(userCharge.getCreateTime());
				t101.setOptMoney(userCharge.getMoney());
				t101.setFrezzMoney(new BigDecimal(0.0));
				t101.setCash(cash);
				t101.setForigenId(orderId);
				t101.setOptCode(optCode);
				t101.setUserId(userId);
				t101.setPartnerId(account.getPartnerId());
				t101.persist();
			}else if(t instanceof UserAccountLog102){
				UserAccountLog102 t102=((UserAccountLog102)t);
				t102.setCreateTime(userCharge.getCreateTime());
				t102.setOptMoney(userCharge.getMoney());
				t102.setFrezzMoney(new BigDecimal(0.0));
				
				t102.setCash(cash);
				t102.setForigenId(orderId);
				t102.setOptCode(optCode);
				t102.setUserId(userId);
				t102.setPartnerId(account.getPartnerId());
				t102.persist();
			}else if (t instanceof UserAccountLog103){
				UserAccountLog103 t103=((UserAccountLog103)t);
				t103.setCreateTime(userCharge.getCreateTime());
				t103.setOptMoney(userCharge.getMoney());
				t103.setFrezzMoney(new BigDecimal(0.0));
				
				t103.setCash(cash);
				t103.setForigenId(orderId);
				t103.setOptCode(optCode);
				t103.setUserId(userId);
				t103.setPartnerId(account.getPartnerId());
				t103.persist();
			} else if(t instanceof UserAccountLog104){
				UserAccountLog104 t104=((UserAccountLog104)t);
				t104.setCreateTime(userCharge.getCreateTime());
				t104.setOptMoney(userCharge.getMoney());
				t104.setFrezzMoney(new BigDecimal(0.0));
				
				t104.setCash(cash);
				t104.setForigenId(orderId);
				t104.setOptCode(optCode);
				t104.setUserId(userId);
				t104.setPartnerId(account.getPartnerId());
				t104.persist();
			}else if (t instanceof UserAccountLog105){
				UserAccountLog105 t105=((UserAccountLog105)t);
				t105.setCreateTime(userCharge.getCreateTime());
				t105.setOptMoney(userCharge.getMoney());
				t105.setFrezzMoney(new BigDecimal(0.0));
				
				t105.setCash(cash);
				t105.setForigenId(orderId);
				t105.setOptCode(optCode);
				t105.setUserId(userId);
				t105.setPartnerId(account.getPartnerId());
				t105.persist();
			} else if(t instanceof UserAccountLog106){
				UserAccountLog106 t106=((UserAccountLog106)t);
				t106.setCreateTime(userCharge.getCreateTime());
				t106.setOptMoney(userCharge.getMoney());
				t106.setFrezzMoney(new BigDecimal(0.0));
				
				t106.setCash(cash);
				t106.setForigenId(orderId);
				t106.setOptCode(optCode);
				t106.setUserId(userId);
				t106.setPartnerId(account.getPartnerId());
				t106.persist();
			}else if (t instanceof UserAccountLog107){
				UserAccountLog107 t107=((UserAccountLog107)t);
				t107.setCreateTime(userCharge.getCreateTime());
				t107.setOptMoney(userCharge.getMoney());
				t107.setFrezzMoney(new BigDecimal(0.0));
				
				t107.setCash(cash);
				t107.setForigenId(orderId);
				t107.setOptCode(optCode);
				t107.setUserId(userId);
				t107.setPartnerId(account.getPartnerId());
				t107.persist();
			} else if(t instanceof UserAccountLog108){
				UserAccountLog108 t108=((UserAccountLog108)t);
				t108.setCreateTime(userCharge.getCreateTime());
				t108.setOptMoney(userCharge.getMoney());
				t108.setFrezzMoney(new BigDecimal(0.0));
				
				t108.setCash(cash);
				t108.setForigenId(orderId);
				t108.setOptCode(optCode);
				t108.setUserId(userId);
				t108.setPartnerId(account.getPartnerId());
				t108.persist();
			}else if (t instanceof UserAccountLog109){
				UserAccountLog109 t109=((UserAccountLog109)t);
				
				t109.setCreateTime(userCharge.getCreateTime());
				t109.setOptMoney(userCharge.getMoney());
				t109.setFrezzMoney(new BigDecimal(0.0));
				t109.setCash(cash);
				t109.setForigenId(orderId);
				t109.setOptCode(optCode);
				t109.setUserId(userId);
				t109.setPartnerId(account.getPartnerId());
				t109.persist();
			} 
			
			
		}else{
			throw userNotExistException;
		}
		
		// 
		//UserAccount userAccount=userAccountService.
		//UserAccount userAccount=userAccountService.
		
		return ret;
	}
	
}
