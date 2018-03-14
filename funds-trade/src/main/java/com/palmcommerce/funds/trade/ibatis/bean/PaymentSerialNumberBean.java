/**
 * 
 */
package com.palmcommerce.funds.trade.ibatis.bean;

import java.io.Serializable;

/**
 * @author sparrow
 *
 */
public class PaymentSerialNumberBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 92373739387365477L;
	private String bankCode;
	private String paymentSerialNumber;
	private String paymentDatetime;
	public String getPaymentDatetime() {
		return paymentDatetime;
	}
	public void setPaymentDatetime(String paymentDatetime) {
		this.paymentDatetime = paymentDatetime;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getPaymentSerialNumber() {
		return paymentSerialNumber;
	}
	public void setPaymentSerialNumber(String paymentSerialNumber) {
		this.paymentSerialNumber = paymentSerialNumber;
	}

}
