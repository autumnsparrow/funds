/**
 * 
 */
package com.palmcommerce.funds.packet.security.openssl;

import com.palmcommerce.funds.packet.security.openssl.impl.DefaultEncrypt;

/**
 * @author sparrow
 *
 */
public class EncryptFactory {
	
	private static final EncryptFactory factory=new EncryptFactory();
	
	public static EncryptFactory getFactory(){
		return factory;
	}
	
	private  IEncrypt  instance=new DefaultEncrypt();
	public  IEncrypt getEncrypt(){
		return instance;
	}

}
