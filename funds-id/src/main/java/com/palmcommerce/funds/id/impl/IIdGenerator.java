/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 26, 2013
 *
 */
package com.palmcommerce.funds.id.impl;

/**
 * @author sparrow
 *
 */
public interface IIdGenerator {
	
	public String getLocalSerialForRemoteSerial(String serial);
	
	
	public String getRemoteSerialByLocalSerial(String localSerial);
	

}
