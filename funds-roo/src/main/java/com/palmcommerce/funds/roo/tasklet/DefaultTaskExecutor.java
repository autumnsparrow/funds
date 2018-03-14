/**
 * 
 */
package com.palmcommerce.funds.roo.tasklet;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author lottery
 * 
 */
public class DefaultTaskExecutor implements ITaskExecutor {
	
	ExecutorService  executors;
	int threadNumber;
	String  fileBase;
	
	public String getFileBase() {
		return fileBase;
	}
	public void setFileBase(String fileBase) {
		this.fileBase = fileBase;
	}

	/**
	 * 默认的任务执行
	 */
	public DefaultTaskExecutor(int threadNumber) {
		// TODO Auto-generated constructor stub
		executors=Executors.newFixedThreadPool(threadNumber);
	}
	
	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.tasklet.ITaskExecutor#execute(java.lang.Runnable)
	 */
	@Override
	public void execute(Tasklet task) {
		// TODO Auto-generated method stub 自动生成的方法存根
		task.setFileBase(getFileBase());
		this.executors.execute(task);
	}
}
