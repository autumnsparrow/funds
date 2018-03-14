package com.palmcommerce.funds.trade.ibatis.dao;

import java.util.List;

import com.palmcommerce.funds.trade.ibatis.bean.BankBean;
import com.palmcommerce.funds.trade.ibatis.bean.UserAccount;
import com.palmcommerce.funds.trade.ibatis.bean.UserAccountLogBean;
import com.palmcommerce.funds.trade.ibatis.bean.UserInfo;



public interface UserInfoDao {

	/**
	 * 获取用户信息
	 * @param userId
	 * @return
	 */
	public UserInfo getUser(String userId);
	
	public UserInfo getUserAccount(String userId);
	
	public int updateMoney(String userId,double money);
	
	public int updateBanknumber(String userId,String bankNumber);
	//public int updateUserInfoBankAccount(String userId,String bankNumber);
	
	public int insertUserInfo(UserInfo userInfo);
	
	public int insertUserAccount(UserAccount userAccount);
	
	public List<BankBean> getBankAll();
	
	public int insertUserAccountLog(UserAccountLogBean userAccount);
}
