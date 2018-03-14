/**
 * 
 */
package com.palmcommerce.funds.roo.tasklet.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.palmcommerce.funds.alert.service.IAlertManager;
import com.palmcommerce.funds.roo.model.TransactionMeta;
import com.palmcommerce.funds.roo.tasklet.Tasklet;

/**
 * @author Administrator
 *
 */
public class AlertMessageTask extends Tasklet {
	TransactionMeta meta;
	
	public TransactionMeta getMeta() {
		return meta;
	}

	public void setMeta(TransactionMeta meta) {
		this.meta = meta;
	}
	/**
	 * 
	 */
	public AlertMessageTask() {
		// TODO Auto-generated constructor stub
	}
	public AlertMessageTask(TransactionMeta meta) {
		super();
		this.meta = meta;
	}

	
	public AlertMessageTask(TransactionMeta meta, IAlertManager alertManager) {
		super();
		this.meta = meta;
		this.alertManager = alertManager;
	}


	IAlertManager alertManager;

	/**
	 * @return the alertManager
	 */
	public IAlertManager getAlertManager() {
		return alertManager;
	}

	/**
	 * @param alertManager the alertManager to set
	 */
	public void setAlertManager(IAlertManager alertManager) {
		this.alertManager = alertManager;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.tasklet.Tasklet#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//alertManager.loadDefaultTradeRecord();
		alertManager.notifyTradeRecord(meta);

	}
}
