/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Nov 8, 2013
 *
 */
package com.palmcommerce.funds.configuration.v2;

/**
 * @author sparrow
 *
 */
public class OpenSslCryptorEntryNative {

	/**
	 * 
	 */
	public OpenSslCryptorEntryNative() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	/**
	 * 
	 * 
	 * 
	 * @param content
	 * @return
	 * @throws OpenSslRSAExcpetion
	 */
	public  native byte[] signature(byte[] content) throws OpenSslRSAExcpetion;

	/**
	 * 
	 * 
	 * 
	 * @param content
	 * @return
	 * @throws OpenSslRSAExcpetion
	 */
	public  native boolean validateSignature(byte[] content) throws OpenSslRSAExcpetion;

	/**
	 * 
	 * 
	 * 
	 * @param content
	 * @return
	 * @throws OpenSslRSAExcpetion
	 */
	public native byte[]  decrypt(byte[] content) throws OpenSslRSAExcpetion;
	
	
	/**
	 * 
	 * 
	 * @param content
	 * @return
	 * @throws OpenSslRSAExcpetion
	 */
	public native byte[]  encrypt(byte[] content) throws OpenSslRSAExcpetion;

}
