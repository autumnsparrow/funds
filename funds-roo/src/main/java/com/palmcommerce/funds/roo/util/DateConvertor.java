package com.palmcommerce.funds.roo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import com.palmcommerce.funds.service.ProtocolStorageException;

public class DateConvertor {

	public DateConvertor() {
		// TODO Auto-generated constructor stub
	}
	public static final String formatString="yyyy-MM-dd HH:mm:ss";
	private static final SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat format2=new SimpleDateFormat("yyyyMMdd");
	
	public static String getTradeTimeByDate(Date t){
		return format.format(t);
		
	}
	
	public static String getBankTimeByDate(Date t){
		return format2.format(t);
	}
	
	public static String getTradeTime(){
		return getTradeTimeByDate(new Date());
		
	}
	
	public static String getBankTime(){
		return getBankTimeByDate(new Date());
	}
	
	/**
	 * 
	 * 
	 * @param tradeTime
	 * @return
	 * @throws ProtocolStorageException
	 */
	public static Date parserTradeTime(String tradeTime) throws ProtocolStorageException {
		Date d;
		try {
			d=DateUtils.parseDate(tradeTime, "yyyy-MM-dd HH:mm:ss");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ProtocolStorageException("900001", "date time format incorrect");
		}
		
		return d;
	}
	
	public static Date parseBankTime(String tradeTime) throws ProtocolStorageException {
		Date d;
		try {
			d=DateUtils.parseDate(tradeTime, "yyyyMMdd");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ProtocolStorageException("900001", "date time format incorrect");
		}
		
		return d;
	}
	
	
	public static void main(String args[])throws Exception{
		
		String accountDate="20140212";
		
		String bankDate=DateConvertor.getBankTimeByDate(new Date());
		Date myDate=DateConvertor.parseBankTime(bankDate);
		Date accountDatetime=DateConvertor.parseBankTime(accountDate);
		long times=accountDatetime.getTime()-myDate.getTime();
		int prevDays=(int)times/(24*60*60*1000);
		System.out.println(" prevDays:"+prevDays);
		
		
	}

}
