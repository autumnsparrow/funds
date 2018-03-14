/**
 * 
 */
package com.palmcommerce.funds.alert.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.palmcommerce.funds.roo.model.TransactionRecord;

/**
 * @author Administrator
 *  
 */
public class TradeRecordStatics {
	String userid;
	String bankId;
	String tradeId;
	String tradeName;
	public int drawNumbers;
	public long drawAmounts;
	public int chargeNumbers;
	public long chargeAmounts;
	
	public long createTime;
	
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	
	
	public TradeRecordStatics(TransactionRecord r,String key){
		String[]  entries=key.split("_");
		this.userid=r.getUserId();
		this.bankId=entries[1];//r.getBankId();
		this.tradeId=entries[2];//r.getTerraceId();
		this.tradeName=r.getUserName();
		this.createTime=System.currentTimeMillis();
		this.chargeAmounts=0;
		this.chargeNumbers=0;
		this.drawAmounts=0;
		this.drawNumbers=0;
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getBankId() {
		return bankId;
	}
	
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public int getDrawNumbers() {
		return drawNumbers;
	}
	public void setDrawNumbers(int drawNumbers) {
		this.drawNumbers = drawNumbers;
	}
	public long getDrawAmounts() {
		return drawAmounts;
	}
	public void setDrawAmounts(long drawAmounts) {
		this.drawAmounts = drawAmounts;
	}
	public int getChargeNumbers() {
		return chargeNumbers;
	}
	public void setChargeNumbers(int chargeNumbers) {
		this.chargeNumbers = chargeNumbers;
	}
	public long getChargeAmounts() {
		return chargeAmounts;
	}
	public void setChargeAmounts(long chargeAmounts) {
		this.chargeAmounts = chargeAmounts;
	}
	
	private static final DateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat f=new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * @return
	 */
	public boolean validate(){
		Date createDate=new Date(createTime);
		String createDateString=f.format(createDate);
		Date currentDate=new Date(System.currentTimeMillis());
		String currentDateString=f.format(currentDate);
		return createDateString.equalsIgnoreCase(currentDateString);
		
	}
}
