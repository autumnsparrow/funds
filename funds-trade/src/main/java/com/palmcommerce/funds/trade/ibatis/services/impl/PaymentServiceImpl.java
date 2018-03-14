package com.palmcommerce.funds.trade.ibatis.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibatis.sqlmap.engine.transaction.TransactionManager;
import com.palmcommerce.funds.trade.ibatis.bean.AccountBankBean;
import com.palmcommerce.funds.trade.ibatis.bean.BankBindBean;
import com.palmcommerce.funds.trade.ibatis.bean.PartnerAccountDetail;
import com.palmcommerce.funds.trade.ibatis.bean.PayMoneyBean;
import com.palmcommerce.funds.trade.ibatis.dao.PaymentDao;
import com.palmcommerce.funds.trade.ibatis.dao.UserInfoDao;
import com.palmcommerce.funds.trade.ibatis.services.PaymentService;

public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private PaymentDao paymentDao;
	
	public PayMoneyBean getPayMoneyBySeriesNo(String seriesNo,String bankCode,String accounttime){
		return this.paymentDao.getPayMoneyBySeriesNo(seriesNo,bankCode,accounttime);
	}

	public int insertPayMoneyBean(PayMoneyBean bean) {
		// TODO Auto-generated method stub
		 this.paymentDao.insertPayMoneyBean(bean);
		 return this.userInfoDao.updateMoney(bean.getUserId(), bean.getMoney());
		
	}
	
	
	public int updatePayMoneyBean(PayMoneyBean bean,boolean minus){
		 this.paymentDao.updatePayMoneyBean(bean);
		 double money=minus?-bean.getMoney():bean.getMoney();
		return this.userInfoDao.updateMoney(bean.getUserId(), money);
	}
	public int updatePayMoneyBean(PayMoneyBean bean){
		return this.paymentDao.updatePayMoneyBean(bean);
		 
	}
	public int updateCallbackPayMoney(PayMoneyBean bean){

		//bean.setMoney(-bean.getMoney());
		 this.paymentDao.insertPayMoneyBean(bean);
	//	this.paymentDao.updatePayMoneyBean(bean);
		 // also need update the cancel paymoney bean
		 PayMoneyBean cancelBean=new PayMoneyBean();
		 cancelBean.setSeriesNo(bean.getCancelSerialNo());
		 cancelBean.setBalanceMoney(0.0);
		 cancelBean.setBankCode(bean.getBankCode());
		 cancelBean.setAccountType(bean.getAccountType());
		 cancelBean.setAccountTime(bean.getAccountTime());
		 this.paymentDao.updatePayMoneyBean(cancelBean);

		return this.userInfoDao.updateMoney(bean.getUserId(), -bean.getMoney());
		
	}

	public PaymentDao getPaymentDao() {
		return paymentDao;
	}

	public void setPaymentDao(PaymentDao paymentDao) {
		this.paymentDao = paymentDao;
	}

	public AccountBankBean getAccountBankBean(String accountTime,
			String bankcode) {
		// TODO Auto-generated method stub
		return this.paymentDao.getAccountBankBean(accountTime, bankcode);
	}
	
	public int deleteAccountBankBean(String accountTime,String bankcode){
		return this.paymentDao.deleteAccountBankBean(accountTime, bankcode);
	}

	public List<PayMoneyBean> getPayMoneyList(String accountTime,String bankCode,
			String accountType) {
		// TODO Auto-generated method stub
		return this.paymentDao.getPayMoneyList(accountTime,bankCode, accountType);
	}

	public int insertAccountBankBean(AccountBankBean bean) {
		// TODO Auto-generated method stub
		return this.paymentDao.insertAccountBankBean(bean);
	}

	public BankBindBean getBankBindBean(String seriesNo,String bankCode) {
		// TODO Auto-generated method stub
		return this.paymentDao.getBankBindBean(seriesNo,bankCode);
	}
	
	

	public int insertBankBindBean(BankBindBean bean) {
		// TODO Auto-generated method stub
		this.paymentDao.insertBankBindBean(bean);
		
		String bankNumber=null;
		if(bean.getBindType()==0){
			bankNumber=bean.getBankNumber();
		}
		return this.userInfoDao.updateBanknumber(bean.getUserId(), bankNumber);
		 
	}

	public UserInfoDao getUserInfoDao() {
		return userInfoDao;
	}

	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	public int insertOldPayMoneyBean(PayMoneyBean bean) {
		// TODO Auto-generated method stub
		return this.paymentDao.insertPayMoneyBean(bean);
	}

	public int deleteAccountDetail(String partnerId, String gameId,
			String accountTime) {
		// TODO Auto-generated method stub
		return this.paymentDao.deleteAccountDetail(partnerId, gameId, accountTime);
	}

	public PartnerAccountDetail getPartnerAccountDetail(String partnerId,
			String gameId, String accountTime) {
		// TODO Auto-generated method stub
		return this.paymentDao.getPartnerAccountDetail(partnerId, gameId, accountTime);
	}

	public int insertPartnerAccountDeatil(PartnerAccountDetail detail) {
		// TODO Auto-generated method stub
		return this.paymentDao.insertPartnerAccountDeatil(detail);
	}

	public PayMoneyBean getPayMoneyCancelBySeriesNo(String cancelSerialNo,
			String bankCode, String accounttime) {
		// TODO Auto-generated method stub
		return this.paymentDao.getPayMoneyByCancelSeriesNo(cancelSerialNo,bankCode,accounttime);
	}

	public int insertPaymentSerialNumber(String bankCode,
			String paymentSerialNumber,String datetime) {
		// TODO Auto-generated method stub
		return this.paymentDao.insertPaymentSerialNumber(bankCode,paymentSerialNumber,datetime);
	}

	public BankBindBean getBankBindByUserId(String userId, String bankCode) {
		// TODO Auto-generated method stub
		return this.paymentDao.getBankBindByUserId(userId,bankCode);
	}


	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.trade.ibatis.services.PaymentService#updateUserAccount(com.palmcommerce.funds.trade.ibatis.bean.PayMoneyBean)
	 */
	public int updateUserAccount(PayMoneyBean bean) {
		// TODO Auto-generated method stub
		return this.userInfoDao.updateMoney(bean.getUserId(), bean.getMoney());
	}


	public int updateBankBindBean(BankBindBean bean) {
		// TODO Auto-generated method stub
		this.paymentDao.updateBankBindBean(bean);
		
		String bankNumber=null;
		if(bean.getBindType()==0){
			bankNumber=bean.getBankNumber();
		}
		return this.userInfoDao.updateBanknumber(bean.getUserId(), bankNumber);
	}


}
