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
public class RemoteStorageException extends Exception {
	
	String state;

	/**
	 * 
	 */
	public RemoteStorageException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public RemoteStorageException(String state,String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
		this.state=state;
	}

	/**
	 * @param arg0
	 */
	public RemoteStorageException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public RemoteStorageException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
