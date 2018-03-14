package com.palmcommerce.server.v29.service;

import com.palmcommerce.server.v29.db.UserAccountLog100;
import com.palmcommerce.server.v29.db.UserAccountLog101;
import com.palmcommerce.server.v29.db.UserAccountLog102;
import com.palmcommerce.server.v29.db.UserAccountLog103;
import com.palmcommerce.server.v29.db.UserAccountLog104;
import com.palmcommerce.server.v29.db.UserAccountLog105;
import com.palmcommerce.server.v29.db.UserAccountLog106;
import com.palmcommerce.server.v29.db.UserAccountLog107;
import com.palmcommerce.server.v29.db.UserAccountLog108;
import com.palmcommerce.server.v29.db.UserAccountLog109;

public class UserAccountLogServiceImpl implements UserAccountLogService {

	private static final Class cls[]= {
		com.palmcommerce.server.v29.db.UserAccountLog100.class,
		com.palmcommerce.server.v29.db.UserAccountLog101.class,
		com.palmcommerce.server.v29.db.UserAccountLog102.class,
		com.palmcommerce.server.v29.db.UserAccountLog103.class,
		com.palmcommerce.server.v29.db.UserAccountLog104.class,
		com.palmcommerce.server.v29.db.UserAccountLog105.class,
		com.palmcommerce.server.v29.db.UserAccountLog106.class,
		com.palmcommerce.server.v29.db.UserAccountLog107.class,
		com.palmcommerce.server.v29.db.UserAccountLog108.class,
		com.palmcommerce.server.v29.db.UserAccountLog109.class};
	
	@Override
	public <T> T findUserAccountLogByForigenIdEquals(String userId,
			String forigenId) {
		// TODO Auto-generated method stub
		T t=null;
		if (userId == null)
			return null;
		char index = userId.charAt(userId.length() - 1);

		switch (index) {
		case '0':{
			t=(T)UserAccountLog100.findUserAccountLog100sByForigenIdEquals(forigenId).getSingleResult();
		}

			break;
		case '1':
		{
			t=(T)UserAccountLog101.findUserAccountLog101sByForigenIdEquals(forigenId).getSingleResult();;
		}
			break;

		case '2':
		{
			t=(T)UserAccountLog102.findUserAccountLog102sByForigenIdEquals(forigenId).getSingleResult();;
		}
			break;

		case '3':
		{
			t=(T)UserAccountLog103.findUserAccountLog103sByForigenIdEquals(forigenId).getSingleResult();;
		}
			break;

		case '4':
		{
			t=(T)UserAccountLog104.findUserAccountLog104sByForigenIdEquals(forigenId).getSingleResult();;
		}
			break;

		case '5':
		{
			t=(T)UserAccountLog105.findUserAccountLog105sByForigenIdEquals(forigenId).getSingleResult();;
		}
			break;

		case '6':
		{
			t=(T)UserAccountLog106.findUserAccountLog106sByForigenIdEquals(forigenId).getSingleResult();;
		}
			break;

		case '7':
		{
			t=(T)UserAccountLog107.findUserAccountLog107sByForigenIdEquals(forigenId).getSingleResult();;
		}
			break;

		case '8':
		{
			t=(T)UserAccountLog108.findUserAccountLog108sByForigenIdEquals(forigenId).getSingleResult();;
		}
			break;
		case '9':
		{
			t=(T)UserAccountLog109.findUserAccountLog109sByForigenIdEquals(forigenId).getSingleResult();;
		}
			break;

		default:
			break;
		}
		return t;
	}

	
	
	
	@Override
	public void saveUserAccountLog(Object t) {
		// TODO Auto-generated method stub
		if(t instanceof UserAccountLog100){
			((UserAccountLog100)t).persist();
		}else if (t instanceof UserAccountLog101){
			((UserAccountLog101)t).persist();
		}else if(t instanceof UserAccountLog102){
			((UserAccountLog102)t).persist();
		}else if (t instanceof UserAccountLog103){
			((UserAccountLog103)t).persist();
		} else if(t instanceof UserAccountLog104){
			((UserAccountLog104)t).persist();
		}else if (t instanceof UserAccountLog105){
			((UserAccountLog105)t).persist();
		} else if(t instanceof UserAccountLog106){
			((UserAccountLog106)t).persist();
		}else if (t instanceof UserAccountLog107){
			((UserAccountLog107)t).persist();
		} else if(t instanceof UserAccountLog108){
			((UserAccountLog108)t).persist();
		}else if (t instanceof UserAccountLog109){
			((UserAccountLog109)t).persist();
		} 
		
	}

	@Override
	public Object newUserAccount(String userId) {
		// TODO Auto-generated method stub
		if (userId == null)
			return null;
		char index = userId.charAt(userId.length() - 1);
		int i=index-'0';
		Object obj=null;
		
		try {
			obj= (Object)cls[i].newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
	
	
	
}
