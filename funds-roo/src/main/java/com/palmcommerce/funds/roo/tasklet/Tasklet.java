/**
 * 
 */
package com.palmcommerce.funds.roo.tasklet;

/**
 * @author lottery
 */
public abstract class Tasklet implements Runnable {
	String fileBase;
	
	public static final String receive_dir="received";
	public static final String sending_dir="sending";

	/**
	 * 
	 */
	public Tasklet() {
		// TODO Auto-generated constructor stub  自动生成的构造函数存根
	}

	
	public abstract void run();

	public String getFileBase() {
		return fileBase;
	}
	public void setFileBase(String fileBase) {
		this.fileBase = fileBase;
	}
}
