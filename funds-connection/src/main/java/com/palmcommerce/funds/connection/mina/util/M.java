/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Nov 3, 2013-9:37:22 PM
 *
 */
package com.palmcommerce.funds.connection.mina.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * @author sparrow
 *
 */
public class M {
	private static final Log logger=LogFactory.getLog(M.class);

	/**
	 * 
	 */
	public M() {
		// TODO Auto-generated constructor stub
	}
	
	String tag;
	long t;
	

	public M(String tag) {
		super();
		t=System.currentTimeMillis();
		this.tag=tag;
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String message="Mectrics [tag=" + tag + ", t=" + (System.currentTimeMillis()-t) + "]";
		logger.debug(message);
		return message;
	}
	
	
	public static M m(String tag){
		return new M(tag);
	}
	

}
