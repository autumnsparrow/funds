package com.palmcommerce.funds.trade.ibatis.impl;

public interface IReconciliationJmx {
	
	public void reconciliation(String date);
	public String getReconciliationDate();
	public void changeReconciliationDate(String d);
	public void cleanStates();
	public void reconciliationByDate(String filename,String bankdate);
}
