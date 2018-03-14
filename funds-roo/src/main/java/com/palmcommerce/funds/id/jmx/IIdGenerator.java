package com.palmcommerce.funds.id.jmx;
/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 26, 2013
 *
 */


/**
 * @author sparrow
 *
 */
public interface IIdGenerator {
	
	public String findRemoteSerialByLocalSerial(String localSerial);
	
	
	public String findLocalSerialByRemoteSerial(String serials);
	

}
