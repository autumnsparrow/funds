/**
 * 
 */
package com.palmcommerce.funds.roo.tasklet;

/**
 * @author lottery
 */
public interface ITaskExecutor {
	
	public void execute(Tasklet task);
	
	public String getFileBase();
}
