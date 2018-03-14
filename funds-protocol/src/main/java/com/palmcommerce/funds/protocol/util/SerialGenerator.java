/**
 * 
 */
package com.palmcommerce.funds.protocol.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.id.serial.PrefixedLeftPaddedNumericGenerator;
import org.apache.commons.id.serial.TimeBasedAlphanumericIdentifierGenerator;

/**
 * @author sparrow
 *
 */
public class SerialGenerator {

	/**
	 * 
	 */
	public SerialGenerator() {
		// TODO Auto-generated constructor stub
	}
	
	
	private static final TimeBasedAlphanumericIdentifierGenerator generator=new TimeBasedAlphanumericIdentifierGenerator(0, 1);
	public static synchronized String generate(){
		 return generator.nextStringIdentifier();
	}
	static final PrefixedLeftPaddedNumericGenerator numberGenerator=new PrefixedLeftPaddedNumericGenerator("UU", true, 15);
	public static synchronized String generateUserId(){
		return numberGenerator.nextStringIdentifier();
	}
	
	static final PrefixedLeftPaddedNumericGenerator numberGenerator1=new PrefixedLeftPaddedNumericGenerator("U", true, 8);
	public static synchronized String generateUserName(){
		return numberGenerator1.nextStringIdentifier();
	}
	
	final static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	final static DateFormat formatter2 = new SimpleDateFormat("yyyyMMdd");
	final static DateFormat formatter3 = new SimpleDateFormat("yyyyMMddhhmmss");
	public static synchronized String getTransactionDatetime(){
		String date=null;
		date=formatter.format(new Date());
		return date;
	}
	
	public static synchronized String getBankDatetime(){
		String date=null;
		date=formatter2.format(new Date());
		return date;
	}
	
	public static synchronized String getSerialPrefix(){
		String date=null;
		date=formatter3.format(new Date());
		return date;
	}

}
