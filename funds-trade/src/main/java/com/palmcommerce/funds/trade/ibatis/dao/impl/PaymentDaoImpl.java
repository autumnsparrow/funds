package com.palmcommerce.funds.trade.ibatis.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.palmcommerce.funds.trade.ibatis.bean.AccountBankBean;
import com.palmcommerce.funds.trade.ibatis.bean.BankBindBean;
import com.palmcommerce.funds.trade.ibatis.bean.PartnerAccountDetail;
import com.palmcommerce.funds.trade.ibatis.bean.PayMoneyBean;
import com.palmcommerce.funds.trade.ibatis.bean.PaymentSerialNumberBean;
import com.palmcommerce.funds.trade.ibatis.dao.PaymentDao;

public class PaymentDaoImpl extends SqlMapClientDaoSupport implements PaymentDao {
	
	public PayMoneyBean getPayMoneyBySeriesNo(String seriesNo,String bankCode,String accounttime){
		Map<String,String> map=new HashMap<String,String>();
		map.put("seriesNo", seriesNo);
		map.put("bankCode", bankCode);
		map.put("time", accounttime);
		//this.getSqlMapClientTemplate()
		return (PayMoneyBean)getSqlMapClientTemplate().queryForObject("Payment.getpaymoneyBySeriesNo", map);
	}
	public int insertPayMoneyBean(PayMoneyBean bean){
		
		Object obj=super.getSqlMapClientTemplate().insert("Payment.insertpaymoney", bean);
		if(obj==null)
			return -1;
		return 0;
	}
	public int updatePayMoneyBean(PayMoneyBean bean) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update("Payment.updatePayMoneyBean", bean);
	}
	public AccountBankBean getAccountBankBean(String accountTime,
			String bankcode) {
		// TODO Auto-generated method stub
		Map<String,String> map=new HashMap<String,String>();
		map.put("accountTime", accountTime);
		map.put("bankcode", bankcode);
		return (AccountBankBean)getSqlMapClientTemplate().queryForObject("Payment.getAccountBankBean", map);
	}
	
	
	public int deleteAccountBankBean(String accountTime,String bankcode){
		Map<String,String> map=new HashMap<String,String>();
		map.put("accountTime", accountTime);
		map.put("bankcode", bankcode);
		return getSqlMapClientTemplate().delete("Payment.deleteAccountBankBean", map);
	}
	
	public List<PayMoneyBean> getPayMoneyList(String accountTime,String bankCode,
			String accountType) {
		// TODO Auto-generated method stub
		Map<String,String> map=new HashMap<String,String>();
		map.put("accountTime", accountTime);
		map.put("bankCode", bankCode);
		if(accountType!=null)
		   map.put("accountType", accountType);
		return getSqlMapClientTemplate().queryForList("Payment.getPayMoneyList", map);
	}
	public int insertAccountBankBean(AccountBankBean bean) {
		// TODO Auto-generated method stub
		Object obj=getSqlMapClientTemplate().insert("Payment.insertAccountBankBean", bean);
		if(obj==null)
			return -1;
		return 1;
	}
	public BankBindBean getBankBindBean(String seriesNo,String bankCode) {
		// TODO Auto-generated method stub
		Map<String,String> map=new HashMap<String,String>();
		map.put("seriesNo", seriesNo);
		map.put("bankCode", bankCode);
		return (BankBindBean)getSqlMapClientTemplate().queryForObject("Payment.getbankBindBean", map);
	}
	public int insertBankBindBean(BankBindBean bean) {
		// TODO Auto-generated method stub
		Object obj=getSqlMapClientTemplate().insert("Payment.insertbankBindBean", bean);
		if(obj==null)
			return -1;
		return 1;
	}
	public int deleteAccountDetail(String partnerId, String gameId,
			String accountTime) {
		// TODO Auto-generated method stub
		Map<String,String> map=new HashMap<String,String>();
		map.put("partnerId", partnerId);
		map.put("gameId", gameId);
		map.put("accountTime", accountTime);
		return getSqlMapClientTemplate().delete("Payment.deleteAccountDetail", map);
	}
	public PartnerAccountDetail getPartnerAccountDetail(String partnerId,
			String gameId, String accountTime) {
		// TODO Auto-generated method stub
		Map<String,String> map=new HashMap<String,String>();
		map.put("partnerId", partnerId);
		map.put("gameId", gameId);
		map.put("accountTime", accountTime);
		return (PartnerAccountDetail)getSqlMapClientTemplate().queryForObject("Payment.getPartnerAccountDetail", map);
	}
	public int insertPartnerAccountDeatil(PartnerAccountDetail detail) {
		// TODO Auto-generated method stub
		
		Object obj=getSqlMapClientTemplate().insert("Payment.insertPartnerAccountDeatil", detail);
		if(obj==null)
			return -1;
		return 1;
	}
	public PayMoneyBean getPayMoneyByCancelSeriesNo(String cancelSerialNo,
			String bankCode, String accounttime) {
		// TODO Auto-generated method stub
		Map<String,String> map=new HashMap<String,String>();
		map.put("cancelSerialNo", cancelSerialNo);
		map.put("bankCode", bankCode);
		map.put("time", accounttime);
		//this.getSqlMapClientTemplate()
		return (PayMoneyBean)getSqlMapClientTemplate().queryForObject("Payment.getPaymoneyByCancelSeriesNo", map);
	}
	public int insertPaymentSerialNumber(String bankCode,
			String paymentSerialNumber,String datetime) {
		// TODO Auto-generated method stub
		int ret=0;
		PaymentSerialNumberBean bean=new PaymentSerialNumberBean();
		bean.setBankCode(bankCode);
		bean.setPaymentDatetime(datetime);
		bean.setPaymentSerialNumber(paymentSerialNumber);
		Object obj=null;
		try {
			obj = getSqlMapClientTemplate().insert("Payment.insertPaymentSerialNumber",bean);
			ret=1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret=-1;
		}
		
		return ret;
	}
	public BankBindBean getBankBindByUserId(String userId, String bankCode) {
		// TODO Auto-generated method stub
		Map<String,String> map=new HashMap<String,String>();
		map.put("userId", userId);
		map.put("bankCode", bankCode);
		return (BankBindBean)getSqlMapClientTemplate().queryForObject("Payment.getBankBindByUserId", map);
	}
	public void updateBankBindBean(BankBindBean bean) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("Payment.updateBankBindBean", bean);
		
	}

}
