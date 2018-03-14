/**
 * 
 */
package com.palmcommerce.funds.packet.security.openssl;

import com.palmcommerce.funds.packet.security.openssl.impl.DefaultDecrypt;

/**
 * @author sparrow
 *
 */
public class DecryptFactory {

	/**
	 * 
	 */
	private DecryptFactory() {
		// TODO Auto-generated constructor stub
	}
	private static final DecryptFactory factory=new DecryptFactory();
	
	
	public static DecryptFactory getFactory(){
		return factory;
	}
	
	private  IDecrypt instance=new DefaultDecrypt();
	public  IDecrypt getDecrypt(){
		return instance;
	}
}
