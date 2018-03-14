package com.palmcommerce.funds.trade.ibatis.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.palmcommerce.funds.trade.ibatis.bean.BankBean;
import com.palmcommerce.funds.trade.ibatis.bean.UserAccount;
import com.palmcommerce.funds.trade.ibatis.bean.UserInfo;
import com.palmcommerce.funds.trade.ibatis.dao.UserInfoDao;
import com.palmcommerce.funds.trade.ibatis.services.UserInfoService;

public class UserInfoServiceImpl implements UserInfoService{
	
	@Autowired
	private UserInfoDao userInfoDao;
	/**
	 * 获取用户信息
	 * @param userId
	 * @return
	 */
	public UserInfo getUser(String userId){
		UserInfo userinfo=this.userInfoDao.getUser(userId);
		UserInfo info=this.userInfoDao.getUserAccount(userId);
		if(info!=null)
			userinfo.setAccountMoney(info.getAccountMoney());
		
		return userinfo;
	}
	public UserInfoDao getUserInfoDao() {
		return userInfoDao;
	}
	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}
	public int insertUser(UserInfo userInfo) {
		// TODO Auto-generated method stub
//		HashMap<String,String> map=new HashMap<String,String>();
//		map.put("realName", userInfo.getRealName());
//		map.put("idType", userInfo.getIdType());
//		map.put("idNo", userInfo.getIdNo());
//		map.put("telephone", userInfo.getPhoneNo());
//		map.put("bankAccount", userInfo.getBankAccount());
//		map.put("createTime", SerialGenerator.getTransactionDatetime());
//		
		
		
		int i= this.userInfoDao.insertUserInfo(userInfo);
		if(i>0){
			UserAccount userAccount=new UserAccount();
			userAccount.setUserId(userInfo.getUserId());
			userAccount.setStatus(1);
			userAccount.setType(0);
			userAccount.setCash(0.0);
			userAccount.setCreditMoney(0.0);
			userAccount.setEffiectiveTime(new Date());
			userAccount.setFreezeCash(0.0);
			userAccount.setgGreditMoney(0.0);
			userAccount.setGift(0.0);
			userAccount.setStatus(1);
			userAccount.setType(1);
			userAccount.setUpdateTime(new Date());
			i=this.userInfoDao.insertUserAccount(userAccount);
		}
		
		return i;
		
	}
	public List<BankBean> getBankAll() {
		// TODO Auto-generated method stub
		return this.userInfoDao.getBankAll();
	}
	
	
}
