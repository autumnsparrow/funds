/**
 * 
 */
package com.palmcommerce.funds.packet.security.openssl;

/**
 * @author sparrow
 *
 */
public interface IDecrypt {
	
	/**
	 * 
	 * 
	 * @param packet
	 * @param fromCode
	 * @return
	 */
	public byte[]  decrypt(byte[] packet,String toCode);
	
	/**
	 * 
	 * 
	 * @param signature
	 * @param fromCode
	 * @return
	 */
	public boolean validateSignature(byte[] signature,int offset,int len,String fromCode);

}
