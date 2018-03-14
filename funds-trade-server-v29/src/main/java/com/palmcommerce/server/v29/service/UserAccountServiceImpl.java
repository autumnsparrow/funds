package com.palmcommerce.server.v29.service;

import java.util.List;

import javax.persistence.Query;

import com.palmcommerce.server.v29.db.UserAccount;


public class UserAccountServiceImpl implements UserAccountService {

	@Override
	public List<UserAccount> findUserAccountByUserId(String userId) {
		// TODO Auto-generated method stub
		return UserAccount.findUserAccountsByUserIdEquals(userId).getResultList();
	}

	@Override
	public int deleteUserAccoutByUserId(String userId) {
		// TODO Auto-generated method stub
		Query q=UserAccount.entityManager().createNativeQuery("delete o from UserAccount o where o.userId=:userId", UserAccount.class);
		q.setParameter("userId", userId);
		
		return q.executeUpdate();
	}
	
	
}
