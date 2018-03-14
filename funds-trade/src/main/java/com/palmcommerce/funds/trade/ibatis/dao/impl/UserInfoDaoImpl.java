package com.palmcommerce.funds.trade.ibatis.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


import com.palmcommerce.funds.trade.ibatis.bean.BankBean;
import com.palmcommerce.funds.trade.ibatis.bean.UserAccount;
import com.palmcommerce.funds.trade.ibatis.bean.UserAccountLogBean;
import com.palmcommerce.funds.trade.ibatis.bean.UserInfo;
import com.palmcommerce.funds.trade.ibatis.dao.UserInfoDao;

public class UserInfoDaoImpl  extends SqlMapClientDaoSupport implements UserInfoDao{

	public UserInfo getUser(String userId) {
		// TODO Auto-generated method stub
		UserInfo userinfo=null;
		userinfo=(UserInfo) getSqlMapClientTemplate().queryForObject("UserInfo.getUserInfo", userId);
		
		return userinfo;
	}

	public UserInfo getUserAccount(String userId) {
		// TODO Auto-generated method stub
		UserInfo userinfo=null;
		userinfo=(UserInfo) this.getSqlMapClientTemplate().queryForObject("UserInfo.getUserAccount", userId);
		
		return userinfo;
	}

	public int updateBanknumber(String userId, String bankNumber) {
		// TODO Auto-generated method stub
		Map<String,String> map=new HashMap<String,String>();
		map.put("userId", userId);
		map.put("bankNumber", bankNumber);
		int i= super.getSqlMapClientTemplate().update("UserInfo.updateBanknumber", map);
		//在SQL-MAP中没有该方法，数据库中没有特定的字段
//		if(i>0){
//			i=super.getSqlMapClientTemplate().update("UserInfo.updateUserInfoBankAccount", map);
//		}
		
		return i;
	}

	public int updateMoney(String userId, double money) {
		// TODO Auto-generated method stub
		Map<String,String> map=new HashMap<String,String>();
		map.put("userId", userId); 
		map.put("money", String.valueOf(money));
		
	//	map.put("updateTime",com.palmcommerce.funds.util.DateConvertor.getTradeTimeByDate(new Date()) );
		return getSqlMapClientTemplate().update("UserInfo.updateMoney", map);
	
	}

	public int insertUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		Object obj= getSqlMapClientTemplate().insert("UserInfo.insertUserInfo",userInfo);
		int row=0;
		if(obj!=null)
			row=1;
		
		return row;
		
	}
	
	public int insertUserAccount(UserAccount userAccount){
		
		Object obj= getSqlMapClientTemplate().insert("UserInfo.insertUserAccount",userAccount);
		int row=0;
		if(obj!=null)
			row=1;
		return row;
		
	}

	@SuppressWarnings("unchecked")
	public List<BankBean> getBankAll() {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("UserInfo.getBankAll");
	}

	public int insertUserAccountLog(UserAccountLogBean userAccount) {
		// TODO Auto-generated method stub
		Object obj= getSqlMapClientTemplate().insert("UserInfo.insertUserAccountLog",userAccount);
		int row=0;
		if(obj!=null)
			row=1;
		return row;
	}

}
