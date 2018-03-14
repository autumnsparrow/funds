/**
 * 
 */
package com.palmcommerce.funds.trade.ibatis.bean;

import java.io.Serializable;

/**
 * @author sparrow
 *
 */
public class BankBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9195592028867345201L;
	String bankId;
	String bankFullName;
	String bankShortName;
	String remark;
	
	

	public String getBankId() {
		return bankId;
	}



	@Override
	public String toString() {
		return "BankBean [bankId=" + bankId + ", bankFullName=" + bankFullName
				+ ", bankShortName=" + bankShortName + ", remark=" + remark
				+ "]";
	}



	public void setBankId(String bankId) {
		this.bankId = bankId;
	}



	public String getBankFullName() {
		return bankFullName;
	}



	public void setBankFullName(String bankFullName) {
		this.bankFullName = bankFullName;
	}



	public String getBankShortName() {
		return bankShortName;
	}



	public void setBankShortName(String bankShortName) {
		this.bankShortName = bankShortName;
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	/**
	 * 
	 */
	public BankBean() {
		// TODO Auto-generated constructor stub
	}

}
