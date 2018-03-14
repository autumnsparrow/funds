package com.palmcommerce.funds.roo.tasklet.schedule;

public interface IScheduledTaskJMX {
	/**
	 * 
	 * INVALID=-1;ACTIVING=0;RECEIVING=1;PROCESSING=2;SENDING=3;TIMEOUT=4;FINISHED=5;ACTIVE_WAITING=6;
	 * 
	 * change state=0
	 * Banks: 
	 * bank A,
	 * bank B.
	 * Date:
	 * bankdate D.
	 * Trades:
	 * Trade E,
	 * Trade F.
	 * equals:
	 * ACTIVING,ACTIVE_WAITING,RECEIVING
	 * 1. sendReconciliationRequest(A,D)
	 * 2. sendReconciliationRequest(B,D)
	 * 
	 * PROCESSING
	 * 3, reconciliationBank(A,file of A,D)
	 * 4, reconciliationBank(B,file of B,D)
	 * 
	 * SENDING
	 * 5, reconciliationTrade(E,D)
	 * 6, reconciliationTrade(F,D)
	 * 
	 * FINISHED.
	 * 
	 * 
	 * 
	 * 
	 * @param state
	 */
	public void changeState(int state);
	/*
	 * 
	 */
	public String getScheduledState();
	/*
	 * 
	 */
	public  void onCleanup();
	
	public void changePreviousDays(int days);
	
	public String getAccountDate();
	
	public void sendReconciliationRequest(String bankcode,String date);
	public void reconciliationBank(String bankcode,String filename,String date);
	
	public void loadRouteRule();

	public  void reconciliationTrade(String tradecode,String bankdate);
	
	/**
	 * reconciliation the account date.
	 * 
	 * equals.
	 * want to reconciliation 20140209
	 * 
	 * 1. get current date  like :20140213
	 * 2. calculate the prevdays 20140209-20140213  = -4
	 * 3. invoke chnagePreviousDays(-4)
	 * 4. invoke onCleanup()
	 * 5. changeState(0)
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param accountDate
	 */
	public void reconciliation(String accountDate);
}
