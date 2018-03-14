/**
 * 
 */
package com.palmcommerce.funds.roo.util;

import java.util.List;

/**
 * @author lottery
 *
 */
public class ListEntryConvertor {

	
	public static <T> T getOneEntry(List<T> list){
		T t=null;
		if(list!=null&&list.size()>0){
			t=list.get(0);
		}
		return t;
	}
}
