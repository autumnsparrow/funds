/**
 * 
 */
package com.palmcommerce.funds.alert.model;

import java.util.concurrent.ConcurrentHashMap;

import com.palmcommerce.funds.roo.util.ListEntryConvertor;
import com.palmcommerce.oss.jpa.model.OssTradeAmountControl;
import com.palmcommerce.oss.jpa.model.OssWithdrawStandard;
import com.palmcommerce.oss.jpa.service.OssTradeAmountControlService;
import com.palmcommerce.oss.jpa.service.OssWithdrawStandardService;

/**
 * @author Administrator
 *   
 */
public class AlertStandardControlManager {
	

	OssTradeAmountControlService ossTradeAmountControlService;
	OssWithdrawStandardService  ossWithdrawStandardService;
	
	public OssTradeAmountControlService getOssTradeAmountControlService() {
		return ossTradeAmountControlService;
	}
	public void setOssTradeAmountControlService(
			OssTradeAmountControlService ossTradeAmountControlService) {
		this.ossTradeAmountControlService = ossTradeAmountControlService;
	}
	public OssWithdrawStandardService getOssWithdrawStandardService() {
		return ossWithdrawStandardService;
	}
	public void setOssWithdrawStandardService(
			OssWithdrawStandardService ossWithdrawStandardService) {
		this.ossWithdrawStandardService = ossWithdrawStandardService;
	}
	
	
	
	private final ConcurrentHashMap<String,AlertStandardControl> mapOfStandardControl=new ConcurrentHashMap<String,AlertStandardControl>();
	
	public  String getStoredKey(TradeRecordStatics entry){
		return String.format("%s_%s", entry.bankId, entry.tradeId);
	}
	public AlertStandardControl getStandardControl(TradeRecordStatics entry){
		String key=getStoredKey(entry);
		AlertStandardControl control=null;
		if(!mapOfStandardControl.contains(key)){
			mapOfStandardControl.put(key, getAlertStandardControl(entry));
		}
		return mapOfStandardControl.get(key);
	}
	
	private AlertStandardControl getAlertStandardControl(TradeRecordStatics entry){
		OssWithdrawStandard withdrawStandard=ListEntryConvertor.getOneEntry(ossWithdrawStandardService.findOssWithdrawStandardsByPlatformIdEquals(entry.tradeId));
		OssTradeAmountControl amountStandard=ListEntryConvertor.getOneEntry( ossTradeAmountControlService.findOssTradeAmountControlsByPlatformIdEqualsOrBankIdEquals(entry.tradeId, entry.bankId));
		
		return new AlertStandardControl(amountStandard, withdrawStandard);
	}
	

}
