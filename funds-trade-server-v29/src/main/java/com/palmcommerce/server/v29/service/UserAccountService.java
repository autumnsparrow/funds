package com.palmcommerce.server.v29.service;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.palmcommerce.server.v29.db.UserAccount;

@RooService(domainTypes = { com.palmcommerce.server.v29.db.UserAccount.class })
public interface UserAccountService {
	public List<UserAccount> findUserAccountByUserId(String userId);
	public int deleteUserAccoutByUserId(String userId);

}
