package com.palmcommerce.server.v29.service;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = {
		com.palmcommerce.server.v29.db.UserAccountLog100.class,
		com.palmcommerce.server.v29.db.UserAccountLog101.class,
		com.palmcommerce.server.v29.db.UserAccountLog102.class,
		com.palmcommerce.server.v29.db.UserAccountLog103.class,
		com.palmcommerce.server.v29.db.UserAccountLog104.class,
		com.palmcommerce.server.v29.db.UserAccountLog105.class,
		com.palmcommerce.server.v29.db.UserAccountLog106.class,
		com.palmcommerce.server.v29.db.UserAccountLog107.class,
		com.palmcommerce.server.v29.db.UserAccountLog108.class,
		com.palmcommerce.server.v29.db.UserAccountLog109.class})
public interface UserAccountLogService {
	
		public <T> T findUserAccountLogByForigenIdEquals(String userId,String forigenId);

		public  void saveUserAccountLog(Object t);
		
		public Object newUserAccount(String userId);
}
