package com.palmcommerce.funds.trade.ibatis.services;

import java.util.List;

import com.palmcommerce.funds.trade.ibatis.bean.BankBean;
import com.palmcommerce.funds.trade.ibatis.bean.UserInfo;

public interface UserInfoService {

	/**
	 * 获取用户信息
	 * @param userId
	 * @return
	 */
	public UserInfo getUser(String userId);
	
	
	public int insertUser(UserInfo userInfo);
	
	
	public List<BankBean> getBankAll();
}
