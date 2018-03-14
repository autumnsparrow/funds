/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Nov 12, 2013
 *
 */
package com.palmcommerce.funds.trade.ibatis.impl;

/**
 * @author sparrow
 *
 *
 *
 *public static int ACCOUNT_TYPE_NO=0;//未对仗
	public static int ACCOUNT_TYPE_YES=1;//对仗成功
	public static int ACCOUNT_TYPE_OVER=2;//多出笔数
	public static int ACCOUNT_TYPE_LACK=3;//缺少笔数
	public static int ACCOUNT_TYPE_ERROR=4;//对仗错误
	public static int ACCOUNT_TYPE_ERROR_OLD=5;//老综合接入的数据
	public static int ACCOUNT_TYPE_CALLBACK=-1;//冲正

 */
public enum UserBankAccountState {


	UNBALANCE(0),CORRECTED(1),WRONG(4),LESS(3),MORE(2),CALLBACK(-1);

	
	final int state;

	private UserBankAccountState(int state) {
		this.state = state;
	}

	/**
	 * @return the state
	 */
	public int getState() {
		return this.state;
	}
	
	
	
}
