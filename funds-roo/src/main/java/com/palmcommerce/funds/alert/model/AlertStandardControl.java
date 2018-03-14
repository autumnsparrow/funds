/**
 * 
 */
package com.palmcommerce.funds.alert.model;

import com.palmcommerce.oss.jpa.model.OssTradeAmountControl;
import com.palmcommerce.oss.jpa.model.OssWithdrawStandard;

/**
 * @author Administrator
 *  
 */
public class AlertStandardControl {
	
	OssTradeAmountControl amountControl;
	OssWithdrawStandard	  withdrawStard;
	
	public AlertStandardControl(OssTradeAmountControl amountControl,
			OssWithdrawStandard withdrawStard) {
		super();
		this.amountControl = amountControl;
		this.withdrawStard = withdrawStard;
	}


	public OssTradeAmountControl getAmountControl() {
		return amountControl;
	}
	public void setAmountControl(OssTradeAmountControl amountControl) {
		this.amountControl = amountControl;
	}
	public OssWithdrawStandard getWithdrawStard() {
		return withdrawStard;
	}
	public void setWithdrawStard(OssWithdrawStandard withdrawStard) {
		this.withdrawStard = withdrawStard;
	}
}
