package com.palmcommerce.funds.mock.client.model;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.id.serial.NumericGenerator;
import org.apache.commons.id.serial.PrefixedAlphanumericGenerator;
import org.apache.commons.id.serial.PrefixedLeftPaddedNumericGenerator;
import org.apache.commons.id.serial.TimeBasedAlphanumericIdentifierGenerator;
import org.apache.commons.lang3.time.DateUtils;

import com.palmcommerce.funds.protocol.util.SerialGenerator;

/**
 * 
 */

/**
 * @author sparrow
 *
 */
public class SerialNumberGenerator {

	/**
	 * 
	 */
	public SerialNumberGenerator() {
		// TODO Auto-generated constructor stub
	}
	
	final static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	final static DateFormat formatter2 = new SimpleDateFormat("yyyyMMdd");
	final static DateFormat formatter3 = new SimpleDateFormat("yyyyMMddhhmmss");
	
	public static synchronized String getSerialPrefix(){
		String date=null;
		date=formatter3.format(new Date());
		return date;
	}
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
	static final PrefixedLeftPaddedNumericGenerator generator=new PrefixedLeftPaddedNumericGenerator(getSerialPrefix(), true, 20);
	public static synchronized String generate(){
		 return generator.nextStringIdentifier();
	}
	

	static final PrefixedLeftPaddedNumericGenerator name=new PrefixedLeftPaddedNumericGenerator("N", true, 10);
	public static synchronized String generateUserName(){
		return name.nextStringIdentifier();
	}
	static final PrefixedLeftPaddedNumericGenerator numberGenerator=new PrefixedLeftPaddedNumericGenerator("U"+getBankDatetime(), true, 18);
	public static synchronized String generateUserId(){
		return numberGenerator.nextStringIdentifier();
	}
	
	
	
	//generator=new PrefixedAlphanumericGenerator(SerialGenerator.getSerialPrefix(), true, 18);
}
