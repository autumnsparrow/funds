/**
 * 
 */
package com.palmcommerce.funds.packet.security.openssl;

/**
 * @author sparrow
 *
 */
public interface IEncrypt {
	
	/**
	 * 
	 * @param content
	 * @param fromCode
	 * @return
	 */
	public byte[] encrypt(byte[] content,String toCode);
	
	/**
	 * 
	 * 
	 * @param signature
	 * @param toCode
	 * @return
	 */
	public byte[] signature(byte[] content,String fromCode);
	
	

}
