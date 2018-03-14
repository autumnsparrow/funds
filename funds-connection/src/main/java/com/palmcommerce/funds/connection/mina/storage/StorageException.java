/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Sep 5, 2013-11:49:59 AM
 *
 */
package com.palmcommerce.funds.connection.mina.storage;

/**
 * @author sparrow
 *
 */
public class StorageException extends Exception {
	
	public String state;
	public String reason;

	/**
	 * 
	 */
	public StorageException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public StorageException(String state,String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
		this.state=state;
		this.reason=arg0;
	}

	/**
	 * @param arg0
	 */
	public StorageException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public StorageException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
