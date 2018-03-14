/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 28, 2013
 *
 */
package com.palmcommerce.funds.configuration.v2;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author sparrow
 *
 */
public class FundsScheduledExecutorService {

	ScheduledExecutorService scheduledExecutorService;

	/**
	 * @return the scheduledExecutorService
	 */
	public ScheduledExecutorService getScheduledExecutorService() {
		return scheduledExecutorService;
	}

	/**
	 * @param scheduledExecutorService the scheduledExecutorService to set
	 */
	public void setScheduledExecutorService(
			ScheduledExecutorService scheduledExecutorService) {
		this.scheduledExecutorService = scheduledExecutorService;
	}

	public FundsScheduledExecutorService() {
		super();
		// TODO Auto-generated constructor stub
		this.scheduledExecutorService=Executors.newScheduledThreadPool(1);
	}
	
	

}
