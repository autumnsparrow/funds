package com.palmcommerce.funds.trade.ibatis.dao;

import java.util.List;

import com.palmcommerce.funds.trade.ibatis.bean.AccountBankBean;
import com.palmcommerce.funds.trade.ibatis.bean.BankBindBean;
import com.palmcommerce.funds.trade.ibatis.bean.PartnerAccountDetail;
import com.palmcommerce.funds.trade.ibatis.bean.PayMoneyBean;

public interface PaymentDao {

	public PayMoneyBean getPayMoneyBySeriesNo(String seriesNo,String bankCode,String accounttime);
	public PayMoneyBean getPayMoneyByCancelSeriesNo(String cancelSerialNo,String bankCode,String accounttime);
	public int insertPayMoneyBean(PayMoneyBean bean);
	
	public int updatePayMoneyBean(PayMoneyBean bean);
	
    public AccountBankBean getAccountBankBean(String accountTime,String bankcode);
    
    public int deleteAccountBankBean(String accountTime,String bankcode);
	
	public List<PayMoneyBean> getPayMoneyList(String accountTime,String bankCode,String accountType);
	
	public int insertAccountBankBean(AccountBankBean bean);
	
	public int insertBankBindBean(BankBindBean bean);
	
	public BankBindBean getBankBindBean(String seriesNo,String bankCode);
	
	
	public PartnerAccountDetail getPartnerAccountDetail(String partnerId,String gameId,String accountTime);
	
	public int  deleteAccountDetail(String partnerId,String gameId,String accountTime);
	
	public int insertPartnerAccountDeatil(PartnerAccountDetail detail);
	public int insertPaymentSerialNumber(String bankCode,
			String paymentSerialNumber, String datetime);
	public BankBindBean getBankBindByUserId(String userId, String bankCode);
	public void updateBankBindBean(BankBindBean bean);
}
