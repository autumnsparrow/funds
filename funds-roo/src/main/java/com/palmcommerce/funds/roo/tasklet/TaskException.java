/**
 * 
 */
package com.palmcommerce.funds.roo.tasklet;

/**
 * @author lottery
 *
 */
public class TaskException extends Exception {
	
	public static final  int EXCEPTION_ENTRIES_EMPTY=00001;
	public static final  int EXCEPTION_FILENAME_EMPTY=00002;
	public static final  int EXCEPTION_FILE_EMPTY=00003;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int code;

	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @param message
	 */
	public TaskException(int code,String message) {
		super(message);
		// TODO Auto-generated constructor stub
		this.code=code;
	}

	/**
	 * @param cause
	 */
	public TaskException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public TaskException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
//	public TaskException(String message, Throwable cause,
//			boolean enableSuppression, boolean writableStackTrace) {
//		super(message, cause, enableSuppression, writableStackTrace);
//		// TODO Auto-generated constructor stub
//	}

}
