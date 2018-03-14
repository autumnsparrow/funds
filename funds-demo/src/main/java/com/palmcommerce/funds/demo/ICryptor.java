/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Sep 29, 2013
 *
 */
package com.palmcommerce.funds.demo;

/**
 * @author sparrow
 *
 */
public interface ICryptor {

	
	public  byte[] signature(byte[] content) throws Exception;
	
	public  boolean validateSignature(byte[] content)throws Exception;
	
	public  byte[] decrypt(byte[] content) throws Exception ;
	
	public  byte[]  encrypt(byte[] content) throws Exception ;
}
